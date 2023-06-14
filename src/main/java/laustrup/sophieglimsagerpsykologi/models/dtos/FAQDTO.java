package laustrup.sophieglimsagerpsykologi.models.dtos;

import laustrup.sophieglimsagerpsykologi.models.FAQ;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;

public class FAQDTO {

    @Getter
    private String question;

    @Getter
    private String answer;

    public FAQDTO(FAQ faq) {
        this(faq.get_question(), faq.get_answer());
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FAQDTO(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
