package laustrup.sophieglimsagerpsykologi.models.dtos;

import laustrup.sophieglimsagerpsykologi.models.Admin;

import lombok.Getter;
import lombok.ToString;

@ToString
public class AdminDTO {

    @Getter
    private BookingDTO[] bookings;

    public AdminDTO(Admin admin) {
        bookings = new BookingDTO[admin.get_bookings().size()];
        for (int i = 0; i < admin.get_bookings().size(); i++)
            bookings[i] = new BookingDTO(admin.get_bookings().get(i));
    }

    public AdminDTO(BookingDTO[] bookings) {
        this.bookings = bookings;
    }
}
