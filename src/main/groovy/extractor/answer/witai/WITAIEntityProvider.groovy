package extractor.answer.witai

final class WITAIEntityProvider {
    private static entityMap = [
            'PersonalDetail.fullName'    : '[ { name="wit$contact", "id": "535a8111-9b01-4678-80ae-53a22ef0bf5b", "values": [] } ] ',
            'PersonalDetail.email'       : '[ { name="wit$email", "id": "535a8107-7041-417d-b500-670b30d5267e", "values": [] } ]',
            'PersonalDetail.mobileNo'    : '[ { name="wit$phone_number", "id": "535a80e2-6fc8-4991-b707-50dd0c862dfb", "values": [] } ]',
            'PersonalDetail.shortSummary': '[ { name="shortsummary", "id": "5a530b2b-608e-4539-a2a6-76bc06706972", "values": [] } ]',
    ]


    static String getEntityToUse(String key) {
        return entityMap.get(key)
    }
}

