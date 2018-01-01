package chatbotapi

import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import grails.core.GrailsApplication
import grails.plugins.GrailsPluginManager
import grails.plugins.PluginManagerAware
import org.springframework.beans.factory.annotation.Autowired
import shared.beans.UserToken

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager
    QuestionProviderService questionProviderService
    JwtTokenGenValidatorService tokenGenValidatorService


    def index() {
        String token = tokenGenValidatorService.generateToken(
                new UserToken(
                        jSessionId: request.getRequestedSessionId(),
                        currentQuestionListIndex: 0,
                        currentSubQuestionListIndex: 1,
                        jobApplicationId: 1
                )
        )

        def userToken = tokenGenValidatorService.parseUserToken( tokenGenValidatorService.decodeToken(token) )
        println userToken.toString()


        def data = [grailsApplication: grailsApplication, pluginManager: pluginManager]
        data.put("Author", "Milan Savaliya")
        return data
    }

    def save() {
        println questionProviderService.getQuestions()[0].size()
    }
}

// Creating Question Decision Tree Or Store it in a Database to consider Time Constraint.
// Answer Extraction Module
// Adapter Module to get the data we want

/**
 * Controller will request Answer Exaction Module to get the Answer
 * Controller will pass extracted answer to the Adapter Module to Convert it into the understandable form
 * Controller will request data saving module to persist the answer
 * Controller will request Next Question Fetcher Module to get the Next Question
 *
 *
 *
 *
 * Next Features:
 *      1) Resuming Job Application Status by asking for Job Application Id
 *      2) Proper Resume Upload Feature.[ In case if User is in Hurry ]
 *
 *
 *
 *

 **/