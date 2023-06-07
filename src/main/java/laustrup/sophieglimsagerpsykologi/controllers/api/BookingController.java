package laustrup.sophieglimsagerpsykologi.controllers.api;

import laustrup.sophieglimsagerpsykologi.models.Booking;
import laustrup.sophieglimsagerpsykologi.models.dtos.BookingDTO;
import laustrup.sophieglimsagerpsykologi.service.controller_services.sub_controller_services.BookingControllerService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    private final String _endpointDirectory = "/api/booking/";

    private final BookingControllerService _service = new BookingControllerService();

    @PostMapping(value = _endpointDirectory + "upsert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingDTO> upsert(@RequestBody BookingDTO booking) {
        return _service.upsert(new Booking(booking));
    }

    @GetMapping(value = _endpointDirectory + "get/appointments")
    public ResponseEntity<BookingDTO[]> getAppointments() {
        return _service.get(true);
    }

    @GetMapping(value = _endpointDirectory + "get/available")
    public ResponseEntity<BookingDTO[]> getAvailable() {
        return _service.get(false);
    }

    @GetMapping(value = _endpointDirectory + "get")
    public ResponseEntity<BookingDTO[]> get() {
        return _service.get();
    }

    @DeleteMapping(value = _endpointDirectory + "delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingDTO[]> delete(@RequestBody BookingDTO booking) {
        return _service.delete(new Booking(booking));
    }
}
