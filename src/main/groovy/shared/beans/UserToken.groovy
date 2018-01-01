package shared.beans

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class UserToken {
    String jSessionId
    Long jobApplicationId
    int currentQuestionListIndex
    int currentSubQuestionListIndex
}
