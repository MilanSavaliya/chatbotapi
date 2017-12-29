package chatbotapi

class PersonalDetail {

    Long id
    String fullName
    String email
    String mobileNo
    String[] socialProfileLinks
    String shortSummary


    JobApplication jobApplication
    Location location

    static constraints = {
        email (email: true)
    }
}
