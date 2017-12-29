package chatbotapi

class JobApplicationStatus {

    Short id
    String name
    String description

    static constraints = {
        description nullable: true
        name (nullable: false, size: 1..15)
    }
}
