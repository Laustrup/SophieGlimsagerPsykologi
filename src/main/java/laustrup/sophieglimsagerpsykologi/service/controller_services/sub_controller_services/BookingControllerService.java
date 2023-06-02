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
        return entityContent(new BookingDTO(_service.upsert(booking)));
    }

    public ResponseEntity<BookingDTO[]> get(boolean isBooked) {
        return entityContent(isBooked
            ? toDTOs(_service.getAppointments().get_data())
            : toDTOs(_service.getAvailable().get_data())
        );
    }

    public ResponseEntity<BookingDTO[]> get() {
        return entityContent(toDTOs(_service.get().get_data()));
    }

    public ResponseEntity<Boolean> delete(Booking booking) {
        boolean isDeleted = _service.delete(booking);
        return new ResponseEntity<>(isDeleted, isDeleted ? HttpStatus.OK : HttpStatus.valueOf(500));
    }
}
