package chatbotapi


import grails.rest.*
import grails.converters.*

class SchemaImporterController {
	static responseFormats = ['json']
    SchemaImporterService schemaImporterService
	
    def index() {
        def isToImport = request.parameterMap['import'].asBoolean()

        def data = ['imported': false]
        if( isToImport ) {
            def logs = this.schemaImporterService.importDefaultSchema(true)
            if( !logs ){
                data['logs'] = ['Records are already inserted']
            }else{
                data['logs'] = logs
            }

            data['imported'] = true
        }
        return data
    }
}
