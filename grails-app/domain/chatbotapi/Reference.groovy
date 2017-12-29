package chatbotapi

class Reference {

    Long id
    String name
    String designation
    String mobile
    String email


    Company currentCompany

    static constraints = {

        email ( email: true)
    }
}
