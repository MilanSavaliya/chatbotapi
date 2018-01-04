package chatbotapi

import grails.gorm.transactions.Transactional
import shared.beans.QuestionToAskNext
import shared.beans.UserGivenInput
import org.springframework.beans.factory.annotation.Autowired
import shared.beans.Question

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
            if (chatInfo.currentQuestionIndex == 0 && chatInfo.currentSubQuestionIndex == 0) {
                def question = this.questionProviderService.getQuestion(chatInfo.currentQuestionIndex, chatInfo.currentSubQuestionIndex)
                return convertToQuestionToAskNext(question, chatInfo, userGivenInput)
            }else{
                //Let's talk with the WitUi, Extract the Answer and then Fetch the Next Question
            }
        }

        null
    }

    private void convertToQuestionToAskNext(Question question, ChatInfo chatInfo, UserGivenInput lastUserGivenInput) {
        new QuestionToAskNext(
                question: question,
                currentQuestionIndex: chatInfo.currentQuestionIndex,
                currentSubQuestionIndex: chatInfo.currentSubQuestionIndex,
                lastUserGivenInput: lastUserGivenInput
        )
    }
}
