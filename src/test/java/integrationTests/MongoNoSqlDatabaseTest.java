package integrationTests;

import com.github.javafaker.Faker;
import facades.database.MongoDBFacade;
import io.cucumber.datatable.DataTable;
import model.MongoModel;
import net.serenitybdd.core.Serenity;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import utils.DateUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MongoNoSqlDatabaseTest {


    private static final String NEW_CUSTOMER = "newCustomer";
    public static final int ZERO = 0;
    public static final String MMDDYYHHMMSSS = "MMddyyHHmmss";
    public static final String LAST_NAME = "Last Name";
    public static final String NO_DATA_COLLECTION = "noDataCollection";
    private List<MongoModel> mongoList = new ArrayList<>();
    MongoDBFacade qasAddress;
    MongoDBFacade paymenTech;

    @Test
    public void createCollectionWithoutData() {
        MongoDBFacade mongoDBFacade = setUpNoDataCollectionScenario();
        assertThat(mongoDBFacade.collectionIsEmpty(NO_DATA_COLLECTION)).isTrue();
    }

    @Test
    public void iCanSaveNewColumnToCollection() {
        Setup setup = new Setup().invokeSetup();
        MongoDBFacade facade = setup.getFacade();
        String lastName = setup.getLastName();

        HashMap<String, String> valuePairs = loadDocumentValuePairs(lastName);

        Document document = facade.saveNewDocument( NEW_CUSTOMER, valuePairs);

        assertThat(lastName).isEqualTo(document.get(LAST_NAME));

        facade.dropCollection( NEW_CUSTOMER );
    }


    @NotNull
    private HashMap<String, String> loadDocumentValuePairs(String lastName) {
        HashMap<String, String> valuePairs = new HashMap<>();
        valuePairs.put("Test Id", "1");
        valuePairs.put("First Name", "Allen");
        valuePairs.put("Last Name", lastName);
        valuePairs.put("City", "Mound Bayou");
        valuePairs.put("State", "MS");
        valuePairs.put("Zip", "38762");
        return valuePairs;
    }

    private void initializeCollection(String collectionName) {
        if (collectionName.equalsIgnoreCase(NEW_CUSTOMER)) {
            mongoList.add(new MongoModel(NEW_CUSTOMER, "newCustomer.csv"));
            MongoModel.initalizeAllCollections(mongoList);
            qasAddress = Serenity.sessionVariableCalled(NEW_CUSTOMER);
        }
    }

    private DataTable getQueryQasCollectionDataTable() {
        List<List<String>> infoInTheRaw = Arrays
                .asList(Arrays.asList("ActorId", "6"),
                        Arrays.asList("AddressCity", "Cleveland"));
        return DataTable.create(infoInTheRaw);
    }

    private class SetUpInitialization {
        private MongoDBFacade facade;
        private String lastName;

        public MongoDBFacade getFacade() {
            return facade;
        }

        public String getLastName() {
            return lastName;
        }

        public SetUpInitialization initialize() {
            facade = new MongoDBFacade();
            facade.dropCollection( NEW_CUSTOMER );

            mongoList.add(new MongoModel( NEW_CUSTOMER, "newCustomer.csv"));  //File intentionally has no rows
            MongoModel.initalizeAllCollections(mongoList);

            lastName = Faker.instance ().name().lastName() +
                    DateUtilities.adjustDate(MMDDYYHHMMSSS, ZERO);
            return this;
        }
    }

    private class Setup {
        private MongoDBFacade facade;
        private String lastName;

        public MongoDBFacade getFacade() {
            return facade;
        }

        public String getLastName() {
            return lastName;
        }

        public Setup invokeSetup() {
            SetUpInitialization setInitialization = new SetUpInitialization().initialize();
            facade = setInitialization.getFacade();
            lastName = setInitialization.getLastName();
            return this;
        }
    }

    @NotNull
    private MongoDBFacade setUpNoDataCollectionScenario() {
        MongoDBFacade mongoDBFacade = new MongoDBFacade();
        mongoDBFacade.dropCollection(NO_DATA_COLLECTION);
        mongoDBFacade.createCollection(NO_DATA_COLLECTION);
        return mongoDBFacade;
    }
}
