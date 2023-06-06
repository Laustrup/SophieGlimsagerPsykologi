package laustrup.sophieglimsagerpsykologi.repositories;

import laustrup.sophieglimsagerpsykologi.Defaults;
import laustrup.sophieglimsagerpsykologi.Program;
import laustrup.sophieglimsagerpsykologi.repositories.h2.H2Config;

import lombok.Getter;

/**
 * Contains necessarily values for the database and are able to configure the values only at startup.
 * Gathers some values from Defaults as standard values.
 * Extends H2Config, that configures the embedded database used for testing purposes.
 */
public class DbLibrary extends H2Config {

    boolean hasInitialized = false;


    /** Will allow a SQL statement to have multiple queries at once. */
    private String _allowMultipleQueries = Defaults.get_instance().get_sqlAllowMultipleQueries();

    /** The location of the database. */
    private String _location = Defaults.get_instance().get_dbLocation();

    /** The port of the database. */
    private int _port = Defaults.get_instance().get_dbPort();

    /** The schema that will be used of the database. */
    private String _schema = Defaults.get_instance().get_dbSchema();

    /**
     * Determines if this DbLibrary has been configured yet,
     * it can only configure the values, if they aren't already configured
     */
    private boolean _setupIsConfigured = false;

    /** Value for the DbGate with the purpose of creating a connection. */
    private String _path = get_path();

    public String get_path() {
        _path = set_path();
        return _path;
    }

    @Getter
    private String _user = Program.get_instance().get_state().equals(Program.State.TESTING) ? "sa" : Defaults.get_instance().get_dbUser();
    @Getter
    private String _password = Program.get_instance().get_state().equals(Program.State.TESTING) ? "" : Defaults.get_instance().get_dbPassword();
    @Getter
    private final String _driverClassName = Program.get_instance().get_state().equals(Program.State.TESTING) ? "org.h2.Driver" : "com.mysql.cj.jdbc.Driver";

    /**
     * Will change the fields of crating a connection for the database,
     * but only in case they haven't already been configured.
     * If fields are null, empty or integers are 0, they will not change the values.
     * @param location The location of the database.
     * @param port The port used for the schema.
     * @param schema The schema that is wished to use.
     * @param allowMultipleQueries If true, it will allow a single SQL statement
     *                             to be able to run multiple queries at once.
     * @param user The user for the database, that has rules for uses of the database.
     * @param password The password to insure the User has access permitted for the database.
     * @return A statement of the fields that has been updated,
     * if the configuration is not allowed, it will return that it wasn't.
     */
    public String changeSetup(String location, int port, String schema,
                              boolean allowMultipleQueries,
                              String user, String password) {
        boolean changeLocation = !(location == null || location.isEmpty()),
                changePort = port > 0,
                changeSchema = !(schema == null || schema.isEmpty()),
                changeUser = !(user == null || user.isEmpty()),
                changePassword = !(password == null || password.isEmpty()),
                allowConfiguration = !_setupIsConfigured;

        if (allowConfiguration) {
            _location = changeLocation ?  location : _location;
            _port = changePort ? port : _port;
            _schema = changeSchema ? schema : _schema;
            _allowMultipleQueries = !allowMultipleQueries ? "" : _allowMultipleQueries;
            _user = changeUser ? user : _user;
            _password = changePassword ? password : _password;
        }

        set_path();
        _setupIsConfigured = true;

        if (allowConfiguration) {
            String fields = (changeLocation ? "Location\n" : "") +
                    (changePort ? "Port\n" : "") +
                    (changeSchema ? "Schema\n" : "") +
                    (!allowMultipleQueries ? "Will not allow multiple queries\n" : "") +
                    (changeUser ? "User\n" : "") +
                    (changePassword ? "Password\n" : "");

            return "\tFields that has been successfully changed are:\n\n" + fields + "\n";
        }
        else
            return "\tConfigurations were not allowed...";
    }


    /**
     * Collects a string of a path to the database from the necessarily fields needed,
     * therefore the path should be set after location, port, schema and allow multiple queries.
     * Will generate or reset the reset_db.sql and it will be composed by the boilerplate.sql and default_values.sql.
     * @return The collected string.
     */
    public String set_path() {
        boolean doInitializeH2Script = Program.get_instance().get_state().equals(Program.State.TESTING) && !hasInitialized;
        if (doInitializeH2Script && !Program.get_instance().is_applicationIsRunning())
            h2InitScript();

        return "jdbc:h2:mem:TESTING;DB_CLOSE_DELAY=-1;mode=MySQL" +
                (doInitializeH2Script && !Program.get_instance().is_applicationIsRunning()
                        ? ";" + h2InitRunScript() : ";SCHEMA=\"" + Defaults.get_instance().get_dbSchema() + "\"");
                        //"mysql://" + _location + ":" + _port + "/" + _schema + _allowMultipleQueries);
    }

    /**
     * If at startup there isn't a wish for a different setup, this will use the default setup.
     * @return A message describing the setup is default.
     */
    public String defaultSetup() {
        _setupIsConfigured = true;
        return "Setup is default!";
    }

    public static DbLibrary _instance = null;

    /**
     * Lazy fetch of getting the Crate instance.
     * If the instance is null, it will use a private constructor to initialise the instance.
     * @return The static instance of the Crate.
     */
    public static DbLibrary get_instance() {
        if (_instance == null) { _instance = new DbLibrary(); }
        return _instance;
    }

    private DbLibrary() {}
}
