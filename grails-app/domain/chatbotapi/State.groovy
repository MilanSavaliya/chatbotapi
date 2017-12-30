package chatbotapi

class State {
    Integer id
    String name

    static belongsTo = [country: Country]
    static hasMany = [cities: City, locations: Location]

    static constraints = {
        name ( nullable: false )
    }
}
