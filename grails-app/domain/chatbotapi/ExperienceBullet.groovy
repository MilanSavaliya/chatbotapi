package chatbotapi

class ExperienceBullet {

    Long id
    String bulletPoint

    static belongsTo = [experience: Experience]
    static constraints = {
        bulletPoint maxSize: 1000
    }
}
