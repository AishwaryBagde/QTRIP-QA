package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_04 {

  static RemoteWebDriver driver;
    
    @BeforeTest(alwaysRun = true)
    public void setup() {
       DriverSingleton driverSingleton =  DriverSingleton.getInstanceOfSingletonBrowserClass();
       driver = driverSingleton.getDriver();
     
    }
   @Test(description ="Verify that Booking history can be viewed",priority = 4,groups = "Reliability Flow",dataProvider="qtripDataProvider",dataProviderClass = DP.class)
     public void TestCase04(String NewUserName, String password, String dataset1, 
     String dataset2, String dataset3) throws InterruptedException, MalformedURLException{
         
        String dataSetArr [][]= new String[3][5];
        

        String[] arr1 = dataset1.split(";");
        String[] arr2 = dataset2.split(";");
        String[] arr3 = dataset3.split(";");
        for(int i = 0; i < arr1.length;i++){

         dataSetArr[0][i] = arr1[i];
         System.out.println("dataSetArr[0][i] :"+ dataSetArr[0][i]);
         dataSetArr[1][i] = arr2[i];
         System.out.println("dataSetArr[1][i] :"+ dataSetArr[1][i]);
         dataSetArr[2][i] = arr3[i];
         System.out.println("dataSetArr[2][i] :"+ dataSetArr[2][i]);
        }
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegisterPage();
        Thread.sleep(1000);
        registerPage.registerUser(NewUserName, password, password, true);
       
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.performLogin(registerPage.lastGeneratedUserName, password);  
        
        for(int i = 0; i< 3;i++){

               homePage.navigateToHomePage();
               Thread.sleep(3000);
               homePage.searchCity(dataSetArr[i][0]);
               homePage.isNoCityFound();
               Thread.sleep(3000);

               homePage.searchCity(dataSetArr[i][0]);
               homePage.assertAutoCompleteText(dataSetArr[i][0]);
               Thread.sleep(3000);
         
               homePage.selectCity(dataSetArr[i][0]);
               Thread.sleep(3000);
                  
               AdventurePage adventurePage = new AdventurePage(driver);
               adventurePage.selectAdventure(dataSetArr[i][1]);
               Thread.sleep(3000);

               AdventureDetailsPage advDetailPage = new AdventureDetailsPage(driver);
               advDetailPage.BookAdventure(dataSetArr[i][2], dataSetArr[i][3], dataSetArr[i][4]);
               advDetailPage.isBookingSuccessful();
               //Thread.sleep(1000);
               HistoryPage historyPage = new HistoryPage(driver);
               historyPage.noOfReservation();
               Thread.sleep(1000);
               
        }
        homePage.logOutUser(); 
         //}
     }

  @AfterTest()
  public void tearDown() {
     driver.quit();
 }
 }
