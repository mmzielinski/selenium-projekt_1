import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        //set sign up site
        //driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        WebElement myAccBttn = driver.findElements(By.xpath("//li[@id='li_myaccount']")).get(1);
        myAccBttn.click();
        WebElement sngUpBttn = driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1);
        sngUpBttn.click();

        //set form & assert
        int randomNumber = (int) (Math.random()*10000);
        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.sendKeys("Marcin");
        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("Testerski");
        WebElement phoneNumber = driver.findElement(By.name("phone"));
        phoneNumber.sendKeys("123456789");
        WebElement emailAddress = driver.findElement(By.name("email"));
        emailAddress.sendKeys("test"+randomNumber+"@tester.com");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("test123");
        WebElement confirmPassword = driver.findElement(By.name("confirmpassword"));
        confirmPassword.sendKeys("test123");
        WebElement signUpBttn = driver.findElement(By.xpath("//button[contains(@class, 'signupbtn')]"));
        signUpBttn.click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertEquals(heading.getText(), "Hi, Marcin Testerski");
    }


    @Test
    public void signUpEmptyFormTest() {
        WebElement myAccBttn = driver.findElements(By.xpath("//li[@id='li_myaccount']")).get(1);
        myAccBttn.click();
        WebElement sngUpBttn = driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1);
        sngUpBttn.click();
        WebElement signUpBttn = driver.findElement(By.xpath("//button[contains(@class, 'signupbtn')]"));
        signUpBttn.click();

        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                .stream().map(WebElement::getText).toList();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
    }


    @Test
    public void signUpInvalidEmailTest() {
        WebElement myAccBttn = driver.findElements(By.xpath("//li[@id='li_myaccount']")).get(1);
        myAccBttn.click();
        WebElement sngUpBttn = driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1);
        sngUpBttn.click();

        int randomNumber = (int) (Math.random()*10000);
        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.sendKeys("Marcin");
        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("Testerski");
        WebElement phoneNumber = driver.findElement(By.name("phone"));
        phoneNumber.sendKeys("123456789");
        WebElement emailAddress = driver.findElement(By.name("email"));
        emailAddress.sendKeys("test"+randomNumber);
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("test123");
        WebElement confirmPassword = driver.findElement(By.name("confirmpassword"));
        confirmPassword.sendKeys("test123");
        WebElement signUpBttn = driver.findElement(By.xpath("//button[contains(@class, 'signupbtn')]"));
        signUpBttn.click();

        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                .stream().map(WebElement::getText).toList();

        Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));
    }
}