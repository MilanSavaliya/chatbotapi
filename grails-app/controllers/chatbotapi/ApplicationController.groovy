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
