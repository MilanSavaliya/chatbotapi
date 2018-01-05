package chatbotapi

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import org.springframework.beans.factory.annotation.Value

class WitUITestController {
    static responseFormats = ['json']

    @Value('${witui.URI.messageVersionURI}')
    String urlToTest

    //Get Request Test
    def index() {
        println urlToTest = urlToTest.concat('&q=Milan&')
        def headers = [
                'Authorization': 'Bearer GWPMQRTYXBIZBG4PIDD5AYTIN4KACZAS',
                'Accept'       : 'application/vnd.wit.20170307+json',
        ]
        def builder = new RestBuilder();
        RestResponse response = builder.get(urlToTest) {
            accept 'application/vnd.wit.20170307+json'
            auth 'Bearer GWPMQRTYXBIZBG4PIDD5AYTIN4KACZAS'
        }
        println response.body.toString()
    }
}
