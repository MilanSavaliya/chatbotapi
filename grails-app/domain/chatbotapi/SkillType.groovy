package chatbotapi

class SkillType {

    Short id
    String name
    String description

    static hasMany = [skills: Skill]

    static constraints = {
        description(nullable: false)
    }
}
