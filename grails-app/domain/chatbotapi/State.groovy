package chatbotapi

class State {

    Integer id
    String name
    String short_code

    static belongsTo = [country: Country]
    static hasMany = [cities: City]

    static constraints = {

    }
}
