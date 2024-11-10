package pages;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class LoginPage {

    @FindBy(id = "email")
    WebElement txtEmail;

    @FindBy(id = "password")
    WebElement txtPassword;


    @FindBy(linkText = "Reset it here")
    public WebElement resetLink;

    @FindBy(className = "MuiButton-containedPrimary")
    private WebElement loginButton;


    public void dologin(String email, String password)
    {
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        loginButton.click();
    }

    public void clearCreds()
    {
        txtEmail.sendKeys(Keys.CONTROL,"a");
        txtEmail.sendKeys(Keys.BACK_SPACE);
        txtPassword.sendKeys(Keys.CONTROL,"a");
        txtPassword.sendKeys(Keys.BACK_SPACE);
    }

    public void userLogin(WebDriver driver) throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray= (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));

        JSONObject userobj = (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email = (String) userobj.get("email");
        String password = (String) userobj.get("password");
        loginPage.dologin(email,password);

        //Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Dashboard']")));

        // Assert
        String expectedText = "Dashboard";
        String actualText = driver.findElement(By.xpath("//div[text()='Dashboard']")).getText();
        Assert.assertEquals(actualText, expectedText, "The actual text does not match the expected text.");


    }

    public LoginPage (WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }







}