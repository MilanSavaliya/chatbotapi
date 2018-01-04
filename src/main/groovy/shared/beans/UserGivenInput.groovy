package shared.beans

import grails.validation.Validateable
import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class UserGivenInput implements Validateable{
    int lastQuestionIndex
    int lastSubQuestionIndex
    String userGivenAnswer

    static constraints = {

    }
}
