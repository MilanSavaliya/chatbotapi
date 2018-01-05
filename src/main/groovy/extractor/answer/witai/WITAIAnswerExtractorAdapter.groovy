package extractor.answer.witai

import chatbotapi.JobApplication
import extractor.answer.AnswerExtractorAdapter
import extractor.answer.ApiResponse

class WITAIAnswerExtractorAdapter implements AnswerExtractorAdapter{
    @Override
    JobApplication getTheJobApplication(ApiResponse apiResponse) {
        def witApiResponse = apiResponse as WITAIResponse
        null
    }
}
