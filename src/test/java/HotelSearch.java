import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class HotelSearch {

    @Test
    public void searchHotel() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/");

        WebElement searchField = driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']"));
        searchField.click();
        WebElement inputField = driver.findElement(By.xpath("//div[@id='select2-drop']//input"));
        inputField.sendKeys("Dubai");
        WebElement textInput = driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']"));
        textInput.click();
    }
}