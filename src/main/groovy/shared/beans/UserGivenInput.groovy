package shared.beans

import grails.validation.Validateable
import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class UserGivenInput{
    int lastQuestionIndex
    int lastSubQuestionIndex
    String userGivenAnswer

    static constraints = {

    }
}
