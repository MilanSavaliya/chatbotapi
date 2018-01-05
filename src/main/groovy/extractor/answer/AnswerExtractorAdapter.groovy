package extractor.answer

import chatbotapi.JobApplication

interface AnswerExtractorAdapter {
    JobApplication getTheJobApplication(ApiResponse apiResponse)
}
