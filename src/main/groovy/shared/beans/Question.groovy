package shared.beans

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class Question {
    String question;
    String[] altQuestions;
    String response
    String targetEntity
    String targetField
}
