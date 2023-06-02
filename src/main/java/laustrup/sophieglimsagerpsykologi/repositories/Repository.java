package laustrup.sophieglimsagerpsykologi.repositories;

import laustrup.sophieglimsagerpsykologi.Program;
import laustrup.utilities.console.Printer;
import laustrup.utilities.parameters.Plato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An abstract class that takes SQLs and performs them through JDBC methods.
 * Are also including a private class for handling database connections.
 * The database connection that is being handled is a connection specifically for this class.
 */
public abstract class Repository extends DbTranslator {

    /**
     * The connector that is used to handle the database connection of this Repository.
     */
    private final DBConnector _connector = new DBConnector();

    /**
     * Opens the connection, if it isn't already open.
     * Is used for reading database executions for actions such as selecting/getting.
     * Is not used for creating changes to the database.
     * Uses the executeQuery() of PreparedStatement to execute a specified SQL statement.
     * Will NOT automatically close connection.
     * @param sql The specified SQL statement, that specifies the action intended for the database.
     * @return The ResultSet gathered from the PreparedStatement, if something unexpected happened, it returns null.
     */
    protected ResultSet read(String sql) {
        handleConnection();
        try {
            return connection().prepareStatement(handleSQL(sql)).executeQuery();
        } catch (SQLException e) {
            Printer.get_instance().print("-- Couldn't read sql statement...\n\n" + sql,e);
        }
        return null;
    }

    /**
     * Opens the connection, if it isn't already open.
     * Are used for making changes such as Update and Delete to the database.
     * Uses the executeUpdate() of PreparedStatement to execute a specified SQL statement.
     * Can automatically close connection.
     * @param sql The specified SQL statement, that specifies the action intended for the database.
     * @return The boolean answer of the database success.
     */
    protected boolean edit(String sql) {
        handleConnection();
        try {
            return connection().prepareStatement(handleSQL(sql)).executeUpdate() > 0;
        } catch (SQLException e) {
            Printer.get_instance().print("Couldn't execute update...\n\n" + sql ,e);
        }
        return false;
    }

