package chatbotapi

import grails.core.GrailsApplication
import grails.plugins.GrailsPluginManager
import grails.plugins.PluginManagerAware
import shared.beans.QuestionToAskNext
import shared.beans.UserGivenInput
import org.springframework.beans.factory.annotation.Autowired
import shared.beans.UserToken

class ApplicationController implements PluginManagerAware {
    static responseFormats = ['json']


    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager


    private QuestionProviderService questionProviderService
    private JwtTokenGenValidatorService tokenGenValidatorService
    private ApplicationService applicationService

    @Autowired
    ApplicationController(
            QuestionProviderService questionProviderService,
            JwtTokenGenValidatorService tokenGenValidatorService,
            ApplicationService applicationService
    ) {
        this.grailsApplication = grailsApplication
        this.pluginManager = pluginManager
        this.questionProviderService = questionProviderService
        this.tokenGenValidatorService = tokenGenValidatorService
        this.applicationService = applicationService
    }


    def index() {
     [grailsApplication: this.grailsApplication, pluginManager: this.pluginManager, userToken: params.userToken]
    }

    def save(UserGivenInput userGivenInput) {
        //At this point we are sure that token would be present in the request and that token would be the valid token
        def jobAppId = (params.userToken as UserToken).jobApplicationId
        def questionToAskNext = this.applicationService.getTheNextQuestion(jobAppId, userGivenInput)
        //Once you have that, get the QuestionList and get the Appropriate Question To Ask.
        //If This is first time, ask first question
        //if this is not the first time,
            // Save the user Answer By parsing appropriate target Entity and its Type,
        // just ask the very next question and return the question
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