package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.RegistrationModel;

public class RegistrationPage {

    @FindBy(linkText = "Register")
    public WebElement registerLink;


    @FindBy(id = "firstName")
    public WebElement txtFirstName;

    @FindBy(id = "lastName")
    public  WebElement txtLastName;

    @FindBy(id = "email")
    public  WebElement txtEmail;

    @FindBy(id = "password")
    public  WebElement txtPassword;

    @FindBy(id = "phoneNumber")
    public  WebElement txtPhoneNumber;

    @FindBy(id = "address")
    public  WebElement txtAddress;

    @FindBy(css = "input[type='radio'][value='Female']")
    public WebElement femaleRadioButton;

    @FindBy(css = "input[type='checkbox'][required]")
    public WebElement requiredCheckbox;

    @FindBy(id = "register")
    WebElement btnRegistration;


    public void doregistration(RegistrationModel registrationModel)
    {
        txtFirstName.sendKeys(registrationModel.getFirstName());
        txtLastName.sendKeys(registrationModel.getLastName()!=null?registrationModel.getLastName():"");
        txtEmail.sendKeys(registrationModel.getEmail());
        txtPassword.sendKeys(registrationModel.getPassword());
        txtPhoneNumber.sendKeys(registrationModel.getPhoneNumber());
        txtAddress.sendKeys(registrationModel.getAddress()!=null?registrationModel.getAddress():"");

        femaleRadioButton.click();
        requiredCheckbox.click();
        btnRegistration.click();



    }
    public RegistrationPage (WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }






}