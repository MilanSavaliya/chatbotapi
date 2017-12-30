package chatbotapi

class City {

    Integer id
    String name

    static belongsTo = [state: State]
    static hasMany = [locations: Location]

    static constraints = {
        name ( nullable: false )
    }
}
