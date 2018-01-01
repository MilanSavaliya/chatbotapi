package chatbotapi

import grails.core.GrailsApplication
import grails.util.Environment
import grails.plugins.*

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    def index() {
        def data = [grailsApplication: grailsApplication, pluginManager: pluginManager]
        data.put("Author", "Milan Savaliya")
        return data
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