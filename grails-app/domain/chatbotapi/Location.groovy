package chatbotapi

class Location {

    Integer id
    String address


    static hasMany = [
            personalDetails: PersonalDetail,
            educationQualifications: EducationalQualification
    ]
    static hasOne = [company: Company]
    static belongsTo = [city: City, state: State, country: Country]

    static constraints = {
        company(nullable: true)
    }
}
