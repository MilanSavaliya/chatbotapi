package chatbotapi

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class JobApplicationStatusSpec extends Specification implements DomainUnitTest<JobApplicationStatus> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:
            def status = new JobApplicationStatus(id: 1, name: 'Created', description: 'Some Foo Description')
        status.save()

        status.id == 1
    }
}
