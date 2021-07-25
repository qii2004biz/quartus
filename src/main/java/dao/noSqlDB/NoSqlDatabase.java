package dao.noSqlDB;

import com.google.common.base.Strings;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import facades.report.Reporter;
import org.junit.Assert;
import utils.PropertiesManager;

public class NoSqlDatabase {
    public static final String MONGODB_INTERNAL_HOSTNAME = "mongodb.internal.hostname";
    public static final String MONGODB_INTERNAL_PORT = "mongodb.internal.port";
    public static final String MONGODB_EXTERNAL_HOSTNAME = "mongodb.external.hostname";
    public static final String MONGODB_EXTERNAL_PORT = "mongodb.external.port";
    private String hostName;
    private String dbName;
    private Integer portNo;
    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;

    private static String propertiesFilePath = "src/test/java/common/config/";
    private static final String fileName = "MONGODB.properties";

    private static PropertiesManager props = new PropertiesManager(propertiesFilePath, fileName);

    private static NoSqlDatabase uniqueInstance;

    private String hostNameKey, portNoKey;
    private String userDomain = Strings.nullToEmpty(System.getenv("USERDOMAIN")).toUpperCase();
    public static synchronized NoSqlDatabase getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new NoSqlDatabase();
        }
        return uniqueInstance;
    }

    private NoSqlDatabase() {
        setConnectionDetails();
        openConnection();
    }

    private void setConnectionDetails() {
        dbName = props.getValue("mongodb.dbName");

        getMongoDbHostAndPortValues();
        this.hostName = props.getValue(hostNameKey);
        this.portNo = Integer.parseInt(props.getValue(portNoKey));

        failWhenNull();
    }

    private void getMongoDbHostAndPortValues() {
        if (userDomain.equals("COMPANY") || userDomain.equals("NONPROD")) {
            hostNameKey = MONGODB_INTERNAL_HOSTNAME;
            portNoKey = MONGODB_INTERNAL_PORT;
        } else {
            hostNameKey = MONGODB_EXTERNAL_HOSTNAME;
            portNoKey = MONGODB_EXTERNAL_PORT;
        }
    }

    private void failWhenNull() {
        if (this.hostName == null || this.portNo == null)
            Assert.fail("ERROR: mongoDb.properties not set properly! " + NoSqlDatabase.class.getName());
    }

    private void openConnection() {
        try {
            MongoClient mongoClient = new MongoClient(hostName, portNo);
            this.mongoDatabase = mongoClient.getDatabase(dbName);
        } catch (Exception ex) {
            Reporter.logException("Error while connecting to mongo client", ex);
        }
    }

    @Override
    public String toString() {
        return "--host " + hostName +
                " --port " + portNo +
                " --db " + dbName;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
