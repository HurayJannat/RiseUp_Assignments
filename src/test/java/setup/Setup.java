package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class Setup {
    public WebDriver driver;

    @BeforeTest
    public void setup()
    {
        driver= new ChromeDriver();
        driver.get("https://dailyfinance.roadtocareer.net/");
        driver.manage().window().maximize();

    }

}