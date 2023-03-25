package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;


public class testCase_01 {  

    static RemoteWebDriver driver;

    //  String lastGeneratedUserName;
    //SoftAssert sa = new SoftAssert();

     // @BeforeSuite (alwaysRun = true)
    // public void createDriver() throws  MalformedURLException{
    //     final DesiredCapabilities capabilities = new DesiredCapabilities();
    //     capabilities.setBrowserName(BrowserType.CHROME);
    //     driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
    //     System.out.println("createDriver()");
    //     driver.manage().window().maximize();
    // }
    
    @BeforeTest(alwaysRun = true)
    public void setup() {
       DriverSingleton driverSingleton =  DriverSingleton.getInstanceOfSingletonBrowserClass();
       driver = driverSingleton.getDriver();
     
    }
    @Test(description ="Verify user registration - login - logout to QTrip",priority = 1,groups="Login Flow",dataProvider="qtripDataProvider",dataProviderClass = DP.class)
    public void TestCase01(String username, String password) throws InterruptedException, MalformedURLException{
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegisterPage();
        Thread.sleep(2000);
        registerPage.registerUser(username, password, password, true);
       
        LoginPage login = new LoginPage(driver);
        login.navigateToLoginPage();
        login.performLogin(registerPage.lastGeneratedUserName, password);  
        
        homePage.logOutUser();
    }

    @AfterTest()
    public void tearDown() {
        System.out.println("quit()");
        driver.quit();
    }

    //     @AfterTest()
    //     public void quitDriver() {
    //        System.out.println("quit()");
    //        driver.quit();
    //    }
    
}
