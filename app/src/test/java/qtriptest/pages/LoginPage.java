package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    
    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    @FindBy(id = "floatingInput")
    WebElement userName_Text_Box;

    @FindBy(id = "floatingPassword")
    WebElement password_Text_Box;
    
    @FindBy(xpath = "//h1")
    WebElement loginPage_Text;

    @FindBy(className = "btn-login")
    WebElement login_Button;


    public LoginPage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        //PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);

    }

    public void navigateToLoginPage(){
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(this.url); 
        }
    }

    public Boolean performLogin(String username, String password) throws InterruptedException{

        userName_Text_Box.sendKeys(username);
        password_Text_Box.sendKeys(password);
        login_Button.click();

        Thread.sleep(6000);
        return this.VerifyUserLoggedIn();
    }
        // WebDriverWait wait = new WebDriverWait(driver, 20);
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete")));

            // try{
            //     return this.homePage_label_message.getText().equals(message); 
            //     //return homePage_label_message.getText().equals("Explore the world with fantastic places to venture around");
            //    } catch(Exception e){
            //     return false;
            //    } 

    public Boolean VerifyUserLoggedIn(){
        
        try{
         return loginPage_Text.isDisplayed();
        } catch(Exception e){
            return false;
        }
    }
}
