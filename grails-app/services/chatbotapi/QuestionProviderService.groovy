package chatbotapi

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.springframework.cache.annotation.Cacheable

@Transactional
class QuestionProviderService {

    @Cacheable(value = "questionsList")
    def getQuestions() {
        //TODO remove this once test is over
        println "In the getQuestion method"
        def inputStream = getClass().getResourceAsStream("/Questions.json")
        def parse = new JsonSlurper().parse(inputStream)
        def questionList = parse.collect{ node ->
            def chain = [] as LinkedList
            chain.add(node)
            while (node.next != null) {
                node = node.next
                chain.add(node)
            }
            return chain
        }

        return questionList
    }
}