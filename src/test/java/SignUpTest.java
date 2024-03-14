import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class SignUpTest {

    @Test
    public void signUp() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/");

        //set sign up site
        //driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        WebElement myAccBttn = driver.findElements(By.xpath("//li[@id='li_myaccount']")).get(1);
        myAccBttn.click();
        WebElement sngUpBttn = driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1);
        sngUpBttn.click();
    }
}