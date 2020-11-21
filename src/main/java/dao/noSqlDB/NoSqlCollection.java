package dao.noSqlDB;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import facades.report.Reporter;
import io.cucumber.datatable.DataTable;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NoSqlCollection {
    public static final String EXCEL_DELIMITER = "\\|";
    public static final String ID = "_id";
    private String collectionName;
    private String fileName;
    private MongoCollection mongoCollection;
    private static final String currentDirectory = System.getProperty("user.dir");
    private static final String dataFolderPath = "/src/test/resources/data/";


    public NoSqlCollection(NoSqlDatabase database, String collectionName, String fileName) {
        this.fileName = fileName;
        this.collectionName = System.getProperty("user.name") + "_" + collectionName;
        mongoCollection = database.getMongoDatabase().getCollection(this.collectionName);
        loadCollectionFromCsv(database, this.collectionName, this.fileName);
    }

    public NoSqlCollection(NoSqlDatabase database, String collectionName) {
        this.collectionName = System.getProperty("user.name") + "_" + collectionName;
        mongoCollection = database.getMongoDatabase().getCollection(this.collectionName);
    }

    public Document queryCollection(DataTable queryParameters) {
        BasicDBObject query = getQueryFromDataTable(queryParameters);
        FindIterable<Document> result = mongoCollection.find(query).limit(1);
        if (result.first() == null) {
            Reporter.printInfo("No actor found for the query - " + query.toString());
            return null;
        }
        return result.first();
    }
    public NoSqlCollection getCollectionByName (NoSqlDatabase database, String collectionName) {
        return (NoSqlCollection) database.getMongoDatabase().getCollection(collectionName);
    }
    private BasicDBObject getQueryFromDataTable(DataTable attrTable) {

        List<List<String>> actorAttributes = attrTable.asLists();

        BasicDBObject query = new BasicDBObject();
        actorAttributes.forEach(row -> {
            if (row.size() > actorAttributes.size()) {
                Reporter.addStepLog("Number of attributes passed ct: " + actorAttributes.size());
            }
            query.append(row.get(0), row.get(1));
        });

        return query;
    }

    public void dropCollection() {
        mongoCollection.drop();
    }

    public void importData(String collectionName, String fileName) {
        loadCollectionFromCsv(NoSqlDatabase.getUniqueInstance(), collectionName, fileName);
    }

    public void saveNewDocumentToCollection(Document document, HashMap<String, String> valuePairs) {
        setColumnValueAndUpdateTimeStamp(document, valuePairs);
        insertValuePairs(document);
    }
    private void loadCollectionFromCsv(NoSqlDatabase database, String collectionName, String fileName) {

        try (FileReader fr = new FileReader(currentDirectory + dataFolderPath + fileName);
             BufferedReader br = new BufferedReader(fr);) {
            String rowContent;
            //create the header
            String header = br.readLine();
            String[] headerColumn = header.split(EXCEL_DELIMITER);
            Integer rowCount = 0; //we won't count the header

            while ((rowContent = br.readLine()) != null) {
                String[] rowColums = rowContent.split(EXCEL_DELIMITER);
                org.bson.Document document = new Document();
                for (int i = 0; i < rowColums.length; i++) {
                    document.append(headerColumn[i].trim(), rowColums[i].trim());
                }
                mongoCollection.insertOne(document);
                rowCount++;
            }

        } catch (IOException e) {
            String msg = "Test aborted! Didn't find file: " + fileName;
            Reporter.logException(msg, e);
        }
    }

    private void setColumnValueAndUpdateTimeStamp(Document document, HashMap<String, String> valuePairs) {
        for (String key : valuePairs.keySet()) {
            document.put(key, valuePairs.get(key));
        }
        document.put("DateTimeStamp", new Date().toString());
    }
    private void insertValuePairs(Document document) {
        mongoCollection.insertOne(document);
    }
}
