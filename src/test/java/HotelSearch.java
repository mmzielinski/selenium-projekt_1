import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class HotelSearch {

    @Test
    public void searchHotel() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/");

        //set city name
        WebElement searchField = driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']"));
        searchField.click();
        WebElement inputField = driver.findElement(By.xpath("//div[@id='select2-drop']//input"));
        inputField.sendKeys("Dubai");
        WebElement textInput = driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']"));
        textInput.click();

        //set check in & check out
        WebElement checkIn = driver.findElement(By.name("checkin"));
        checkIn.click();
        WebElement setDate = driver.findElement(By.name("checkin"));
        setDate.sendKeys("21/03/2024");

        WebElement checkOut = driver.findElement(By.name("checkout"));
        checkOut.click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='29']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        //set travellers
        WebElement travellers = driver.findElement(By.id("travellersInput"));
        travellers.click();
        WebElement adultsPlusButton = driver.findElement(By.id("adultPlusBtn"));
        adultsPlusButton.click();
        WebElement childPlusButton = driver.findElement(By.id("childPlusBtn"));
        childPlusButton.click();

        //set search & assert
        WebElement searchButton = driver.findElement(By.xpath("//button[text()=' Search']"));
        searchButton.click();

        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class, 'list_title')]//b"))
                .stream().map(element -> element.getAttribute("textContent")).toList();

        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));
    }
}