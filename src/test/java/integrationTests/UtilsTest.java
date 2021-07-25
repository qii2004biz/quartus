package integrationTests;

import com.github.javafaker.Faker;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.DateUtilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class UtilsTest {

    private static final int ZERO_DAYS = 0;
    private static final int THIRTY_DAYS = 30;
    private static final int THREE_SIX_SIX_DAYS = 366;
    private static final String YYYY_MM_DD = "yyyy/MM/dd";
    private static final String FORWARD_SLASH = "/";
    private static final String BLANK = "";
    private static final int SIXTY_DAYS = 60;
    Date date = (new SimpleDateFormat(YYYY_MM_DD).parse(DateUtilities.adjustDate(YYYY_MM_DD, -THIRTY_DAYS)));
    String d1 = DateUtilities.adjustDate(YYYY_MM_DD, ZERO_DAYS).replaceAll(FORWARD_SLASH, BLANK);
    String d2 = DateUtilities.adjustDate(YYYY_MM_DD, THIRTY_DAYS).replaceAll(FORWARD_SLASH, BLANK);
    String d3 = DateUtilities.adjustDate(YYYY_MM_DD, THREE_SIX_SIX_DAYS).replaceAll(FORWARD_SLASH, BLANK);
    String d4 = DateUtilities.adjustDate(YYYY_MM_DD, THREE_SIX_SIX_DAYS).replaceAll(FORWARD_SLASH, BLANK);
    Faker faker = Faker.instance ();
    String email = faker.name ().firstName () +"." + faker.name ().lastName ()
            +faker.number ().digits ( 3 )+"@nowhere.com";
    String phone = faker.phoneNumber ().cellPhone ();
    String zip = faker.address ().zipCode ();
//    String county = faker.address ().countyByZipCode ( zip );

    public UtilsTest() throws ParseException {
    }

    @Test
    public void testRandomGeneratorEmail() {
        String address = Faker.instance ().address().streetAddress ()
                + "|" + faker.address ().city ()
                + "|" + faker.address ().state ()
                + "|" + zip
                + "|" + "county"
                + "|" + phone;

        EmailValidator emailValidator = EmailValidator.getInstance();
        System.out.println(address);
        String[] s = address.split("\\|");
        assertThat(s.length).isEqualTo(6);
        assertThat(emailValidator.isValid(email)).isTrue();
        System.out.println("done");
    }

    @Test
    public void addToToday() throws ParseException {

        System.out.println(new Date() + "\n" + date);
        assignDateVariables(ZERO_DAYS, THIRTY_DAYS, SIXTY_DAYS, THREE_SIX_SIX_DAYS);

        printDates();

        assertThat(Integer.parseInt(d2)).isGreaterThan(Integer.parseInt(d1));
        assertThat(Integer.parseInt(d3)).isGreaterThan(Integer.parseInt(d2));
        assertThat(Integer.parseInt(d4)).isGreaterThan(Integer.parseInt(d3));
        assertThat(Integer.parseInt(d4)).isGreaterThan(Integer.parseInt(d1));
    }

    @Test
    public void subtractFromToday() throws ParseException {

        System.out.println(new Date() + "\n" + date);
        assignDateVariables(-ZERO_DAYS, -THIRTY_DAYS, -SIXTY_DAYS, -THREE_SIX_SIX_DAYS);

        printDates();

        assertThat(Integer.parseInt(d2)).isLessThan(Integer.parseInt(d1));
        assertThat(Integer.parseInt(d3)).isLessThan(Integer.parseInt(d2));
        assertThat(Integer.parseInt(d4)).isLessThan(Integer.parseInt(d3));
        assertThat(Integer.parseInt(d4)).isLessThan(Integer.parseInt(d1));
    }

    private void printDates() {
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
        System.out.println(d4);
    }

    private void assignDateVariables(int i1, int i2, int i3, int i4) {
        d1 = DateUtilities.adjustDate(YYYY_MM_DD, i1).replaceAll(FORWARD_SLASH, BLANK);
        d2 = DateUtilities.adjustDate(YYYY_MM_DD, i2).replaceAll(FORWARD_SLASH, BLANK);
        d3 = DateUtilities.adjustDate(YYYY_MM_DD, i3).replaceAll(FORWARD_SLASH, BLANK);
        d4 = DateUtilities.adjustDate(YYYY_MM_DD, i4).replaceAll(FORWARD_SLASH, BLANK);
    }

}
