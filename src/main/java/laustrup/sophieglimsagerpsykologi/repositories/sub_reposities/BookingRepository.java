package laustrup.sophieglimsagerpsykologi.repositories.sub_reposities;

import laustrup.sophieglimsagerpsykologi.models.Booking;
import laustrup.sophieglimsagerpsykologi.repositories.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;

public class BookingRepository extends Repository {

    public void upsert(Booking booking) {
        boolean hasClient = booking.get_client() != null && booking.get_client().get_id() > 0;

        edit(
            (hasClient ?
                "INSERT INTO clients(" +
                    "id," +
                    "`name`," +
                    "`email`," +
                    "birthdate," +
                    "consultation" +
                ") " +
                "VALUES(" +
                    booking.get_client().get_id() + ",'" +
                    booking.get_client().get_name() + "','" +
                    booking.get_client().get_email() + "','" +
                    booking.get_client().get_birthdate().toString() + "'" +
                ") ON DUPLICATE KEY UPDATE " +
                    "`name` = '" + booking.get_client().get_name() + "'," +
                    "`email` = '" + booking.get_client().get_email() + "'," +
                    "birthdate = '" + booking.get_client().get_birthdate().toString() + "'," +
                    "consultation = '" + booking.get_client().get_consultation() + "';"
            : "") +
            "INSERT INTO bookings(" +
                (hasClient ? "client_id," : "") +
                "`start`," +
                "`end`," +
                "`subject`," +
                "title," +
                "`description`," +
                "`timestamp`" +
            ") " +
            "VALUES(" +
                (hasClient ? booking.get_client().get_id() + ",'" : "'") +
                booking.get_start() + "','" +
                booking.get_end() + "','" +
                booking.get_subject() + "','" +
                booking.get_title() + "','" +
                booking.get_description() + "'," +
                "NOW()" +
            ") " +
            "ON DUPLICATE KEY UPDATE " +
                "client_id = " + (hasClient ? booking.get_client().get_id() : "NULL") + ", " +
                "`start` = '" + booking.get_start().toString() + "', " +
                "`end` = '" + booking.get_end().toString() + "', " +
                "`subject` = '" + booking.get_subject() + "', " +
                "title = '" + booking.get_title() + "', " +
                "`description` = '" + booking.get_description() + "'" +
            "; "
        );
    }

    public ResultSet get(LocalDateTime start, LocalDateTime end) {
        return get("WHERE `start` = '" + start.toString() + "' AND `end` = '" + end.toString() + "'");
    }

    public ResultSet get() { return get(new String()); }

    public ResultSet get(boolean isBooked) {
        return get("WHERE client_id IS " + (isBooked ? "NOT " : " ") + "NULL AND `end` > NOW()");
    }

    private ResultSet get(String where) {
        return read("SELECT * FROM bookings LEFT JOIN ON bookings.client_id = clients.id " + where + ";");
    }

    public boolean delete(Booking booking) {
        return edit("DELETE FROM bookings WHERE `start` = '" + booking.get_start().toString() + "' AND `end` = '" + booking.get_end().toString() + "';");
    }
}
