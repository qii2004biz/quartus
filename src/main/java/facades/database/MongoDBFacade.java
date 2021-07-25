package facades.database;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import dao.noSqlDB.NoSqlCollection;
import dao.noSqlDB.NoSqlDatabase;
import dao.noSqlDB.NoSqlDocument;
import io.cucumber.datatable.DataTable;
import org.bson.Document;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MongoDBFacade {
    private NoSqlDatabase database = NoSqlDatabase.getUniqueInstance() ;
    private NoSqlCollection collection;
    private NoSqlDocument noSqlDocument;
    private Document document = new Document();

    public MongoDBFacade() {
    }

    public MongoDBFacade(String collectionName) {
        collection = new NoSqlCollection(this.database, collectionName);
    }

    public MongoDBFacade(String collectionName, String fileName) {
        createCollection(collectionName, fileName);
    }

    public void findActor(DataTable queryParameters) {
        noSqlDocument = new NoSqlDocument(collection, queryParameters);
    }


    public void findActor(String column, String value) {
        List<java.util.List<String>> infoInTheRaw = Arrays.asList(Arrays.asList(column, value));
        DataTable dataTable = DataTable.create(infoInTheRaw);
        findActor(dataTable);
    }

    public String get(String column) {
        return noSqlDocument.get(column);
    }

    public void put(String column, String value) {
        noSqlDocument.put(column, value);
    }

    public void dropCollection(String collectionName) {
        collection = new NoSqlCollection(this.database, collectionName);
        collection.dropCollection();
    }

    public Document saveNewDocument(String collectionName, HashMap valuePairs) {

        if (this.collection == null)
            collection = new NoSqlCollection(this.database, collectionName);

        collection.saveNewDocumentToCollection(document, valuePairs);
        return this.document;
    }

    public void createCollection(String collectionName, String fileName) {
        collection = new NoSqlCollection(this.database, collectionName, fileName);
    }

    public void createCollection(String collectionName) {
        collection = new NoSqlCollection(this.database, collectionName);
    }

    public void loadCollection(String collectionName, String fileName) {
        NoSqlCollection noSqlCollection = new NoSqlCollection(this.database, collectionName, fileName);
    }

    public boolean collectionIsEmpty(String noDataCollection) {
        MongoIterable<String> result = database.getMongoDatabase().listCollectionNames();
        MongoCursor<String> iterator = result.iterator();
        String user = System.getProperty("user.name") + "_";

        while (iterator.hasNext()) {
            if (iterator.next().equalsIgnoreCase(user + noDataCollection))
                return false;
        }
        return true;
    }
}
