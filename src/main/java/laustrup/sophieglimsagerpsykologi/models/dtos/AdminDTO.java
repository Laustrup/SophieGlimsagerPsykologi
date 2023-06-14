package laustrup.sophieglimsagerpsykologi.models.dtos;

import laustrup.sophieglimsagerpsykologi.models.Admin;

import lombok.Getter;
import lombok.ToString;

@ToString
public class AdminDTO {

    @Getter
    private BookingDTO[] bookings;

    @Getter
    private FAQDTO[] faq;

    public AdminDTO(Admin admin) {
        bookings = new BookingDTO[admin.get_bookings().size()];
        for (int i = 0; i < bookings.length; i++)
            bookings[i] = new BookingDTO(admin.get_bookings().get(i));

        faq = new FAQDTO[admin.get_faq().size()];
        for (int i = 0; i < faq.length; i++)
            faq[i] = new FAQDTO(admin.get_faq().get(i));
    }

    public AdminDTO(BookingDTO[] bookings, FAQDTO[] faq) {
        this.bookings = bookings;
        this.faq = faq;
    }
}
