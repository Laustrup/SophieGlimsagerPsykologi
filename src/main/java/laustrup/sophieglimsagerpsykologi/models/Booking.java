package laustrup.sophieglimsagerpsykologi.models;

import laustrup.sophieglimsagerpsykologi.models.dtos.BookingDTO;

import lombok.Getter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@ToString(of = {"_start","_end"})
public class Booking {

    @Getter
    private boolean _booked;

    @Getter
    private LocalDateTime _start, _end, _timestamp;

    @Getter
    private Subject _subject;

    @Getter
    private int _length;

    @Getter
    private String _title, _description;

    @Getter
    private Client _client;

    public Booking(BookingDTO dto) {
        this(dto.getStart(),dto.getEnd(),dto.getSubject(),new Client(dto.getClient()),
                dto.getTitle(), dto.getDescription(), dto.getTimestamp()
        );
    }

    public Booking(LocalDateTime start, LocalDateTime end, Subject subject,
                   Client client, String title, String description, LocalDateTime timestamp) {
        _start = start;
        _end = end;
        calculateLength();
        _subject = subject;
        _client = client;
        _title = title;
        _description = description;
        _booked = _client != null;
        _timestamp = timestamp;
    }

    public Booking(long id, LocalDateTime start, LocalDateTime end, Subject subject,
                   Client client, String title, String description) {
        _start = start;
        _end = end;
        calculateLength();
        _subject = subject;
        _client = client;
        _title = title;
        _description = description;
        _booked = false;
        _timestamp = LocalDateTime.now();
    }
    private int calculateLength() {
        _length = (int) Duration.between(_start,_end).toMinutes();
        return _length;
    }

    public enum Subject {
        STRESS,DEATH_MOANING,ANXIETY,DEPRESSION
    }
}
