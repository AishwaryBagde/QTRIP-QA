package qtriptest.pages;

import java.util.List;
import java.util.ArrayList;
//import org.apache.logging.log4j.core.util.Assert;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {
    RemoteWebDriver driver;

    
    @FindBy (xpath = "//div[@class='filter-bar-tile d-flex align-items-center']/select[@id ='duration-select']")
    WebElement  duration_Dropdown;

    @FindBy (xpath = "//div[@class='filter-bar-tile d-flex align-items-center']/select[@id='category-select']")
    WebElement category_Dropdown;

    @FindBy (id = "search-adventures") 
    WebElement search_Adventure;

    @FindBy (xpath = "(//div[@class='ms-3'])[1]")
    WebElement duration_Filter_Clear_Button;

    @FindBy (xpath = "(//div[@class='ms-3'])[2]")
    WebElement category_Filter_Clear_Button;

    @FindBy (xpath = "(//div[@class='ms-3'])[3]")
    WebElement search_Filter_Clear_Button;

    @FindBy (xpath = "//div[@class='d-flex align-items-center']")
    WebElement category_label;

    @FindBy (xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    WebElement search_adventure_Detail;

    @FindBy(xpath = "//div[@class='row']/div")
    List<WebElement> adventureDetails;

    @FindBy(xpath = "//div[@class='filter-bar-tile d-flex align-items-center']/input[@id ='search-adventures']")
    WebElement searchAdventureTxtBox;


    public AdventurePage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void setCategoryValue(String category_value){
        try{
            if (verifyPresenceOfCategoryFilterDropdown()){
                category_Dropdown.click();
                selectCategoryFilterDropdownValue(category_value);
                Thread.sleep(1000);
            }
        }catch( Exception e){
            System.out.println("Unable to click on CategoryFilter Dropdown");
        }
        

    }
    public Boolean verifyPresenceOfCategoryFilterDropdown(){
        Boolean status = false;
        try{
           status = category_Dropdown.isDisplayed();
           return status;
        } catch(Exception e){
            return status;
        }

    }

    public void  selectCategoryFilterDropdownValue(String categoryFilter){
        try{
            Select select = new Select(category_Dropdown);
            List<WebElement> category = select.getOptions();
            for(int i=1;i<= category.size(); i++){
                if(category.get(i).getText().contains(categoryFilter)){
                    System.out.print("category values are :" + category.get(i).getText());
                    category.get(i).click();
                    System.out.println(category_label.getText());
                    Thread.sleep(1000);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Category Filter value not selected");
            }
    }


    public void setFilterValue(String duration_Filter ){
        try { 
            if (verifyPresenceOfDurationFilterDropdown()) {
                duration_Dropdown.click();
                selectDurationFilterDropdownValue(duration_Filter);
                Thread.sleep(1000);
            }
        } catch( Exception e){
            System.out.println("Not able to Select Duration Filter Dropdown");
        }
     }

     public Boolean verifyPresenceOfDurationFilterDropdown(){
        Boolean status = false;
        try{
            status = duration_Dropdown.isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
     }

     public void selectDurationFilterDropdownValue(String duration_Filter){

        try{
            Select select = new Select(duration_Dropdown);
            List<WebElement> duration = select.getOptions();
            for(int i=1; i<= duration.size(); i++) {
                if(duration.get(i).getText().contains(duration_Filter)) {
                    System.out.println("values of dropdown are:"+ duration.get(i).getText());
                    duration.get(i).click();
                    Thread.sleep(1000);
                    break;
                }
            } 
        } catch (Exception e) {
        System.out.println("Duration filter value not selected");
        }
    }

    public void clearFilterValue(){
        duration_Filter_Clear_Button.click();
        category_Filter_Clear_Button.click();
        search_Filter_Clear_Button.click(); 
    }



        public void getResultCount(String expectedFilterResult){
            int actualSize = adventureDetails.size();
            int expectedSize = Integer.parseInt(expectedFilterResult);
            Assert.assertEquals(actualSize, expectedSize,"Values does Not match");
        }


        public void selectAdventure(String AdventureName) throws InterruptedException{

            searchAdventureTxtBox.click();
            //WebDriverWait wait = new WebDriverWait(driver, 20);
            //wait.until(ExpectedConditions.visibilityOf(searchAdventureTxtBox));
            searchAdventureTxtBox.sendKeys(AdventureName);
            Thread.sleep(2000);
            search_adventure_Detail.click();
    
        }





}