package chatbotapi

class Experience {

    Integer id
    String title
    Date startedAt
    Date endedAt
    boolean currentlyWorking

    static belongsTo = [company: Company, jobApplication: JobApplication]
    static hasMany = [bulletPoints: ExperienceBullet]
    static constraints = {
        endedAt max: new Date()
    }
}
