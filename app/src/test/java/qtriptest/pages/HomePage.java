
package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    @FindBy(xpath = "//div[@class='nav-link login register']")
    WebElement logout_Button;

    @FindBy(className = "hero-input")
    WebElement search_Text_Box;

    @FindBy(id = "results")
    WebElement no_City_Found_Message;

    @FindBy(className = "tile-text text-center")
    WebElement list_Of_Cities_Present;
    
    @FindBy(xpath = "//ul[@id='results']/a/li")
    WebElement city_Auto_Complete_Text;

    

    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);

    }

    public void navigateToHomePage(){
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(this.url);   
        }
    }


    public Boolean isUserLoggedIn(){
        try {
            // Find the LOGOUT button present on the top right of the page 
            return logout_Button.isDisplayed();
        } catch (Exception e) {
            return false;
        }           
    }   
    

    public void logOutUser(){
        if(logout_Button.isDisplayed()){
            logout_Button.click();
        }
    }

    public void clickRegister(){
        // if(register_button.isDisplayed()){
        //     register_button.click();    
    }
    

    public void searchCity(String cityName){
        search_Text_Box.clear();
        search_Text_Box.sendKeys(cityName);
    }

     /*
     * Returns Boolean based on if the "No city found" text is displayed
     */
    public Boolean assertAutoCompleteText(String cityName) {
        Boolean status = false;
        try {
            if(list_Of_Cities_Present.equals(cityName)){
                status = city_Auto_Complete_Text.isDisplayed();
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    public Boolean isNoCityFound() {
        Boolean status = false;
        try {
              //Thread.sleep(5000);
            status = no_City_Found_Message.isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    public void selectCity(String city){
          // Thread.sleep(3000);
            Actions a = new Actions(driver);
            a.moveToElement(city_Auto_Complete_Text).click().build().perform();
         
    }
}
