package chatbotapi

class ChatTokens {
    String token
    static belongsTo = [ jobApplication: JobApplication ]

    static constraints = {
        jobApplicationId( validator: { val, obj -> JobApplication.read(val) != null })
        token maxSize: 1000
    }
}
