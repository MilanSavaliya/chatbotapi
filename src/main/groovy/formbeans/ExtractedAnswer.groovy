package formbeans

import groovy.transform.Canonical

@Canonical
class ExtractedAnswer {
    Question askedQuestion
    Answer givenAnswer
    String extractedText
}
