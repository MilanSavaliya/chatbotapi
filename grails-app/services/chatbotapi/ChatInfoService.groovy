package chatbotapi

import grails.gorm.transactions.Transactional

@Transactional
class ChatInfoService {

    def save(ChatInfo chatInfo) {
        return chatInfo.save()
    }
}
