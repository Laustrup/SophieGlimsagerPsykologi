package laustrup.sophieglimsagerpsykologi.repositories.sub_reposities;

import laustrup.sophieglimsagerpsykologi.repositories.Repository;

import java.sql.SQLException;

public class AdminRepository extends Repository {

    public boolean verify(String login) {
        try {
            return read("SELECT * FROM admin WHERE login = '" + login + "';").next();
        } catch (SQLException e) {
            return false;
        }
    }
}
