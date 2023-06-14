package laustrup.sophieglimsagerpsykologi.repositories.sub_reposities;

import laustrup.sophieglimsagerpsykologi.models.FAQ;
import laustrup.sophieglimsagerpsykologi.repositories.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository extends Repository {

    public boolean verify(String login) {
        try {
            return read("SELECT * FROM admin WHERE login = '" + login + "';").next();
        } catch (SQLException e) {
            return false;
        }
    }

    public void insert(FAQ faq) {
        edit(
            "INSERT IGNORE INTO faq(`question`,`answer`) VALUES ('" + faq.get_question() + "','" + faq.get_answer() + "');"
        );
    }

    public ResultSet get() {
        return read("SELECT * FROM faq;");
    }

    public boolean delete(FAQ faq) {
        return edit("DELETE FROM faq WHERE `question` = '" + faq.get_question() + "' AND `answer` = '" + faq.get_answer() + "';");
    }
}
