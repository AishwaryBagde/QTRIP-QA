
package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HistoryPage {
     RemoteWebDriver driver;
    
    @FindBy(xpath = "//strong[contains(text(),'here')]")
    WebElement reservation_Page;

    @FindBy(xpath = "//table/tbody/tr/th")
    List<WebElement> transcation_Id_Details;

    @FindBy(className = "cancel-button")
    WebElement cancel_Adventure_Button;
    
    @FindBy(xpath = "//div[@id='no-reservation-banner']")
    WebElement no_Reservation_message;

    public HistoryPage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public List<WebElement> noOfReservation(){
        reservation_Page.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfAllElements(transcation_Id_Details));
        return transcation_Id_Details;
    }

    public void cancelReservation(String transactions)throws InterruptedException{
        for(int j=0; j< transcation_Id_Details.size();j++){
            if(transcation_Id_Details.get(j).getText().equals(transactions)){
                cancel_Adventure_Button.click();
            }
        }
    }


}