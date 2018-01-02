package chatbotapi

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic

@CompileStatic
@Transactional
class SchemaImporterService {

    def importDefaultSchema(boolean getLog = false) {
        return importJobApplicationStatus(getLog)
    }

    private def importJobApplicationStatus(boolean getLog = false) {
        def js = JobApplicationStatus.read(1)
        def logEntry = " Job Application Status with ID "
        if (js == null) {
            //Import Default JobApplication Status
            JobApplicationStatus[] applicationStatus = getDefaultJobApplicationStatus()
            def all = JobApplicationStatus.saveAll(applicationStatus)
            if (getLog) {
                def collect = all.collect {
                    def finalStr = logEntry + it + ' has been inserted'
                    log.debug(finalStr)
                    finalStr
                }
                return collect
            } else {
                all.each {
                    def finalStr = logEntry + it + ' has been inserted'
                    log.debug(finalStr)
                }
            }
        }else{
            def collect = JobApplicationStatus.findAll().collect {
                def finalStr = logEntry + it.id + ' has been inserted'
                log.debug(finalStr)
                finalStr
            }
            collect
        }
    }

    private JobApplicationStatus[] getDefaultJobApplicationStatus() {
        [
                new JobApplicationStatus(name: 'CREATED', description: 'job application is created'),
                new JobApplicationStatus(name: 'IN_PROGRESS', description: 'job application creation is in process'),
                new JobApplicationStatus(name: 'COMPLETED', description: 'job application is completed')
        ] as JobApplicationStatus[]
    }
}
