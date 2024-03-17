package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;

import java.util.List;


public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("21/03/2024", "29/03/2024");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();

        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class, 'list_title')]//b"))
                .stream().map(element -> element.getAttribute("textContent")).toList();

        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));
    }
}