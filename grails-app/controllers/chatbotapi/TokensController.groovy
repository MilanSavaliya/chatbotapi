package chatbotapi


import grails.rest.*
import grails.converters.*
import org.springframework.beans.factory.annotation.Autowired
import shared.beans.UserToken

class TokensController {
	static responseFormats = ['json']

    @Autowired
    JwtTokenGenValidatorService tokenGenValidatorService
	
    def save() {
//        def jobApplication = new JobApplication();
//        jobApplication.currentStatus = JobApplicationStatus.read(1)
//        tokenGenValidatorService.generateToken(
//                new UserToken(
//                        jSessionId: request.getSession(true),
//                        currentQuestionListIndex: 0,
//                        currentSubQuestionListIndex: 0,
//                        jobApplicationId:
//                )
//        )
    }
}
