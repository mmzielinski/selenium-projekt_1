package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;


public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Marcin");
        signUpPage.setLastName("Testerski");
        signUpPage.setPhone("123456789");
        int randomNumber = (int) (Math.random()*10000);
        signUpPage.setEmail("test"+randomNumber+"@testerski.pl");
        signUpPage.setPassword("test123");
        signUpPage.confirmPassword("test123");
        signUpPage.signUpButton();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        loggedUserPage.getHeadingText();
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Marcin Testerski");
    }


    @Test
    public void signUpTest2() {
        int randomNumber = (int) (Math.random()*10000);
        String email = "test" + randomNumber + "@testerski.pl";

        User user = new User();
        user.setFirstName("Marcin");
        user.setLastName("Testerski");
        user.setPhone("123456789");
        user.setEmail(email);
        user.setPassword("test123");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        //signUpPage.fillSignUpForm("Marcin", "Testerski", "123456789",email, "test123");
        signUpPage.fillSignUpForm2(user);

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        loggedUserPage.getHeadingText();
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Marcin Testerski");
    }


    @Test
    public void signUpEmptyFormTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUpButton();

        List<String> errors = signUpPage.getErrors();

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
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Marcin");
        signUpPage.setLastName("Testerski");
        signUpPage.setPhone("123456789");
        signUpPage.setEmail("test");
        signUpPage.setPassword("test123");
        signUpPage.confirmPassword("test123");
        signUpPage.signUpButton();

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));
    }
}