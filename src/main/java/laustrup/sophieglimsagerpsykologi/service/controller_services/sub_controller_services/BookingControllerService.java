package laustrup.sophieglimsagerpsykologi.service.controller_services.sub_controller_services;

import laustrup.sophieglimsagerpsykologi.models.Booking;
import laustrup.sophieglimsagerpsykologi.models.dtos.BookingDTO;
import laustrup.sophieglimsagerpsykologi.service.controller_services.ControllerService;
import laustrup.sophieglimsagerpsykologi.service.entity_services.BookingService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BookingControllerService extends ControllerService<BookingDTO> {

    private final BookingService _service = new BookingService();

    public ResponseEntity<BookingDTO> upsert(Booking booking) {
        booking = _service.upsert(booking);
        return booking != null ? entityContent(new BookingDTO(booking)) : entityContent((BookingDTO) null);
    }

    public ResponseEntity<BookingDTO[]> get(boolean isBooked) {
        return convert(isBooked
                ? _service.getAppointments()
                : _service.getAvailable()
        );
    }

    public ResponseEntity<BookingDTO[]> get() {
        return convert(_service.get());
    }

    public ResponseEntity<Boolean> delete(Booking booking) {
        return new ResponseEntity<>(_service.delete(booking), HttpStatus.OK);
    }
}
