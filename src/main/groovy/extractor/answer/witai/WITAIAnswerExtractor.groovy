package extractor.answer.witai

import extractor.answer.AnswerExtractor
import extractor.answer.ApiResponse
import grails.plugins.rest.client.RestResponse
import groovy.transform.CompileStatic
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
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
        def headers = new HttpHeaders()
        headers.set(HttpHeaders.ACCEPT, 'application/vnd.wit.20170307+json')
        headers.set(HttpHeaders.AUTHORIZATION, 'Bearer GWPMQRTYXBIZBG4PIDD5AYTIN4KACZAS')

        def uriComponentBuilder = UriComponentsBuilder.fromHttpUrl(getUrlToCall())
                .queryParam('v', getAPIVersion())
                .queryParam('q', userGivenInput.userGivenAnswer)
                .queryParam('context', '{ "entities" : ' + entityToEncode + ' }')

        def entity = new HttpEntity<>(headers)
        def responseEntity = new RestTemplate().exchange(uriComponentBuilder.build().encode().toUri(), HttpMethod.GET, entity, String);
        new WITAIResponse(new RestResponse(responseEntity))
    }

    @CompileStatic
    private String getAPIVersion() {
        '28122017' //DDMMYYYY
    }

    String getUrlToCall() {
        ('https://api.wit.ai/message')
    }
}
