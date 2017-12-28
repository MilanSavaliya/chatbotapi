package chatbotapi

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

class Application extends GrailsAutoConfiguration {

    @Value('${witui.URI.host}')
    String urlToTest


    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Bean
    String generateBean(){
        println 'Milan this is string to test ' + urlToTest
    }
}