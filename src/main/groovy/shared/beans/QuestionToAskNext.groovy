package shared.beans

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class QuestionToAskNext {
    Question question
    int currentQuestionIndex
    int currentSubQuestionIndex
    UserGivenInput lastUserGivenInput
}
