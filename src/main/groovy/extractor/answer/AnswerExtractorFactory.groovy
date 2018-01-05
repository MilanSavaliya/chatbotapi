package extractor.answer

interface AnswerExtractorFactory {
    AnswerExtractor getAnswerExtractor()
    AnswerExtractorAdapter getAnswerExtractorAdapter()
}
