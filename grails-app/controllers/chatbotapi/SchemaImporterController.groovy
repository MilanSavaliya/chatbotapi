package chatbotapi

import grails.plugins.GrailsPluginManager
import grails.plugins.PluginManagerAware
import groovy.transform.Canonical

class SchemaImporterController implements PluginManagerAware {
    static responseFormats = ['json']

    GrailsPluginManager pluginManager
    SchemaImporterService schemaImporterService

    def index() {
        def logs = schemaImporterService.importDefaultSchema(true)
        logs.each {
            println it
        }
        return [logs: logs]
    }
}