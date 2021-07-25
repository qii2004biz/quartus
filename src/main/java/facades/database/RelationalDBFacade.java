package facades.database;

import dao.relationalDB.RelationalDatabase;
import dao.relationalDB.RelationalTable;
import java.sql.ResultSet;

/**
 * RelationalDBFacade
 */
public class RelationalDBFacade {

    private RelationalDatabase database;
    private RelationalTable table;

    /**
     *
     * @param connectionString
     */
    public RelationalDBFacade(String connectionString) {
        database = new RelationalDatabase(connectionString);
        table = new RelationalTable(database);
    }

    /**
     *
     * @param connectionString
     * @param userName
     * @param  password
     */
    public RelationalDBFacade(String connectionString, String userName, String password) {
        database = new RelationalDatabase(connectionString, userName, password);
        table = new RelationalTable(database);
    }

    /**
     * selectRecords - select records from RelationalTable
     * @param query
     * @return ResultSet
     */
    public ResultSet selectRecords(String query) {
        return table.select(query);
    }

    /**
     * insertRecords - insert records into RelationalTable
     * @param query
     * @return int - records inserted
     */
    public int insertRecords(String query){
        return table.insert(query);
    }

    /**
     * updateRecords - updates records in RelationalTable
     * @param query
     * @return int - records updated
     */
    public int updateRecords(String query){
        return table.update(query);
    }

    /**
     * deleteRecords - delete records from RelationalTable
     * @param query
     * @return int - records deleted
     */
    public int deleteRecords(String query){
        return table.delete(query);
    }

    /**
     * closeConnection
     */
    public void closeConnection() {
        database.closeConnection();
    }
}
