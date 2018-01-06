package chatbotapi

import extractor.answer.AnswerExtractorFactory
import extractor.answer.witai.WitAIAnswerExtractorFactory
import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Autowired
import shared.beans.Question
import shared.beans.QuestionToAskNext
import shared.beans.UserGivenInput

@Transactional
class ApplicationService {

    private ChatInfoService chatInfoService;
    private QuestionProviderService questionProviderService

    @Autowired
    ApplicationService(
            ChatInfoService chatInfoService,
            QuestionProviderService questionProviderService
    ) {
        this.chatInfoService = chatInfoService
        this.questionProviderService = questionProviderService
    }

    QuestionToAskNext getTheNextQuestion(Long jobAppId, UserGivenInput userGivenInput) {
        def jobApp = JobApplication.get(jobAppId)
        if (jobApp) {
            def chatInfo = ChatInfo.get(jobApp.chatInfo.id) as ChatInfo
            //TODO Remove False [ This is debugging purpose only ]
            if (false && chatInfo.currentQuestionIndex == 0 && chatInfo.currentSubQuestionIndex == 0) {
                def question = this.questionProviderService.getQuestion(chatInfo.currentQuestionIndex, chatInfo.currentSubQuestionIndex)
                return convertToQuestionToAskNext(question, chatInfo, userGivenInput)
            } else {
                //Currently we are only having WITAI
                AnswerExtractorFactory factory = new WitAIAnswerExtractorFactory(
                        userGivenInput,
                        this.questionProviderService.getQuestion(chatInfo.currentQuestionIndex, chatInfo.currentSubQuestionIndex),
                        jobApp
                )
                def answerExtractor = factory.getAnswerExtractor()
                def apiResponse = answerExtractor.extractAnswer()
                def modifiedJobApp = factory.getAnswerExtractorAdapter().getTheJobApplication(apiResponse)
                modifiedJobApp.save()
                if (modifiedJobApp.hasErrors()) {
                    //Todo find logic to handle errors
                    println modifiedJobApp.errors
                } else {
                    //check if this is the last node of current parent index
                    def questions = this.questionProviderService.getSubQuestions(chatInfo.currentQuestionIndex)
                    if (questions) {
                        def subQuestion = questions.get(chatInfo.currentSubQuestionIndex + 1)
                        if (subQuestion) {
                            //let's save the state of chatinfo first,
                            chatInfo.currentSubQuestionIndex++
                            chatInfo.save()
                            return convertToQuestionToAskNext(subQuestion, chatInfo, userGivenInput)
                        }

                        //TODO think for logic when no more subquestion is present
                    }
                    //TODo think for logic when no more question set present
                }
            }
        }
    }

    QuestionToAskNext convertToQuestionToAskNext(Question question, ChatInfo chatInfo, UserGivenInput lastUserGivenInput) {
        new QuestionToAskNext(
                question: question,
                currentQuestionIndex: chatInfo.currentQuestionIndex,
                currentSubQuestionIndex: chatInfo.currentSubQuestionIndex,
                lastUserGivenInput: lastUserGivenInput
        )
    }
}
