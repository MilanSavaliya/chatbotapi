package shared.beans

import chatbotapi.JwtTokenGenValidatorService
import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class UserToken {
    Long jobApplicationId
}