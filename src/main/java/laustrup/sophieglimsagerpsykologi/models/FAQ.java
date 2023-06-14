package laustrup.sophieglimsagerpsykologi.models;

import laustrup.sophieglimsagerpsykologi.models.dtos.FAQDTO;

import lombok.Getter;

public class FAQ {

    @Getter
    private String _question;

    @Getter
    private String _answer;

    public FAQ(FAQDTO dto) {
        this(dto.getQuestion(), dto.getAnswer());
    }

    public FAQ(String question, String answer) {
        _question = question;
        _answer = answer;
    }
}
