package chatbotapi

class City {

    Integer id
    String name
    String short_code

    static belongsTo = [state: State]

    static constraints = {
    }
}
