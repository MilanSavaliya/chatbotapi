package chatbotapi

class EducationalQualification {
    Integer id
    String degreeName
    String universityName
    String percentile
    Date startedAt
    Date completedAt

    static belongsTo = [jobApplication: JobApplication, location: Location]

    static constraints = {

        completedAt max: new Date()
    }
}
