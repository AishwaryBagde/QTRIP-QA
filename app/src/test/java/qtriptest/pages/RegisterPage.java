package qtriptest.pages;

import java.util.UUID;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";

    public String lastGeneratedUserName = " ";

    @FindBy(id = "floatingInput")
    WebElement username_Text_box;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password_Text_box;

    @FindBy(xpath = "//input[@name='confirmpassword']")
    WebElement confirm_Password_Text_box;

    @FindBy(className = "btn-login")
    WebElement register_Now_Button;

    public RegisterPage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        //PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void navigateToRegisterPage(){
        if(!driver.getCurrentUrl().equals(this.url)){
            this.driver.get(this.url);
            PageFactory.initElements(driver,this);
            //PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
        }

}
    
    public Boolean registerUser(String username, String password, String confirmPassword, Boolean makeUsernameDynamic) throws InterruptedException{
      
        String test_data_username;
        if(makeUsernameDynamic){
            username = username.substring(0, username.indexOf('@'));
            System.out.print(username);
            test_data_username = username + UUID.randomUUID().toString().substring(0, 6) + "@gmail.com";
            System.out.print(test_data_username);
        }else{
            test_data_username = username;
        }

        username_Text_box.sendKeys(test_data_username);
        Thread.sleep(2000);
        password_Text_box.sendKeys(password);
        confirm_Password_Text_box.sendKeys(confirmPassword);
        register_Now_Button.click();

        this.lastGeneratedUserName = test_data_username;


        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login"));
        return this.driver.getCurrentUrl().endsWith("/login");

    }


    
}
