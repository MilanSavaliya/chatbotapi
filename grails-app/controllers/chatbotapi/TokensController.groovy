package chatbotapi

import exceptions.runtime.ChatbotAPIException
import exceptions.runtime.ChatbotAPIExceptionImpl
import org.springframework.beans.factory.annotation.Autowired
import shared.beans.UserToken

import javax.persistence.EntityNotFoundException
import javax.persistence.PersistenceException

class TokensController {
    private JwtTokenGenValidatorService tokenGenValidatorService
    private ChatInfoService tokenDaoService

    @Autowired
    TokensController(
            JwtTokenGenValidatorService tokenGenValidatorService,
            ChatInfoService tokenDaoService
    ){
        this.tokenGenValidatorService = tokenGenValidatorService
        this.tokenDaoService = tokenDaoService
    }

    //tokens/{{id}}
    def show(Long id){
        def data = [:]
        def jobApp = JobApplication.read(id)
        if (jobApp != null) {
            def chatToken = ChatInfo.findByJobApplication(jobApp)
            if (chatToken) {
                data.token = chatToken.token
                render( view: 'save', model: data)
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

    /**
     * Creates new Token For new Guest User and Save it in the Database with the associated job application id  Session. Then Returns UserToken Object
     * @return Token
     */
    def save() {
        def jobApplication = new JobApplication();
        jobApplication.currentStatus = JobApplicationStatus.get(1)
        jobApplication.createdAt = new Date()
        def chatInfo = new ChatInfo(jobApplication: jobApplication)
        def id = jobApplication.save()
        if( id != null  ){
            def token = tokenGenValidatorService.generateToken(
                    new UserToken(
                            jobApplicationId: jobApplication.getId()
                    )
            ) as String
            if( this.tokenDaoService.save(new ChatInfo(jobApplication: jobApplication, token: token)) != null ) {
                return ['token': token]
            }
        }

        render(view: '/errors/runtime', model: [exception: new ChatbotAPIExceptionImpl(new PersistenceException("Something wrong with Persistence"), 500)])
    }
}
