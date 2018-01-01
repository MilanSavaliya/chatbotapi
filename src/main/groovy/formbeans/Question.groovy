package formbeans

import groovy.transform.Canonical

@Canonical
class Question {
    String text;
    String jobApplicationId;
    QuestionType questionType
}
