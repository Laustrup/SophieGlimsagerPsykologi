package laustrup.sophieglimsagerpsykologi.service.entity_services;

import laustrup.sophieglimsagerpsykologi.models.Admin;
import laustrup.sophieglimsagerpsykologi.repositories.DbGate;
import laustrup.sophieglimsagerpsykologi.repositories.sub_reposities.AdminRepository;
import laustrup.sophieglimsagerpsykologi.repositories.sub_reposities.BookingRepository;
import laustrup.sophieglimsagerpsykologi.service.entity_services.persistence_services.BookingGather;

public class AdminService {

    private final AdminRepository _repository = new AdminRepository();
    private final BookingRepository _bookings = new BookingRepository();
    private final BookingGather _gather = new BookingGather();

    public Admin get(String password) {
        Admin admin = _repository.verify(password) ? new Admin(_gather.gather(_bookings.get())) : null;
        DbGate.get_instance().close();
        return admin;
    }
}
