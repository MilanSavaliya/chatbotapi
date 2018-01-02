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

    private DecodedJWT decodeToken(String token) {
        return jwtVerifier.verify(token);
    }

    UserToken parseUserToken(String token) {
        def decodedJWT = this.decodeToken(token)
        def userToken = new UserToken();
        userToken.jSessionId = decodedJWT.getClaims()[JwtTokenKeys.JSessionID.name()].asString()
        userToken.jobApplicationId = decodedJWT.getClaims()[JwtTokenKeys.JobApplicationID.name()].asLong()
        userToken.currentQuestionListIndex = decodedJWT.getClaims()[JwtTokenKeys.CurrentQuestionIndex.name()].asInt()
        userToken.currentSubQuestionListIndex = decodedJWT.getClaims()[JwtTokenKeys.CurrentSubQuestionIndex.name()].asInt()

        return userToken
    }


    static void main(String[] args) {
        def tokenHelper = new JwtTokenGenValidatorService();
        def generatedToken = tokenHelper.generateToken(new UserToken(jSessionId: 'alksdjflkjsdflj123123', currentQuestionListIndex: 0, currentSubQuestionListIndex: 2, jobApplicationId: 1)) as String
        println generatedToken
        def userToken = tokenHelper.parseUserToken(generatedToken) as UserToken
        assert userToken.currentSubQuestionListIndex == 2
        assert userToken.currentQuestionListIndex == 0
        assert userToken.jSessionId == 'alksdjflkjsdflj123123'
        assert userToken.jobApplicationId == 1
    }
}

