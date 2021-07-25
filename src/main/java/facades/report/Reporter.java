package facades.report;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import java.nio.file.Path;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

public class Reporter {

    private Reporter() {
    }

    /**
     * addStepLog - Add's step log to all reporter
     *
     * @param message - Information to be added
     */

    public static void addStepLog(String message) {
        serenityLog(message);
    }


    /**
     * printInfo - Output Information
     *
     * @param message - Information to be added
     */

    public static void printInfo(String message) {
        serenityLog(message);
        System.out.println("Custom Error " + message);
    }


    /**
     * logException - Log Exception
     *
     * @param message - Information to be added
     */

    public static void logException(String message) {
        serenityLog(message);
        System.out.println("Custom Error " + message);
        fail(message);
    }


    /**
     * logException - Log Exception with exception
     *
     * @param message - Information to be added
     * @param ex      - exception thrown by the class
     */

    public static void logException(String message, Exception ex) {

        if (ex instanceof SQLException) {
            message = message + ((SQLException) ex).getSQLState();
        }
        serenityLog(message);
        System.out.println("Custom Error " + message);
        System.out.println("Custom Error " + ex.getMessage());
        fail(message, ex);
    }

    /**
     * linkFileToReport - Creates a link to the external file inside serenity report. Useful to log input and output xml etc
     *
     * @param title    - title to be displayed on the button
     * @param filePath - Path of the file to be linked
     */
    public static void linkFileToReport(String title, Path filePath) {
        try {
            Serenity.recordReportData().withTitle(title)
                    .fromFile(filePath);
        } catch (Exception ex) {
            String msg = "Unable to link File to Report : " + filePath;
            logException(msg, ex);
        }
    }

    /**
     * linkContentToReport - Creates a link to the content inside serenity report. Useful to log input and output xml etc
     *
     * @param title   - title to be displayed on the button
     * @param content - Path of the file to be linked
     */

    public static void linkContentToReport(String title, String content) {
        Serenity.recordReportData().withTitle(title)
                .andContents(content);
    }


    /**
     * serenityLog - This method is used to produce log the custom message inside Serenity reports
     *
     * @param message - title to be displayed on the button
     */

    @Step("{0}")
    private static void serenityLog(String message) {
//        This method is used to produce log the custom message inside Serenity reports
    }
}


