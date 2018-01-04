package chatbotapi

import com.auth0.jwt.exceptions.JWTVerificationException
import exceptions.runtime.ChatbotAPIExceptionImpl
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import shared.beans.UserToken


@CompileStatic //To Improve Performance as Interceptors will be executed for every request.
class TokenInterceptor {


    private JwtTokenGenValidatorService tokenGenValidatorService

    @Autowired
    TokenInterceptor(
            JwtTokenGenValidatorService tokenGenValidatorService
    ){
        matchAll().excludes(controller:"tokens")
        this.tokenGenValidatorService = tokenGenValidatorService
    }

    //This method executes just before request reaches the targeted controller's method
    //TODO Complete Token Based Verification for the controllers
    boolean before() {
        def authHeader = request.getHeader('Authorization')?.toString()
        if ( authHeader == null ) return false

        def authHeaderParts = authHeader.split "\\s+"
        if( authHeaderParts.length < 1 ) return false

        def token = authHeaderParts[1] as String
        if( (token?.trim() ?: '').isEmpty() == true ) return false

        try {
            def userToken = tokenGenValidatorService.parseUserToken(token) as UserToken
            params.userToken = userToken
            return true
        }catch(JWTVerificationException jvEx){
            render( view: '/errors/runtime', model: [exception: new ChatbotAPIExceptionImpl(new RuntimeException('Token Verification Failed'), 400)])
            false
        }
    }

    boolean after() {
        println "In the After state"
        true
    }

    void afterView() {
        // no-op
    }
}
