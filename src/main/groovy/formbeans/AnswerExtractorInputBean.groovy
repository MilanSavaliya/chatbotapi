package formbeans

import groovy.transform.Canonical

@Canonical
class AnswerExtractorInputBean {
    Question askedQuestion
    Answer givenAnswer
    ExtractedAnswer extractedAnswer
}
