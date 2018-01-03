package chatbotapi

import exceptions.runtime.ChatbotAPIException
import exceptions.runtime.ChatbotAPIExceptionImpl
import org.springframework.beans.factory.annotation.Autowired
import shared.beans.UserToken

import javax.persistence.EntityNotFoundException

class TokensController {

    @Autowired
    JwtTokenGenValidatorService tokenGenValidatorService

    def index(Long jobApplicationId) {
        def data = [:]
        def jobApp = JobApplication.read(jobApplicationId)
        if (jobApp != null) {
            def chatToken = ChatTokens.findByJobApplication(jobApp)
            if (chatToken) {
                data.token = chatToken.token
            } else {
                renderRuntimeErrorView(
                        new ChatbotAPIExceptionImpl(
                                new EntityNotFoundException("ChatToken with given JobApplication Id $jobApplicationId Not found in our Database"), 500
                        )
                )
            }
        } else {
            renderRuntimeErrorView(new ChatbotAPIExceptionImpl(
                    'JobApplication with given ID not found in the DATABASE',
                    500,
                    null,
                    null))
        }

//        return [token: 'sometoken', responseCode: 500]

    }

    private renderRuntimeErrorView(ChatbotAPIException exception) {
        render(view: "/errors/runtime", model:
                [
                        exception: exception,
                ]
        )
    }

    def save() {
        def jobApplication = new JobApplication();
        jobApplication.currentStatus = JobApplicationStatus.get(1)
        jobApplication.createdAt = new Date()
        def id = jobApplication.save()
        def token = tokenGenValidatorService.generateToken(
                new UserToken(
                        jSessionId: request.getSession(true),
                        currentQuestionListIndex: 0,
                        currentSubQuestionListIndex: 0,
                        jobApplicationId: jobApplication.getId()
                )
        ) as String
        return ['token': token]
    }
}
