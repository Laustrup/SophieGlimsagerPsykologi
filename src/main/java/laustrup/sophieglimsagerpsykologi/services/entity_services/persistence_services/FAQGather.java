package laustrup.sophieglimsagerpsykologi.services.entity_services.persistence_services;

import laustrup.sophieglimsagerpsykologi.models.FAQ;
import laustrup.utilities.collections.lists.Liszt;
import laustrup.utilities.console.Printer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FAQGather {

    public Liszt<FAQ> gather(ResultSet set) {
        Liszt<FAQ> faq = new Liszt<>();

        try {
            while (set.next()) {
                faq.add(faq(set));
            }
        } catch (SQLException e) {
            Printer.get_instance().print("Issue when gathering FAQ...",e);
        }

        return faq;
    }

    private FAQ faq(ResultSet set) throws SQLException {
        return new FAQ(
                set.getString("question"),
                set.getString("answer")
        );
    }
}
