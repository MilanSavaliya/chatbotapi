package chatbotapi

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean

class Application extends GrailsAutoConfiguration {

    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override
    Collection<String> packageNames() {
        super.packageNames() + ['chatbotapi']
    }

    @Bean
    @Qualifier("jwtSignAlgorithm")
    Algorithm jwtSignAlgorithm() {
        def tokenKey = System.getenv()['TOKEN_KEY']
        if (tokenKey == null)
            throw new NullPointerException("TOKEN_KEY env variable not found!")
        return Algorithm.HMAC256(tokenKey)
    }

    @Bean
    JWTVerifier jwtVerifier(
            @Qualifier("jwtSignAlgorithm")
            Algorithm jwtSignAlgorithm
    ){
        return JWT.require(jwtSignAlgorithm).build()
    }
}