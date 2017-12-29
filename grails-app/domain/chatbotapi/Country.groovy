package chatbotapi

class Country {

    Integer id
    String name
    String short_code

    static hasMany = [states: State]

    static constraints = {

    }
}
