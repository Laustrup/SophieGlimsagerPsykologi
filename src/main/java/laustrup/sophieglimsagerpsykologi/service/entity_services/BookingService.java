package laustrup.sophieglimsagerpsykologi.service.entity_services;

import laustrup.sophieglimsagerpsykologi.models.Booking;
import laustrup.sophieglimsagerpsykologi.repositories.DbGate;
import laustrup.sophieglimsagerpsykologi.repositories.sub_reposities.BookingRepository;
import laustrup.sophieglimsagerpsykologi.service.entity_services.persistence_services.BookingGather;
import laustrup.utilities.collections.lists.Liszt;

public class BookingService {

    private final BookingRepository _repository = new BookingRepository();
    private final BookingGather _gather = new BookingGather();

    public Booking upsert(Booking booking) {
        _repository.upsert(booking);
        booking = _gather.gather(_repository.get(booking.get_start(),booking.get_end())).getFirst();
        DbGate.get_instance().close();
        return booking;
    }

    public Liszt<Booking> getAppointments() {
        Liszt<Booking> appointments = _gather.gather(_repository.get(true));
        DbGate.get_instance().close();
        return appointments;
    }

    public Liszt<Booking> getAvailable() {
        Liszt<Booking> available = _gather.gather(_repository.get(false));
        DbGate.get_instance().close();
        return available;
    }

    public Liszt<Booking> get() {
        Liszt<Booking> bookings = _gather.gather(_repository.get());
        DbGate.get_instance().close();
        return bookings;
    }

    public boolean delete(Booking booking) {
        boolean success = _repository.delete(booking);
        DbGate.get_instance().close();
        return success;
    }
}
