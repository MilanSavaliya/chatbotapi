package extractor.answer.witai

import extractor.answer.AnswerExtractor
import extractor.answer.AnswerExtractorAdapter
import extractor.answer.AnswerExtractorFactory
import shared.beans.Question
import shared.beans.UserGivenInput

class WitAIAnswerExtractorFactory implements AnswerExtractorFactory {

    private UserGivenInput userGivenInput
    private Question lastQuestionAsked

    WitAIAnswerExtractorFactory(UserGivenInput userGivenInput, Question lastQuestionAsked){
        this.userGivenInput = userGivenInput
        this.lastQuestionAsked = lastQuestionAsked
    }

    @Override
    AnswerExtractor getAnswerExtractor() {
        new WITAIAnswerExtractor(this.userGivenInput, this.lastQuestionAsked)
    }

    @Override
    AnswerExtractorAdapter getAnswerExtractorAdapter() {
        new WITAIAnswerExtractorAdapter()
    }
}
