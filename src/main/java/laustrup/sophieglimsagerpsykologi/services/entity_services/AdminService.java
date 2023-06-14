package laustrup.sophieglimsagerpsykologi.services.entity_services;

import laustrup.sophieglimsagerpsykologi.models.Admin;
import laustrup.sophieglimsagerpsykologi.models.FAQ;
import laustrup.sophieglimsagerpsykologi.repositories.DbGate;
import laustrup.sophieglimsagerpsykologi.repositories.sub_reposities.AdminRepository;
import laustrup.sophieglimsagerpsykologi.repositories.sub_reposities.BookingRepository;
import laustrup.sophieglimsagerpsykologi.services.entity_services.persistence_services.BookingGather;
import laustrup.sophieglimsagerpsykologi.services.entity_services.persistence_services.FAQGather;
import laustrup.utilities.collections.lists.Liszt;

public class AdminService {

    private final AdminRepository _repository = new AdminRepository();
    private final BookingRepository _bookings = new BookingRepository();
    private final BookingGather _bookingGather = new BookingGather();
    private final FAQGather _FAQGather = new FAQGather();

    public Admin get(String password) {
        Admin admin = _repository.verify(password) ? new Admin(_bookingGather.gather(_bookings.get()),_FAQGather.gather(_repository.get())) : null;
        DbGate.get_instance().close();
        return admin;
    }

    public Liszt<FAQ> insert(FAQ faq) {
        _repository.insert(faq);
        return getFAQ();
    }

    public Liszt<FAQ> getFAQ() {
        Liszt<FAQ> faqs = _FAQGather.gather(_repository.get());
        DbGate.get_instance().close();
        return faqs;
    }

    public Liszt<FAQ> deleteFAQ(FAQ faq) {
        if (_repository.delete(faq))
            return getFAQ();
        else {
            DbGate.get_instance().close();
            return null;
        }
    }
}
