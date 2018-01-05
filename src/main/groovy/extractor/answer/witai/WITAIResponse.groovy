package extractor.answer.witai

import extractor.answer.ApiResponse
import grails.plugins.rest.client.RestResponse
import groovy.json.JsonSlurper

class WITAIResponse implements ApiResponse{
    private RestResponse response
    private def parsedObject

    WITAIResponse(RestResponse response){
        this.response = response
        this.parsedObject = new JsonSlurper().parseText(response.body.toString())
    }


    String get_Text(){
        return this.parsedObject._text
    }

    WitAIEntity[] getEntities(){
        null
    }

}

class WitAIEntity{
    boolean suggested
    int confidence
    String value
    String type
}
