package chatbotapi

class JobApplication {

    Long id
    Date createdAt
    String applyingFor
    String currentDesignation
    String declaration


    JobApplicationStatus currentStatus

    static constraints = {
        declaration maxSize: 1000
    }
}
