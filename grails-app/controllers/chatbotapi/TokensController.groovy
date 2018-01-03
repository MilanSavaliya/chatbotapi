package chatbotapi

import exceptions.runtime.ChatbotAPIException
import exceptions.runtime.ChatbotAPIExceptionImpl
import org.springframework.beans.factory.annotation.Autowired
import shared.beans.UserToken

import javax.persistence.EntityNotFoundException
import javax.persistence.PersistenceException

class TokensController {


    JwtTokenGenValidatorService tokenGenValidatorService
    ChatTokenDaoService chatTokenDaoService

    @Autowired
    TokensController(
            JwtTokenGenValidatorService tokenGenValidatorService,
            ChatTokenDaoService chatTokenDaoService
    ){
        this.tokenGenValidatorService = tokenGenValidatorService
        this.chatTokenDaoService = chatTokenDaoService
    }

    def show(Long id){
        def data = [:]
        def jobApp = JobApplication.read(id)
        if (jobApp != null) {
            def chatToken = ChatTokens.findByJobApplication(jobApp)
            if (chatToken) {
                data.token = chatToken.token
                render( view: 'tokens/save', model: data)
            } else {
                renderRuntimeErrorView(
                        new ChatbotAPIExceptionImpl(
                                new EntityNotFoundException("ChatToken with given JobApplication Id $id Not found in our Database"), 500
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
        if( id != null  ){
            def token = tokenGenValidatorService.generateToken(
                    new UserToken(
                            jSessionId: request.getSession(true),
                            currentQuestionListIndex: 0,
                            currentSubQuestionListIndex: 0,
                            jobApplicationId: jobApplication.getId()
                    )
            ) as String
            if( this.chatTokenDaoService.save(new ChatTokens(jobApplication: jobApplication, token: token)) != null ) {
                return ['token': token]
            }
        }

        render(view: 'error/runtime', model: [exception: new ChatbotAPIExceptionImpl(new PersistenceException("Something wrong with Persistence"), 500)])
    }
}
