package shared.beans

import groovy.transform.Canonical

@Canonical
class Answer {
    String text
    Question question
}