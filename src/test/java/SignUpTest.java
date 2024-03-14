import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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

        //set form & assert
        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.sendKeys("Marcin");
        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("Testerski");
        WebElement phoneNumber = driver.findElement(By.name("phone"));
        phoneNumber.sendKeys("123456789");
        WebElement emailAddress = driver.findElement(By.name("email"));
        emailAddress.sendKeys("test2@tester.com");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("test123");
        WebElement confirmPassword = driver.findElement(By.name("confirmpassword"));
        confirmPassword.sendKeys("test123");
        WebElement signUpBttn = driver.findElement(By.xpath("//button[contains(@class, 'signupbtn')]"));
        signUpBttn.click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(heading.getText().contains("Hi, Marcin Testerski"));
    }
}