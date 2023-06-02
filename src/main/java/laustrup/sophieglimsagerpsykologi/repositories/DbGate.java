package laustrup.sophieglimsagerpsykologi.repositories;

import laustrup.sophieglimsagerpsykologi.Program;
import laustrup.utilities.console.Printer;
import laustrup.utilities.parameters.Plato;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbGate {

    /**
     * Singleton instance of the DbGate.
     */
    private static DbGate _instance = null;

    /**
     * Checks first if instance is null, otherwise will create a new instance of the object.
     * Created as a lazyfetch.
     * @return The instance of the object, as meant as a singleton.
     */
    public static DbGate get_instance() {
        if (_instance == null) _instance = new DbGate();
        return _instance;
    }

    /**
     * This is the only connection to the database, that is to be used.
     */
    @Getter
    private Connection _connection;

    private DbGate() {}

    /**
     * Will open connection.
     * @return True if the connection could open, otherwise false.
     */
    public boolean open() {
        if (_connection == null)
            return createConnection();
        else if (isClosed().get_truth())
            return createConnection();

        return false;
    }

    /**
     * Creates a connection from DriverManager.
     * @return True if the connection is open and haven't caught any exceptions.
     */
    private boolean createConnection() {
        DbLibrary library = DbLibrary.get_instance();

        try {
            _connection = DriverManager.getConnection(library.get_path(), library.get_user(), library.get_password());
            return isOpen().get_truth();
        } catch (SQLException e) {
            Printer.get_instance().print("Couldn't open connection...",e);
        }

        return false;
    }

    /**
     * Will close connection.
     * @return The success of the closing as a Plato. Will be undefined, if there is a SQLException and null if the connection is null.
     */
    public Plato close() {
        if (_connection != null) {
            try {
                if (isOpen().get_truth()) {
                    _connection.close();
                    return new Plato(true);
                }
            } catch (SQLException e) {
                Printer.get_instance().print("Couldn't close connection...",e);
                Plato plato = new Plato();
                plato.set_message("Couldn't close connection...");
                return plato;
            }
        }
        return null;
    }

    /**
     * Determine whether the connection is open.
     * @return True if it is open, false if it is closed.
     */
    public Plato isOpen() {
        return new Plato(!isClosed().get_truth());
    }

    /**
     * Determine whether the connection is closed.
     * @return True if it is closed, false if it is open.
     */
    public Plato isClosed() {
        try {
            return new Plato(_connection.isClosed());
        } catch (SQLException e) {
            Printer.get_instance().print("Trouble determine if the connection is closed...",e);
        }
        return new Plato();
    }

    /**
     * Determines whether the connection is null or not.
     * @return _connection == null;
     */
    public boolean connectionIsNull() {
        return _connection == null;
    }
}
