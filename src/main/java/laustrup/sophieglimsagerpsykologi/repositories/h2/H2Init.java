package laustrup.sophieglimsagerpsykologi.repositories.h2;

import laustrup.services.FileService;
import laustrup.sophieglimsagerpsykologi.Defaults;
import laustrup.utilities.collections.lists.Liszt;
import laustrup.utilities.console.Printer;

import java.time.LocalDateTime;

/**
 * Is used for initializing the H2 database, that is the embedded database used for testing.
 * Creates a h2_init.sql at the SQL directory, that is composed by the boilerplate.sql and default_values.sql
 * and translate for the H2 to generate start values and schemas.
 */
class H2Init {

    /** The location of the directory containing SQL scripts. */
    protected final String _sqlDirectory = Defaults.get_instance().get_directory("/") + "src/main/resources/sqls/";

    /** The location and name of the h2_init.sql file, that is the boilerplate.sql and default_values.sql together. */
    protected final String _h2InitFileLocation = _sqlDirectory + "h2_init.sql";

    /**
     * Is true, if there has been generated a script of the MySQL boilerplate.sql into H2 init script.
     * If it is false, it will allow one to be generated.
     */
    private boolean _hasGeneratedInitScript = false;

    /**
     * If the h2_init.sql already exists, it will reset the values, otherwise create it from scratch.
     * @param location The location the file will be generated.
     */
    void generateH2InitScript(String location) {
        if (!_hasGeneratedInitScript) {
            LocalDateTime start = LocalDateTime.now();

            String[][] replacements = mySQLTranslations();

            FileService.get_instance().delete(location);
            FileService.get_instance().write(location,
                    FileService.get_instance().getContent(_sqlDirectory, "boilerplate.sql",
                            replacements) /*+
                            (Defaults.get_instance().is_included()
                                    ? FileService.get_instance().getContent(_sqlDirectory, "default_values.sql",
                                    replacements) : ""
                            )*/
            );
            _hasGeneratedInitScript = true;

            Printer.get_instance().print("h2_init.sql generation",start);
        }
    }

    /**
     * Translate the boilerplate.sql and default_values.sql into SQL that the H2 understands
     * and generates a files called h2_init.sql at the sql directory.
     * @return The translations that should be replacements from the MySQL files.
     */
    private String[][] mySQLTranslations() {
        try {
            return convertCollectionIntoLines(generateCollection(
                            FileService.get_instance().getContent(_sqlDirectory, "boilerplate.sql") +
                                    FileService.get_instance().getContent(_sqlDirectory, "default_values.sql")
                    )
            );
        } catch (Exception e) {
            Printer.get_instance().print("Error in translating MySQL into H2 syntax...",e);
            return new String[][]{};
        }
    }

    /**
     * Will generate a collection of the contents of comments and ENUMs that will be removed from scripts.
     * @param scripts The scripts containing the comments that will be removed.
     * @return The generated collection of comments.
     */
    private Liszt<String> generateCollection(String scripts) {
        String line = new String();
        boolean lineIsComment = false;
        Liszt<String> collection = new Liszt<>();

        for (int i = 0; i < scripts.length(); i++) {
            if (scripts.charAt(i) == '\n') {
                if ((lineIsComment && !collection.contains(line)) || line.contains("ENUM")) {
                    if (line.contains("ENUM")) {
                        line = line.replace("    ","");
                        for (int j = i; scripts.charAt(j) != ')'; j++)
                            line += scripts.charAt(j) != ' ' && scripts.charAt(j) != '\n' ? scripts.charAt(j) : "";
                        line += ")";
                    }
                    if (line.length() > 2) {
                        line = line.replaceAll("--", "");
                        line = line.replaceAll("90 ","");
                    }
                    if (!line.equals("90"))
                        collection.add(line);
                }
                line = new String();
                lineIsComment = false;
            } else {
                line += scripts.charAt(i);
                if (line.length() > 1 && (String.valueOf(scripts.charAt(i-1)).equals("-") && scripts.charAt(i) == '-')) {
                    lineIsComment = true;
                    line = String.valueOf(scripts.charAt(i-1) + scripts.charAt(i));
                }
            }
        }

        return collection;
    }

