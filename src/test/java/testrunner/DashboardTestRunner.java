package testrunner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import setup.Setup;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;

public class DashboardTestRunner extends Setup {

    //@BeforeTest
    public void doLogin(){
        LoginPage login = new LoginPage(driver);
        login.dologin("salman@test.com","1234");
        // login.dologin(System.getProperty("username"),System.getProperty("password"));
    }

   @BeforeTest
    public void doUserLogin() throws IOException, ParseException {
        LoginPage login = new LoginPage(driver);
        login.userLogin(driver);

    }



    @Test(priority = 1, description = "Verify add item from csv")
    public void addItemsViaCSV() throws IOException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        String csvFilePath = "./src/test/resources/items.csv";
        dashboardPage.addItemsFromCSV(csvFilePath);
    }








}