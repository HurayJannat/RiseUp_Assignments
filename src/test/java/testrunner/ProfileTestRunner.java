package testrunner;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import setup.Setup;

import java.io.IOException;
import java.time.Duration;

public class ProfileTestRunner extends Setup {


    @BeforeTest
    public void doUserLogin() throws IOException, ParseException {
        LoginPage login = new LoginPage(driver);
        login.userLogin(driver);

    }

    @Test(priority = 1, description = "verify to upload image")
    public void uploadImage()
    {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.uploadImage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

    }
}