    /**
     * Takes the elements from the collections and puts them into an array,
     * where the replacement of the value will be an empty String.
     * Also adds the other commands with the replacement syntax for the H2.
     * @param collection A collection of comment that will be removed with an empty String.
     * @return The converted lines, that is the replacement for the h2_init.sql.
     */
    private String[][] convertCollectionIntoLines(Liszt<String> collection) {
        String[][] syntaxes = new String[][]{
                new String[]{"\n", " "},
                new String[]{"BOOL","BOOLEAN"},
                new String[]{"DATETIME","TIMESTAMP"},
                new String[]{" INT(1)"," INTEGER"},
                new String[]{" INT(2)"," INTEGER"},
                new String[]{" INT(3)"," INTEGER"},
                new String[]{" INT(4)"," INTEGER"},
                new String[]{" INT(5)"," INTEGER"},
                new String[]{" INT(6)"," INTEGER"},
                new String[]{" INT(7)"," INTEGER"},
                new String[]{" INT(8)"," INTEGER"},
                new String[]{" INT(9)"," INTEGER"},
                new String[]{" INT(10)"," INTEGER"},
                new String[]{"BIGINT(20)", "BIGINT"},
                new String[]{"BIGINT(19)", "BIGINT"},
                new String[]{"BIGINT(18)", "BIGINT"},
                new String[]{"BIGINT(17)", "BIGINT"},
                new String[]{"BIGINT(16)", "BIGINT"},
                new String[]{"BIGINT(15)", "BIGINT"},
                new String[]{"`","\""},
                new String[]{"--",""},
                new String[]{"         "," "},
                new String[]{"     "," "},
                new String[]{"    "," "}
        };
        String[][] lines = new String[collection.size()+syntaxes.length][collection.size()+syntaxes.length];
        for (int i = 0; i < lines.length-syntaxes.length; i++) {
            if (collection.get(i).contains("ENUM"))
                lines[i] = new String[]{collection.get(i), translateEnum(collection.get(i))};
            else
                lines[i] = new String[]{collection.get(i), ""};
        }

        return addSyntaxes(lines,syntaxes);
    }

    /**
     * Adds the last syntaxes into the lines, that will be removed from the SQLs.
     * @param lines The lines that will be joined with syntaxes.
     * @param syntaxes The syntaxes that will be put into lines.
     * @return The lines with syntaxes.
     */
    private String[][] addSyntaxes(String[][] lines, String[][] syntaxes) {
        int syntaxIndex = 0;
        for (int i = syntaxes.length; i > 0; i--) {
            lines[lines.length-i] = syntaxes[syntaxIndex];
            syntaxIndex++;
        }
        return lines;
    }

    /**
     * Translates Enum variables from MySQL into a VARCHAR with a CHECK,
     * meaning only some VARCHAR values are allowed.
     * @param line The whole line that will be converted.
     * @return The VARCHAR parameter converted into VARCHAR with the CHECK.
     */
    private String translateEnum(String line) {
        int lengthLimit = 0;
        String column = new String(), option = new String();
        boolean isPartOfAnOption = false, columnIsFound = false, columnStart = false;
        Liszt<String> options = new Liszt<>();

        for (int i = 0; i < line.length(); i++) {
            if (!columnStart)
                columnStart = line.charAt(i) != ' ';
            if (columnStart && !columnIsFound) {
                if (line.charAt(i) == ' ')
                    columnIsFound = true;
                else
                    column += line.charAt(i);
            } else {
                if (line.charAt(i) != ',' && line.charAt(i-1) == '\'')
                    isPartOfAnOption = true;
                if (isPartOfAnOption) {
                    option += line.charAt(i);
                    lengthLimit = lengthLimit >= option.length() ? lengthLimit : option.length();
                }
                if (isPartOfAnOption && i < line.length()-1 && (line.charAt(i) != ',' && line.charAt(i+1) == '\'')) {
                    options.add(option);
                    option = new String();
                    isPartOfAnOption = false;
                }
            }
        }

        String translation = column + " VARCHAR(" + lengthLimit + ") CHECK (" + column + " IN (";

        for (int i = 1; i <= options.size(); i++){
            translation += "'" + options.Get(i) + "'";

            if (i < options.size())
                translation += ", ";
        }

        return translation + "))";
    }
}