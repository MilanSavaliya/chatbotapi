package chatbotapi

class Company {

    Integer id
    String name
    String description

    static hasMany = [experiences: Experience, references: Reference]

    static belongsTo = [location: Location]
    static constraints = {
        description(nullable: true)
    }
}
