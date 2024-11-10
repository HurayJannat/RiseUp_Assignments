package testrunner;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.LoginPage;
import setup.Setup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class LoginTestRunner extends Setup {


    //@Test(priority = 1, description = "User should Not be able to login with invalid creds")
    public void doNoTLoginWithInvalidCreds() throws InterruptedException {
        //Login
        LoginPage login = new LoginPage(driver);
        login.dologin("huray.jannatdipty@gmail.com", "12345");
        //Wait
        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("MuiTypography-body1")));
        // Assert
        String expectedMessage ="Invalid";
        String errorMessage = driver.findElement(By.className("MuiTypography-body1")).getText();
        Assert.assertTrue(errorMessage.contains(expectedMessage));
        login.clearCreds();


    }



   // @Test(priority = 2, description = "User should be able to login with valid creds")
    public void doLoginWithValidCreds() throws InterruptedException {

        //Login
        LoginPage login = new LoginPage(driver);
        login.dologin("huray.jannatdipty@gmail.com","1234");

        //Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Dashboard']")));

        // Assert
        String expectedText = "Dashboard";
        String actualText = driver.findElement(By.xpath("//div[text()='Dashboard']")).getText();
        Assert.assertEquals(actualText, expectedText, "The actual text does not match the expected text.");
    }



    @Test(priority =1, description = "User should be able to login")

    public void userLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.userLogin(driver);


    }



}