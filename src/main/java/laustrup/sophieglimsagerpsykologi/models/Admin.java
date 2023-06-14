package laustrup.sophieglimsagerpsykologi.models;

import laustrup.sophieglimsagerpsykologi.models.dtos.AdminDTO;
import laustrup.sophieglimsagerpsykologi.models.dtos.BookingDTO;
import laustrup.sophieglimsagerpsykologi.models.dtos.FAQDTO;
import laustrup.utilities.collections.lists.Liszt;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Admin {

    @Getter
    private Liszt<Booking> _bookings = new Liszt<>();

    @Getter
    private Liszt<FAQ> _faq;

    public Admin(AdminDTO dto) {
        for (BookingDTO booking : dto.getBookings())
            _bookings.add(new Booking(booking));

        for (FAQDTO faq : dto.getFaq())
            _faq.add(new FAQ(faq));
    }

    public Admin(Liszt<Booking> bookings, Liszt<FAQ> faq) {
        _bookings = bookings;
        _faq = faq;
    }
}
