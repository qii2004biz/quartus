package dao.noSqlDB;

import com.google.common.base.Strings;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import facades.report.Reporter;
import org.junit.Assert;
import utils.PropertiesManager;

public class NoSqlDatabase {
    public static final String MONGODB_LOCAL_HOSTNAME = "mongodb.local.hostname";
    public static final String MONGODB_LOCAL_PORT = "mongodb.local.port";
    public static final String MONGODB_SHARED_HOSTNAME = "mongodb.shared.hostname";
    public static final String MONGODB_SHARED_PORT = "mongodb.shared.port";
    public static final String NO_SQL_HOST = "noSqlHost";
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
        String noSqlHost_prop = System.getProperty ( NO_SQL_HOST );
        hostNameKey = MONGODB_LOCAL_HOSTNAME;
        portNoKey = MONGODB_LOCAL_PORT;
        if (System.getProperty ( noSqlHost_prop) != null) {
            if (noSqlHost_prop.toUpperCase () == "SHARED") {
                hostNameKey = MONGODB_SHARED_HOSTNAME;
                portNoKey = MONGODB_SHARED_PORT;
            }
        }
    }

    private void failWhenNull() {
        if (this.hostName == null)
            Assert.fail("ERROR: mongoDb.properties hostName is null! " + NoSqlDatabase.class.getName());
        if (this.portNo == null || this.portNo == 0)
            Assert.fail("ERROR: mongoDb.properties portNo is null or zero! " + NoSqlDatabase.class.getName());
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
