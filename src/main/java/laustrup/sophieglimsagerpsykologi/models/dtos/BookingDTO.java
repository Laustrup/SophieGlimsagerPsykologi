package laustrup.sophieglimsagerpsykologi.models.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import laustrup.sophieglimsagerpsykologi.models.Booking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString @NoArgsConstructor
public class BookingDTO {

    @Getter
    private boolean booked;

    @Getter
    private LocalDateTime start, end, timestamp;

    @Getter
    private Booking.Subject subject;

    @Getter
    private int length;

    @Getter
    private String title, description;

    @Getter
    private ClientDTO client;

    public BookingDTO(Booking booking) {
        this(booking.get_start(),booking.get_end(),booking.get_subject(), new ClientDTO(booking.get_client()),
                booking.get_title(), booking.get_description(), booking.get_timestamp()
        );
        booked = booking.is_booked();
        length = booking.get_length();
    }

    public BookingDTO(LocalDateTime start, LocalDateTime end, Booking.Subject subject, ClientDTO client,
                      String title, String description, LocalDateTime timestamp) {
        this.start = start;
        this.end = end;
        this.subject = subject;
        this.client = client;
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BookingDTO(LocalDateTime start, LocalDateTime end, Booking.Subject subject,
                      ClientDTO client, String title, String description) {
        this.start = start;
        this.end = end;
        this.subject = subject;
        this.client = client;
        this.title = title;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }
}
