package chatbotapi

class Projects {

    Long id
    String projectName
    String shortSummary
    String roleBreakdown
    String[] techStackUsed

    JobApplication jobApplication

    static constraints = {
        shortSummary ( maxSize: 1000 ) /* TO make this TEXT */
    }
}
