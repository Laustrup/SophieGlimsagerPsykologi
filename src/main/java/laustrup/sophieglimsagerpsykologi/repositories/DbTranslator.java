package laustrup.sophieglimsagerpsykologi.repositories;

import java.time.LocalDateTime;

/**
 * Is used for having methods, that will handle some Java and SQL issues.
 */
public abstract class DbTranslator {

    /**
     * Will set the value as a VARCHAR, surrounded with '', but in case it is null,
     * it will set it as null.
     * @param column The value that will be put into the column.
     * @return The generated value to be set in the column.
     */
    protected String varCharColumn(String column) {
        return column != null ? "'" + column + "'" : null;
    }

    /**
     * Will set the value as a DATETIME, surrounded with '', but in case it is null,
     * it will set it as null.
     * @param column The value that will be put into the column.
     * @return The generated value to be set in the column.
     */
    protected String dateTimeColumn(LocalDateTime column) {
        return column != null ? "'" + column + "'" : null;
    }

    /**
     * Will set the value as a DATETIME, surrounded with '', but in case it is null,
     * it will set it as the time now with NOW().
     * @param column The value that will be put into the column.
     * @return The generated value to be set in the column.
     */
    protected String timestampColumn(LocalDateTime column) {
        return column != null ? "'" + column + "'" : "NOW()";
    }

    /**
     * Will create a SQL statement, that is used for searching the database,
     * also removes % in the search query, for preventing issues.
     * @param columns The columns in the db that should be compared with the search query.
     * @param query The search query, with the value that should be compared of the columns,
     *              that will be accepted and included
     * @return The generated SQL statement.
     */
    protected String whereSearchStatement(String[] columns, String query) {
        String sql = new String("WHERE ");
        query = query.replaceAll("%","");

        for (int i = 0; i < columns.length; i++)
            sql += columns[i] + " LIKE '%" + query + "%'" + (i < columns.length-1 ? " OR " : "");

        return sql;
    }
}
