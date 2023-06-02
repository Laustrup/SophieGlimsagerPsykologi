package laustrup.sophieglimsagerpsykologi.models;

import laustrup.sophieglimsagerpsykologi.models.dtos.ClientDTO;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;

@ToString
public class Client {

    @Getter
    private long _id;

    @Getter
    private String _name, _email;

    @Getter
    private LocalDate _birthdate;

    @Getter
    private int _age, _phone;

    @Getter
    private Consultation _consultation;

    public Client(ClientDTO dto) {
        this(dto.getId(), dto.getName(), dto.getEmail(), dto.getBirthdate(), dto.getPhone(), dto.getConsultation());
    }

    public Client(long id, String name, String email, LocalDate birthdate, int phone, Consultation consultation) {
        _id = id;
        _name = name;
        _email = email;
        _birthdate = birthdate;
        calculateAge();
        _phone = phone;
        _consultation = consultation;
    }

    public Client(String name, String email, LocalDate birthdate, int phone, Consultation consultation) {
        _name = name;
        _email = email;
        _birthdate = birthdate;
        calculateAge();
        _phone = phone;
        _consultation = consultation;
    }

    private int calculateAge() {
        _age = (int) (Period.between(_birthdate,LocalDate.now()).toTotalMonths() / 12);
        return _age;
    }

    public enum Consultation {
        ONLINE,PHONE,PHYSICAL
    }
}
