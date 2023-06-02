package laustrup.sophieglimsagerpsykologi.models.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import laustrup.sophieglimsagerpsykologi.models.Client;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
public class ClientDTO {

    @Getter
    private long id;

    @Getter
    private String name, email;

    @Getter
    private LocalDate birthdate;

    @Getter
    private int age, phone;

    @Getter
    private Client.Consultation consultation;

    public ClientDTO(Client client) {
        this(client.get_id(), client.get_name(), client.get_email(), client.get_birthdate(), client.get_phone(), client.get_consultation());
        age = client.get_age();
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ClientDTO(long id, String name, String email, LocalDate birthdate, int phone, Client.Consultation consultation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.phone = phone;
        this.consultation = consultation;
    }
}
