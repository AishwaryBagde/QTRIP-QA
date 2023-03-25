package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_02 {

    static RemoteWebDriver driver;


    @BeforeTest(alwaysRun = true)
    public void setup() {
       DriverSingleton driverSingleton =  DriverSingleton.getInstanceOfSingletonBrowserClass();
       driver = driverSingleton.getDriver();
    }

    @Test(description ="verify Search and Filters work fine in QTrip",priority = 2,groups="Search and Filter flow",dataProvider="qtripDataProvider",dataProviderClass = DP.class)
    public void TestCase02(String cityName, String category_Filter, String duration_Filter,
     String expectedFilterResult, String expectedUnfilterResult )throws InterruptedException, MalformedURLException{
    
    HomePage homePage = new HomePage(driver);
    homePage.navigateToHomePage();
    Thread.sleep(1000);

    homePage.searchCity(cityName);
    homePage.isNoCityFound();
    Thread.sleep(2000);

    homePage.searchCity(cityName);
    homePage.assertAutoCompleteText(cityName);
    Thread.sleep(2000);

    homePage.selectCity(cityName);
    Thread.sleep(2000);

    AdventurePage adventurePage = new AdventurePage(driver);
    //Verify without Filter
    adventurePage.clearFilterValue();
    adventurePage.getResultCount(expectedUnfilterResult);
    adventurePage.setFilterValue(duration_Filter);
    adventurePage.setCategoryValue(category_Filter);
    

       //Verify with Filter
    adventurePage.getResultCount(expectedFilterResult);
    
    }
    
    @AfterTest()
    public void tearDown() {
       driver.quit();
   }
}
