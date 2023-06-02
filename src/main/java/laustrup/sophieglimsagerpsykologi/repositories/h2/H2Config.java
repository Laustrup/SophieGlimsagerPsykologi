package laustrup.sophieglimsagerpsykologi.repositories.h2;

/**
 * Is used to configure and translate MySQL into H2.
 * Extends H2Init, that will use methods to create the setup configurations for the H2.
 */

public class H2Config extends H2Init {

    /**
     * Generates a SQL script for initializing the H2 database with schema and values.
     * The title will be h2_init.sql and can be found at the sql directory.
     */
    protected void h2InitScript() {
        generateH2InitScript(_h2InitFileLocation);
    }

    /**
     * Will translate a MySQL SQL query that will be executed to the H2 in testing
     * into H2 syntax dialect.
     * @param query The MySQL SQL query that will be translated before execution.
     * @return The H2 translated SQL query.
     */
    public String translateMySQLIntoH2(String query) {
        return query.replace("`", "\"");
    }

    /**
     * Will generate a string that will run a script at initiating the H2 database,
     * that is the boilerplate.sql and default_values.sql.
     * @return The generated init String.
     */
    protected String h2InitRunScript() {
        return "INIT=RUNSCRIPT FROM '" + _h2InitFileLocation + "'";
    }
}