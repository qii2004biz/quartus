package dao.noSqlDB;

import com.mongodb.client.MongoDatabase;
import io.cucumber.datatable.DataTable;
import org.bson.Document;

public class NoSqlDocument {
    private Document document;
    private static MongoDatabase mongoDatabase;
    private static DataTable queryParams;
    private static String user;
    private static String name;

    //Constructors
    public NoSqlDocument(DataTable queryParams) {
        this.user = user;
        this.mongoDatabase = mongoDatabase;
        this.name = name;
        this.queryParams = queryParams;
    }

    public NoSqlDocument(NoSqlCollection collection, DataTable queryParameters) {
        document = collection.queryCollection(queryParameters);
    }

    public String get(String column) {
        try {
            return document.get(column).toString();
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void put(String column, String value) {
        try {
            document.put(column, value);
        } catch (NullPointerException e) {
            return;
        }
    }
}
