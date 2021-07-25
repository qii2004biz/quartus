package dao.relationalDB;

import facades.report.Reporter;

import java.sql.*;

public class RelationalDatabase {


    private Connection connection;
    private Statement statement;


    /**
     * RelationalDatabase initalize RelationalDatabase connection
     *
     * @param connectionString
     */
    public RelationalDatabase(String connectionString) {
        connection = openConnection(connectionString);
        statement = createStatement(connection);
    }


    /**
     * RelationalDatabase initalize RelationalDatabase connection
     *
     * @param connectionString
     * @param userName
     * @param password
     */
    public RelationalDatabase(String connectionString, String userName, String password) {
        connection = openConnection(connectionString, userName, password);
        statement = createStatement(connection);
    }

    /**
     * openConnection - Open connection with only connection string
     *
     * @param connectionString
     * @return Connection
     */
    private Connection openConnection(String connectionString) {
        if (connection != null) {
            return connection;
        }
        try {
            return DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            String message = "SQL Exception when opening connection to -" + connectionString;
            Reporter.logException(message, ex);
        }
        return null;
    }

    /**
     * openConnection - Open connection with connection string and user credentials
     *
     * @param connectionString
     * @param user
     * @param password
     * @return
     */
    private Connection openConnection(String connectionString, String user, String password) {
        if (connection != null) {
            return connection;
        }
        try {
            return DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException ex) {
            String message = "SQL Exception when opening connection to -" + connectionString;
            Reporter.logException(message, ex);
        }
        return null;
    }

    /**
     * createStatement - Creates new statement for the connection
     *
     * @param connection
     * @return Statement
     */
    private Statement createStatement(Connection connection) {
        try {
            return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception ex) {
            String message = "SQL Exception when creating statement on connection -" + connection.toString();
            Reporter.logException(message, ex);
        }
        return null;
    }

    /**
     * getStatement - Returns statement
     *
     * @return Statement
     */

    Statement getStatement() {
        return statement;
    }

    /**
     * closeConnection - Closes existing connection and statement
     */

    public void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            statement = null;
            connection = null;
        } catch (SQLException ex) {
            String message = "SQL Exception when closing connection or statement";
            Reporter.logException(message, ex);
        }

    }

}
