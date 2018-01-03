package exceptions.runtime

import grails.rest.Link

class ChatbotAPIExceptionImpl extends RuntimeException implements ChatbotAPIException {
    Integer responseCode
    String extraInformation
    Link linkTodoc

    ChatbotAPIExceptionImpl(
            String message,
            Integer responseCode,
            String extraInformation,
            Link linkToDoc
    ) {
        super(message)
        this.responseCode = responseCode
        this.extraInformation = extraInformation
        this.linkTodoc = linkToDoc
    }

    ChatbotAPIExceptionImpl(RuntimeException runtimeException, Integer responseCode, String extraInfo, Link linkToDoc) {
        this(runtimeException.getMessage(), responseCode, extraInfo, linkToDoc)
    }

    ChatbotAPIExceptionImpl(RuntimeException runtimeException, Integer responseCode) {
        this(runtimeException.getMessage(), responseCode, null, null)
    }

    @Override
    int getResponseCode() {
        return this.responseCode
    }

    @Override
    String getExtraInformation() {
        return this.extraInformation
    }

    @Override
    Link getLinkToTheDocumentation() {
        return this.linkTodoc
    }
}
