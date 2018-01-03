package chatbotapi

class JobApplication {
    Long id
    Date createdAt
    String applyingFor
    String currentDesignation
    String declaration

    static hasOne = [personalDetail: PersonalDetail]
    static belongsTo = [currentStatus: JobApplicationStatus]
    static hasMany = [
            skills                 : Skill,
            educationQualifications: EducationalQualification,
            projects               : Projects,
            experiences            : Experience,
            chatTokens             : ChatTokens
    ]

    static constraints = {
        declaration(nullable: true, maxSize: 1000)
        currentDesignation(nullable: true)
        applyingFor(nullable: true)
        createdAt(max: new Date() + 1)
        personalDetail(nullable: true)
    }
}