package laustrup.sophieglimsagerpsykologi.models;

import laustrup.utilities.collections.lists.Liszt;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Admin {

    @Getter
    private Liszt<Booking> _bookings;

    public Admin(Liszt<Booking> bookings) {
        _bookings = bookings;
    }
}
