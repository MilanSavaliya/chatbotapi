package extractor.answer.witai

import extractor.answer.AnswerExtractor
import extractor.answer.ApiResponse
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import groovy.transform.CompileStatic
import shared.beans.Question
import shared.beans.UserGivenInput

class WITAIAnswerExtractor implements AnswerExtractor {

    private UserGivenInput userGivenInput
    private Question questionAsked

    WITAIAnswerExtractor(
            UserGivenInput userGivenInput,
            Question questionAsked
    ) {
        if (userGivenInput == null) throw NullPointerException("UserGiven Input IS NULL")
        if (questionAsked == null) throw NullPointerException("Question Input IS NULL")

        this.userGivenInput = userGivenInput
        this.questionAsked = questionAsked
    }

    @CompileStatic
    @Override
    ApiResponse extractAnswer() {
        //Code to get the API Response
        def entityToEncode = WITAIEntityProvider.getEntityToUse("${questionAsked.targetEntity}.${questionAsked.targetField}".toString())
        def parameters = [
                'context': '{ "entities" : ' + entityToEncode + ' }'
        ]
        String urlToCall = getUrlToCall(parameters)
        println " Generated URL " + urlToCall

        def restBuilder = new RestBuilder();
        def response = restBuilder.get(urlToCall) {
            accept 'application/vnd.wit.20170307+json'
            auth 'Bearer GWPMQRTYXBIZBG4PIDD5AYTIN4KACZAS'
        }

        println response.body.toString()

        new WITAIResponse(response)
    }

    @CompileStatic
    private String getAPIVersion() {
        '28/12/2017'
    }

    String getUrlToCall(Map parameters) {
        String parametersToAppend = parameters.collect { key, val ->
            def value = java.net.URLEncoder.encode("=${val}".toString(), "UTF-8")
            "${key}${value}"
        }.join('&')

        ('https://api.wit.ai/message?v=28/12/2017&q='+ java.net.URLEncoder.encode(userGivenInput.userGivenAnswer, "UTF-8") + '&' + parametersToAppend)
    }
}