    /**
     * Opens the connection, if it isn't already open.
     * Are used for making changes such as Create to the database.
     * Uses the executeUpdate() of PreparedStatement to execute a specified SQL statement.
     * Will NOT automatically close connection.
     * @param sql The specified SQL statement, that specifies the action intended for the database.
     * @return The PreparedStatement that is executed with the GENERATED KEY.
     */
    protected PreparedStatement create(String sql) {
        try {
            PreparedStatement statement = handleConnection().prepareStatement(
                    handleSQL(sql),
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            statement.executeUpdate();
            return statement;
        } catch (SQLException e) {
            Printer.get_instance().print("Couldn't execute create...\n\n" + sql,e);
        }

        return null;
    }

    /**
     * In case the program is running in testing mode, it will try and translate the MySQL into H" syntax.
     * @param sql The SQL query that will be executed.
     * @return The fixed SQL.
     */
    private String handleSQL(String sql) {
        return Program.get_instance().get_state().equals(Program.State.TESTING)
                ? DbLibrary.get_instance().translateMySQLIntoH2(sql)
                : sql;
    }

    /**
     * If connection is closed, it will open it, otherwise not.
     * @return The open connection.
     */
    public Connection handleConnection() {
        try {
            if (DbGate.get_instance().connectionIsNull())
                return _connector.createConnection();
            else if (DbGate.get_instance().isClosed().get_truth())
                return _connector.createConnection();
            else if (!DbGate.get_instance().isClosed().get_truth())
                return DbGate.get_instance().get_connection();
        }
        catch (Exception e) {
            Printer.get_instance().print("Couldn't handle connection",e);
        }
        return null;
    }

    /**
     * Will close the database connection of this instance.
     * @return The success of the closing as a Plato. Will be undefined, if there is a SQLException and null if the connection is null.
     */
    public Plato closeConnection() { return _connector.closeConnection(); }

    /**
     * Will determine if the Connection of this Repository is closed.
     * @return True if it is closed, false if it is open and undefined.
     *         All as Plato type.
     */
    public Plato connectionIsClosed() {
        return DbGate.get_instance().isClosed();
    }
    /**
     * Defines the database connection of this repository by getting it from its DBConnector.
     * @return The database connection from the DBConnector.
     */
    private Connection connection() { return DbGate.get_instance().get_connection(); }

    /**
     * Will do a select * of a table, do a where of id and check if the ResultSet contains any rows.
     * Does not close connection.
     * @param id The id that should exist.
     * @param table The table that the id should exist on.
     * @param column The column that should contain the id.
     * @return True if the id exists on table.
     */
    public boolean exists(long id, String table, String column) {
        ResultSet set = read("SELECT * FROM " + table + " WHERE " + column + " = " + id + ";");
        try {
            return set.next();
        } catch (SQLException e) {
            Printer.get_instance().print("ResultSet error in id exists...",e);
        }
        return false;
    }

    /**
     * Will do a select * of a table, do a where of ids and check if the ResultSet contains any rows.
     * Does not close connection.
     * @param table The table that the id should exist on.
     * @param primaryId An id that should exist.
     * @param primaryColumn A column that should contain an id.
     * @param secondaryId Another id that should exist.
     * @param secondaryColumn Another column that should contain an id.
     * @return True if the ids exists on table.
     */
    public boolean exists(String table, long primaryId, String primaryColumn,
                          long secondaryId, String secondaryColumn) {
        ResultSet set = read("SELECT * FROM " + table + " WHERE " + primaryColumn + " = " + primaryId + " AND " +
                secondaryColumn + " = " + secondaryId + ";");
        try {
            return !set.isAfterLast();
        } catch (SQLException e) {
            Printer.get_instance().print("ResultSet error in ids exists...",e);
        }
        return false;
    }

    /**
     * Will delete rows where the id matches the column of the table.
     * Checks if rows exists.
     * @param id The id of the rows that will be deleted.
     * @param table The table that the id should be deleted from.
     * @param column The column that should contain the id.
     * @param closeConnection Will close Connection if true.
     * @return True if connection is closed if intended and the rows doesn't exist.
     */
    protected boolean delete(long id, String table, String column, boolean closeConnection) {
        if (edit("DELETE FROM " + table + " WHERE " + column + " = " + id + ";")) {
            boolean doesExists = exists(id, table, column);
            return !doesExists && (closeConnection ? closeConnection().get_truth() : true);
        }
        return (closeConnection ? closeConnection().get_truth() : true);
    }

    /**
     * Will delete rows where the two id matches two columns of a table.
     * Checks if rows exists.
     * @param table The table that the ids should be deleted from.
     * @param primaryId An id of the rows that will be deleted.
     * @param primaryColumn A column that should contain an id.
     * @param secondaryId Another id of the rows that will be deleted.
     * @param secondaryColumn Another column that should contain an id.
     * @param closeConnection Will close Connection if true.
     * @return True if connection is closed if intended and the rows doesn't exist.
     */
    protected boolean delete(String table, long primaryId, String primaryColumn,
                             long secondaryId, String secondaryColumn,
                             boolean closeConnection) {
        if (edit("DELETE FROM " + table +
                " WHERE " + primaryColumn + " = " + primaryId + " AND " +
                secondaryColumn + " = " + secondaryId + ";")) {
            boolean doesExists = exists(table, primaryId, primaryColumn, secondaryId, secondaryColumn);
            return !doesExists && (closeConnection ? closeConnection().get_truth() : true);
        }
        return (closeConnection ? closeConnection().get_truth() : true);
    }

    /**
     * Will use the argument value of plato.
     * Purpose is to indicate that this is a Plato object.
     * Will also surround the argument with ''.
     * @param plato The Plato that will be inserted into the column.
     * @return The generated value to be set in the column.
     */
    protected String platoColumn(Plato plato) {
        return "'" + plato.get_argument().toString() + "'";
    }

    /**
     * Are handling the connections of databases and this application.
     * May only be used for the abstract Repository.
     */
    private class DBConnector {

        /**
         * Will create a connection, if it is closed at the moment.
         * @return If it opens the connection, it will return the opened connection, else it will return null.
         */
        public Connection createConnection() {
            openConnection();
            return DbGate.get_instance().get_connection();
        }

        /**
         * Opens the connection with the DriverManager and the Crate information.
         */
        private void openConnection() {
            if (DbGate.get_instance().connectionIsNull())
                DbGate.get_instance().open();
            else if (DbGate.get_instance().isClosed().get_truth())
                DbGate.get_instance().open();
        }

        /**
         * If the connection isn't already closed, it will close it.
         * @return The success of the closing as a Plato. Will be undefined, if there is a SQLException and null if the connection is null.
         */
        public Plato closeConnection() {
            return DbGate.get_instance().close();
        }
    }
}
