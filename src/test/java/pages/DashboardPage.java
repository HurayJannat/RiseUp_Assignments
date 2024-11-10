package pages;

import org.apache.commons.csv.CSVFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.csv.CSVRecord;
import setup.Setup;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Set;

public class DashboardPage extends Setup {

    @FindBy(className = "add-cost-button")
    public WebElement btnAdd;


    @FindBy(id = "itemName")
    public WebElement txtItem;

    @FindBy(css = "input[type='number']")
    public WebElement txtCost;

    @FindBy(id = "amount")
    public WebElement txtAmount;

    @FindBy(css = "button[type='submit']")
    public  WebElement btnSubmit;

    @FindBy(id = "month")
    private WebElement monthDropdown;

    @FindBy(id = "remarks")
    public WebElement txtRemarks;

    @FindBy(className = "MuiIconButton-root")
    public WebElement btnMenu;

    @FindBy(xpath = "//li[contains(text(), 'Profile')]")
    public WebElement profileMenuItem;

    @FindBy(xpath = "//button[contains(text(), 'Edit')]")
    public WebElement editButton;

    @FindBy(css = "input[type='file'].upload-input")
    public WebElement uploadInput;

    @FindBy(xpath = "//button[contains(text(),'Upload Image')]")
    private WebElement uploadButton;


    public void addItem(String itemName, String cost, String amount, String month, String remarks) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(txtItem));
        txtItem.sendKeys(itemName);
        txtCost.sendKeys(cost);
        txtAmount.sendKeys(amount);
        Select select = new Select(monthDropdown);
        select.selectByVisibleText(month);
        txtRemarks.sendKeys(remarks);

        // Submit the form if needed
        wait.until(ExpectedConditions.visibilityOf(btnSubmit));
        btnSubmit.click();
        Thread.sleep(3000);




    }


    public void addItemsFromCSV(String csvFilePath) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null && count < 5) {
                String[] data = line.split(","); // Assuming CSV format: itemName,cost,amount,month,remarks
                String itemName = data[0];
                String cost = data[1];
                String amount = data[2];
                String month = data[3];
                String remarks = data[4];


                // Add item from CSV
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                wait.until(ExpectedConditions.visibilityOf(btnAdd));
                btnAdd.click();

                addItem(itemName, cost, amount, month, remarks);
                count++;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void uploadImage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(btnMenu));
        btnMenu.click();
        wait.until(ExpectedConditions.visibilityOf(profileMenuItem));
        profileMenuItem.click();
        wait.until(ExpectedConditions.visibilityOf(editButton));
        editButton.click();
        wait.until(ExpectedConditions.visibilityOf(uploadInput));
        Path path = Paths.get("./src/test/resources/image.jpg").toAbsolutePath();
        String absolutePath = path.toString();
        uploadInput.sendKeys(absolutePath);
        uploadButton.click();




    }

    public DashboardPage (WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
}