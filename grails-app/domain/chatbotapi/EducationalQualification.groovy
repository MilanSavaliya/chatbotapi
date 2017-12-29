package chatbotapi

class EducationalQualification {

    Integer id
    String degreeName
    String universityName
    String percentile
    Date startedAt
    Date completedAt

    JobApplication jobApplication
    Location location

    static constraints = {
    }
}
