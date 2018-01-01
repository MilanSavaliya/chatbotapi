package QuestionLinkedListGenerator

import groovy.json.JsonSlurper

class QuestionListGenerator {
    static void main(String[] args) {
        def inputStream = QuestionListGenerator.class.getResourceAsStream("/Questions.json")
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
    }
}
