package extractor.answer.witai

import chatbotapi.JobApplication
import extractor.answer.AnswerExtractor
import extractor.answer.AnswerExtractorAdapter
import extractor.answer.AnswerExtractorFactory
import shared.beans.Question
import shared.beans.UserGivenInput

class WitAIAnswerExtractorFactory implements AnswerExtractorFactory {

    private UserGivenInput userGivenInput
    private Question lastQuestionAsked
    private JobApplication jobApp

    WitAIAnswerExtractorFactory(UserGivenInput userGivenInput, Question lastQuestionAsked, JobApplication jobApp){
        this.userGivenInput = userGivenInput
        this.lastQuestionAsked = lastQuestionAsked
        this.jobApp = jobApp
    }

    @Override
    AnswerExtractor getAnswerExtractor() {
        new WITAIAnswerExtractor(this.userGivenInput, this.lastQuestionAsked)
    }

    @Override
    AnswerExtractorAdapter getAnswerExtractorAdapter() {
        new WITAIAnswerExtractorAdapter(
                this.userGivenInput,
                this.lastQuestionAsked,
                this.jobApp
        )
    }
}
