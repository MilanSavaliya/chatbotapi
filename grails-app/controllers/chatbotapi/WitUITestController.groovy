package chatbotapi

import grails.core.GrailsApplication
import grails.rest.*
import grails.converters.*
import groovyx.net.http.FromServer
import groovyx.net.http.HttpBuilder
import org.springframework.beans.factory.annotation.Value

class WitUITestController {
	static responseFormats = ['json']

    @Value('${witui.URI.messageVersionURI}')
    String urlToTest

    GrailsApplication grailsApplication

    def index() {

        println urlToTest

        def headers = [
                'Authorization' : 'Bearer GWPMQRTYXBIZBG4PIDD5AYTIN4KACZAS',
                'Accept': 'application/vnd.wit.20170307+json',
        ]

        def http = HttpBuilder.configure {
            request.uri = finalUrl + '?q=Milan'
            request.headers = headers
        }.get {
            response.when( 200 ) {
                FromServer fs ->
                    println "Milan : " + fs.message
            }
        }

        http.get()
    }
}
