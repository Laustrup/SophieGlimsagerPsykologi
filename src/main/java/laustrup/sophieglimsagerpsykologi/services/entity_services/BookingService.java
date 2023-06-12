package laustrup.sophieglimsagerpsykologi.services.entity_services;

import laustrup.sophieglimsagerpsykologi.models.Booking;
import laustrup.sophieglimsagerpsykologi.repositories.DbGate;
import laustrup.sophieglimsagerpsykologi.repositories.sub_reposities.BookingRepository;
import laustrup.sophieglimsagerpsykologi.services.entity_services.persistence_services.BookingGather;
import laustrup.utilities.collections.lists.Liszt;

import java.sql.ResultSet;

public class BookingService {

    private final BookingRepository _repository = new BookingRepository();
    private final BookingGather _gather = new BookingGather();

    public Booking upsert(Booking booking) {
        _repository.upsert(booking);
        ResultSet set = _repository.get(booking.get_start(),booking.get_end());
        booking = set != null ? _gather.gather(set).getFirst() : null;
        DbGate.get_instance().close();
        return booking;
    }

    public Liszt<Booking> getAppointments() {
        ResultSet set = _repository.get(true);
        Liszt<Booking> appointments = set != null ? _gather.gather(set) : null;
        DbGate.get_instance().close();
        return appointments;
    }

    public Liszt<Booking> getAvailable() {
        ResultSet set = _repository.get(false);
        Liszt<Booking> available = set != null ? _gather.gather(set) : null;
        DbGate.get_instance().close();
        return available;
    }

    public Liszt<Booking> get() {
        ResultSet set = _repository.get();
        Liszt<Booking> bookings = set != null ? _gather.gather(set) : null;
        DbGate.get_instance().close();
        return bookings;
    }

    public boolean delete(Booking booking) {
        boolean success = _repository.delete(booking);
        if (success)
            DbGate.get_instance().close();

        return success;
    }
}
