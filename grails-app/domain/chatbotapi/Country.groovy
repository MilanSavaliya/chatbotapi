package chatbotapi

class Country {
    Integer id
    String shortname
    String name
    Integer phoneCode

    static hasMany = [states: State, locations: Location]

    static constraints = {
        phoneCode( min: 0 )
    }
}
