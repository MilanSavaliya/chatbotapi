package chatbotapi

class Reference {

    Long id
    String name
    String designation
    String mobile
    String email

    static belongsTo = [currentCompany: Company]
    static constraints = {
        email ( email: true)
        currentCompany nullable: true
    }
}
