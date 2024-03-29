package laustrup.sophieglimsagerpsykologi.services.entity_services.persistence_services;

import laustrup.sophieglimsagerpsykologi.models.Booking;
import laustrup.sophieglimsagerpsykologi.models.Client;
import laustrup.sophieglimsagerpsykologi.models.FAQ;
import laustrup.utilities.collections.lists.Liszt;
import laustrup.utilities.console.Printer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Supplier;

public class BookingGather {

    public Liszt<Booking> gather(ResultSet set) {
        Liszt<Booking> bookings = new Liszt<>();

        if (set != null) {
            try {
                while (set.next()) {
                    Booking booking = booking(set);
                    if (booking != null && !bookings.contains(booking))
                        bookings.add(booking);
                }
            }
            catch (SQLException e) {
                Printer.get_instance().print("Exception in next() method when constructing bookings...",e);
                return null;
            }
        }
        else
            return null;

        return bookings;
    }

    private Booking booking(ResultSet set) {
        try {
            return new Booking(
                set.getTimestamp("start").toLocalDateTime(),
                set.getTimestamp("end").toLocalDateTime(),
                (Booking.Subject) ifNotNull(set.getString("subject"), () -> {
                    try {
                        return Booking.Subject.valueOf(set.getString("subject"));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }),
                client(set),
                set.getString("title"),
                set.getString("description"),
                set.getTimestamp("timestamp").toLocalDateTime()
            );
        } catch (SQLException e) {
            try {
                Printer.get_instance().print("Couldn't construct " + set.getString("title") + "...",e);
            } catch (SQLException ex) {
                Printer.get_instance().print("Couldn't construct a booking from resultset...", e);
            }
        }

        return null;
    }

    private Client client(ResultSet set) throws SQLException {
        if (set.getLong("id") == 0)
            return null;

        return new Client(
            set.getLong("id"),
            set.getString("name"),
            set.getString("email"),
            set.getTimestamp("birthdate").toLocalDateTime().toLocalDate(),
            set.getInt("phone"),
            Client.Consultation.valueOf(set.getString("consultation"))
        );
    }

    private Object ifNotNull(Object input, Supplier<Object> supplier) {
        try {
            return input != null ? supplier.get() : null;
        } catch (Exception e) {
            return null;
        }
    }
}

