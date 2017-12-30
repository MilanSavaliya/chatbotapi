package chatbotapi

class Skill {

    Long id
    String name
    String description

    static belongsTo = [skillType: SkillType, jobApplication: JobApplication]
    static constraints = {
    }
}
