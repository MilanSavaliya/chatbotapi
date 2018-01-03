package chatbotapi

import grails.gorm.transactions.Transactional

@Transactional
class ChatTokenDaoService {

    def save(ChatTokens chatTokens) {
        return chatTokens.save()
    }
}
