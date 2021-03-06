package extractor.answer.witai

import extractor.answer.ApiResponse
import grails.plugins.rest.client.RestResponse
import groovy.json.JsonSlurper
import groovy.transform.Canonical

class WITAIResponse implements ApiResponse {
    private RestResponse response
    private def parsedObject

    WITAIResponse(RestResponse response) {
        this.response = response
        this.parsedObject = new JsonSlurper().parseText(response.body.toString())
    }


    String get_Text() {
        return this.parsedObject._text
    }

    String meaning() {
        return getMostConfidentAnswer()
    }

    private String getMostConfidentAnswer() {
        def entities = getEntities()
        def mostClosestEntity = entities.collect { String key, List<WitAIEntity> values ->
            values.max { WitAIEntity entity ->
                entity.confidence
            }
        }.max { WitAIEntity entity -> entity.confidence }

        return mostClosestEntity?.value
    }

    Map<String, List<WitAIEntity>> getEntities() {
        Map output = [:]
        this.parsedObject.entities?.each { key, value ->
            def listOfVals = []
            value?.each {
                println it
                def entity = new WitAIEntity()
                entity.confidence = it.confidence ?: 0
                entity.suggested = it.suggested ?: false
                entity.value = it.value ?: null
                entity.type = it.type ?: null
                listOfVals.add(entity)
            }
            output.put(key, listOfVals)
        }
        output
    }
}

@Canonical
class WitAIEntity {
    boolean suggested
    double confidence
    String value
    String type
}
