package extractor.answer.witai

import extractor.answer.AnswerExtractor
import extractor.answer.ApiResponse
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
                'q'      : userGivenInput.userGivenAnswer,
                'context': '{ "entities" : ' + entityToEncode + ' }'
        ]
        String urlToCall = getUrlToCall(parameters)
        println " Generated URL " + urlToCall
        null
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

        ('https://api.wit.ai/message?v=28/12/2017&' + parametersToAppend)
    }
}
