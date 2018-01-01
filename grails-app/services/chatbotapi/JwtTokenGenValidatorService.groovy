package chatbotapi

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Autowired
import shared.beans.UserToken
import shared.enms.JwtTokenKeys

/**
 * This service will provide entry point to generate and validate issued tokens
 */
@Transactional
class JwtTokenGenValidatorService {

    @Autowired
    private Algorithm jwtSignAlgorithm
    @Autowired
    private JWTVerifier jwtVerifier

    String generateToken(
            UserToken userToken
    ) {
        return JWT.create()
                .withClaim(JwtTokenKeys.JSessionID.name(), userToken.jSessionId)
                .withClaim(JwtTokenKeys.JobApplicationID.name(), userToken.jobApplicationId)
                .withClaim(JwtTokenKeys.CurrentQuestionIndex.name(), userToken.currentQuestionListIndex)
                .withClaim(JwtTokenKeys.CurrentSubQuestionIndex.name(), userToken.currentSubQuestionListIndex).sign(this.jwtSignAlgorithm)
    }

    DecodedJWT decodeToken(String token) {
        return jwtVerifier.verify(token);
    }

    UserToken parseUserToken(DecodedJWT decodedJWT) {
        def userToken = new UserToken();
        userToken.jSessionId = decodedJWT.getClaims()[JwtTokenKeys.JSessionID.name()]
        userToken.jobApplicationId = decodedJWT.getClaims()[JwtTokenKeys.JobApplicationID.name()]
        userToken.currentQuestionListIndex = decodedJWT.getClaims()[JwtTokenKeys.CurrentQuestionIndex.name()]
        userToken.currentSubQuestionListIndex = decodedJWT.getClaims()[JwtTokenKeys.CurrentSubQuestionIndex.name()]
        return userToken
    }


}

