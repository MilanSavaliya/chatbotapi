package chatbotapi

class Experience {

    Integer id
    String title
    Date startedAt
    Date endedAt
    boolean currentlyWorking


    Company company
    JobApplication jobApplication

    static constraints = {
    }
}
