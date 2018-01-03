package exceptions.runtime

import grails.rest.Link

interface ChatbotAPIException {
    int getResponseCode();
    String getExtraInformation();
    Link getLinkToTheDocumentation()
    String getMessage()
}
