package dao.relationalDB;

import facades.report.Reporter;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * RelationalTable
 */
public class RelationalTable {

    private RelationalDatabase relationalDatabase;

    /**
     * @param sqlDatabase
     */
    public RelationalTable(RelationalDatabase sqlDatabase) {
        this.relationalDatabase = sqlDatabase;
    }

    /**
     * @param query
     * @return
     */
    public ResultSet select(String query) {
        try {
            assertThat(query.toLowerCase())
                    .as("Query is not a select statement: " + query)
                    .startsWith("select");

            return relationalDatabase.getStatement().executeQuery(query);
        } catch (SQLException ex) {
            String message = "SQL error when executing query - " + query;
            Reporter.logException(message, ex);
        }
        return null;
    }

    /**
     * @param query
     * @return
     */
    public int insert(String query) {
        assertThat(query.toLowerCase())
                .as("Query is not a insert statement: " + query)
                .startsWith("insert");
        return queryTable(query);
    }

    /**
     * @param query
     * @return
     */
    public int update(String query) {
        assertThat(query.toLowerCase())
                .as("Query is not a update statement: " + query)
                .startsWith("update");
        return queryTable(query);
    }

    /**
     * @param query
     * @return
     */
    public int delete(String query) {
        assertThat(query.toLowerCase())
                .as("Query is not a delete statement: " + query)
                .startsWith("delete");
        return queryTable(query);
    }

    /**
     * @param query
     * @return
     */
    private int queryTable(String query) {
        try {
            return relationalDatabase.getStatement().executeUpdate(query);
        } catch (SQLException ex) {
            String message = "SQL error when executing query - " + query;
            Reporter.logException(message, ex);
        }
        return 0;
    }


}
