package chatbotapi

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import exceptions.TokenKeyNotPresent
import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean

class Application extends GrailsAutoConfiguration {

    Application(){
        log.info("Creating Application using Logback")
    }

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
        def tokenKey = System.getProperty("TOKEN_KEY") as String
        if (tokenKey == null) throw new TokenKeyNotPresent()
        Algorithm.HMAC256(tokenKey)
    }

    @Bean
    JWTVerifier jwtVerifier(
            @Qualifier("jwtSignAlgorithm")
                    Algorithm jwtSignAlgorithm
    ) {
        JWT.require(jwtSignAlgorithm).build()
    }
}