package chatbotapi

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.springframework.cache.annotation.Cacheable
import shared.beans.Question

@Transactional
class QuestionProviderService {

    @Cacheable(value = "questionsList")
    List<LinkedList<Question>> getQuestions() {
        def inputStream = getClass().getResourceAsStream("/Questions.json")
        def parse = new JsonSlurper().parse(inputStream)

        parse.collect { node ->
            def chain = [] as LinkedList
            chain.add(convertToQuestionBean(node))
            while (node.next != null) {
                node = node.next
                chain.add(convertToQuestionBean(node))
            }
            return chain
        }
    }


    Question getQuestion(int questionIndex, int subQuestionIndex) {
        return this.getQuestions()[questionIndex][subQuestionIndex]
    }

    /**
     * Expectes JsonSluper's single node having Question object properties
     * @param jsonNode
     * @return Question Object From JsonSluper's node
     */
    def convertToQuestionBean(Object jsonNode) {
        new Question(
                question: jsonNode.question,
                altQuestions: jsonNode.alt_questions.collect { it } as String[],
                response: jsonNode.response,
                targetEntity: jsonNode.target_entity,
                targetField: jsonNode.target_field
        )
    }
}