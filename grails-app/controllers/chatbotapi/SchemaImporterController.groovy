package chatbotapi

import grails.plugins.GrailsPluginManager
import grails.plugins.PluginManagerAware
import groovy.transform.Canonical

class SchemaImporterController implements PluginManagerAware {
    static responseFormats = ['json']

    GrailsPluginManager pluginManager
    SchemaImporterService schemaImporterService

    def index() {
        def data = [:]
        data.stringList = [
                new LogEntry(logText: 'One'),
                new LogEntry(logText: 'Two'),
                new LogEntry(logText: 'Three'),
                new LogEntry(logText: 'Four')
        ] as ArrayList<LogEntry>
        return data
    }
}

@Canonical
class LogEntry{
    String logText
}