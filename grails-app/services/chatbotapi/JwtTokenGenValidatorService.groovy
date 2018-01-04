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
                .withClaim(JwtTokenKeys.JobApplicationID.name(), userToken.jobApplicationId)
                .sign(this.jwtSignAlgorithm)
    }

    private DecodedJWT decodeToken(String token) {
        return jwtVerifier.verify(token);
    }

    UserToken parseUserToken(String token) {
        def decodedJWT = this.decodeToken(token)
        def userToken = new UserToken();
        userToken.jobApplicationId = decodedJWT.getClaims()[JwtTokenKeys.JobApplicationID.name()].asLong()
        return userToken
    }
}

