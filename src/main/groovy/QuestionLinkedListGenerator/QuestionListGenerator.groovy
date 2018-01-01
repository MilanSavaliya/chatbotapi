package QuestionLinkedListGenerator

import groovy.json.JsonSlurper

class QuestionListGenerator {
    static void main(String[] args) {
        def inputStream = QuestionListGenerator.class.getResourceAsStream("/Questions.json")
        def parse = new JsonSlurper().parse(inputStream)
        def questionList = [] as LinkedList
        parse.each { node ->
            def counter = 1;
            def chain = [] as LinkedList
            chain.add( node )
            while(node.next != null){
                node = node.next
                chain.add(node)
            }

            println " "
            println "Starting List PRINTING"
            chain.each {
                println it.question
            }
            println "Ending List PRINTING"
        }
    }
}
