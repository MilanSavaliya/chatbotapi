package extractor.answer.witai

import chatbotapi.JobApplication
import chatbotapi.PersonalDetail
import extractor.answer.AnswerExtractorAdapter
import extractor.answer.ApiResponse
import shared.beans.Question
import shared.beans.UserGivenInput

class WITAIAnswerExtractorAdapter implements AnswerExtractorAdapter {

    private UserGivenInput userGivenInput
    private Question lastQuestionAsked
    private JobApplication jobApp

    WITAIAnswerExtractorAdapter(UserGivenInput userGivenInput, Question lastQuestionAsked, JobApplication jobApp) {
        this.userGivenInput = userGivenInput
        this.lastQuestionAsked = lastQuestionAsked
        this.jobApp = jobApp
    }

    @Override
    JobApplication getTheJobApplication(ApiResponse apiResponse) {
        def witApiResponse = apiResponse as WITAIResponse
        def entity = getEntityFromTargetEntity(witApiResponse, lastQuestionAsked.targetEntity, lastQuestionAsked.targetField)
        jobApp
    }

    /**
     * Method returns the target Entity if found if not then it will bind entity with the JobApp and then returns it
     * @param response
     * @param jobApplication
     * @param targetEntity
     * @return
     */
    def getEntityFromTargetEntity(
            WITAIResponse response,
            String targetEntity,
            String targetField
    ) {
        if (targetEntity.equalsIgnoreCase("PersonalDetail")) {
            def personalDetail = jobApp.getPersonalDetail() ?: new PersonalDetail(jobApplication: jobApp)
            jobApp.setPersonalDetail(personalDetail)

            if (targetField.equalsIgnoreCase("fullName")) {
                personalDetail.fullName = response.meaning()
            } else if (targetField.equalsIgnoreCase("email")) {
                personalDetail.email = response.meaning()
            } else if (targetField.equalsIgnoreCase("mobileNo")) {
                personalDetail.mobileNo = response.meaning()
            } else if (targetField.equalsIgnoreCase("shortSummary")) {
                personalDetail.shortSummary = response.meaning()
            }
            return personalDetail
        }
    }
}
