package chatbotapi

class PersonalDetail {
    Long id
    String fullName
    String email
    String mobileNo
    String[] socialProfileLinks
    String shortSummary

    static belongsTo = [jobApplication: JobApplication, currentLocation: Location]
    static constraints = {
        email email: true, nullable: true
        mobileNo nullable: true
        socialProfileLinks nullable: true
        shortSummary nullable: true
    }
}