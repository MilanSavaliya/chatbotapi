package chatbotapi

class Projects {
    Long id
    String projectName
    String shortSummary
    String roleBreakdown
    String[] techStackUsed

    static belongTo = [jobApplication: JobApplication]

    static constraints = {
        shortSummary(maxSize: 1000) /*TO make this TEXT*/
    }
}