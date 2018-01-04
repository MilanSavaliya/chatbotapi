package chatbotapi

class ChatInfo {
    int currentQuestionIndex = 0
    int currentSubQuestionIndex = 0
    String token
    static belongsTo = [ jobApplication: JobApplication ]

    static constraints = {
        jobApplicationId( validator: { val, obj -> JobApplication.read(val) != null })
        token type: "text", nullable: true
        currentQuestionIndex min: 0
        currentSubQuestionIndex min: 0
    }
}
