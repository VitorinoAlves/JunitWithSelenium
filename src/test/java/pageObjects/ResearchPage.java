package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResearchPage {
    private WebDriver driver;
    JavascriptExecutor js;

    @FindBy(css = ".entry-title")
    private WebElement pageTitle;

    @FindBy(xpath = "//input[@placeholder=\"Nome\"]")
    private WebElement nameField;

    @FindBy(xpath = "//input[@placeholder=\"Sobrenome\"]")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@placeholder=\"Email\"]")
    private WebElement emailField;

    @FindBy(xpath = "//input[@placeholder=\"Confirmar Email\"]")
    private WebElement confirmEmailField;

    @FindBy(xpath = "//label[contains(text(),\"Telefone\")]/../input")
    private WebElement phoneField;

    @FindBy(xpath = "//div[@class=\"choices__inner\"]")
    private WebElement experienceTimeFiled;

    @FindBy(xpath = "//div[@class=\"choices__list\"]//div")
    private List<WebElement> experienceTimeOptions;

    @FindBy(xpath = "//input[@type=\"radio\"]")
    private List<WebElement> ageOptions;

    @FindBy(xpath = "//label[contains(text(),\"Com quais tipos de testes\")]/..//input")
    private List<WebElement> automatedTestsOptions;

    @FindBy(xpath = "//input[@placeholder=\"Ex.: Java, JavaScript, Python ...\"]")
    private WebElement knownTechnologies;

    @FindBy(xpath = "//label[contains(text(),\"De 1 a 10 como você avalia\")]")
    private WebElement expertiseReating;

    @FindBy(xpath = "//input[@type=\"range\"]")
    private WebElement expertiseReatingSlider;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement submitButton;

    @FindBy(xpath = "//p")
    private WebElement feedbackMessage;

    public ResearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public boolean researchPageTitleVisibility(){
        return pageTitle.getText().contains("PESQUISA");
    }

    public void setName(String name){
        nameField.sendKeys(name);
    }
    public void setLastName(String lastName){
        lastNameField.sendKeys(lastName);
    }
    public void setEmail(String email){
        emailField.sendKeys(email);
    }
    public void setConfirmEmail(String confirmEmail){
        confirmEmailField.sendKeys(confirmEmail);
    }

    public void setPhoneNumber(String phoneNumber){
        phoneField.sendKeys(phoneNumber);
    }

    public void setAge(double option){
        js.executeScript("arguments[0].scrollIntoView();", ageOptions.get((int)option));
        ageOptions.get((int)option).click();
    }

    public void setExperienceTime(double option){
        //Scroll until the web element
        js.executeScript("arguments[0].scrollIntoView();", experienceTimeFiled);
        experienceTimeFiled.click();
        experienceTimeOptions.get((int) option).click();
    }

    public void setAutomatedTestsOptions(double optionOne, double optionTwo){
        js.executeScript("arguments[0].scrollIntoView();", automatedTestsOptions.get((int)optionOne));
        automatedTestsOptions.get((int)optionOne).click();
        automatedTestsOptions.get((int)optionTwo).click();
    }

    public void setKnownTechnologies(String technologies){
        knownTechnologies.sendKeys(technologies);
    }

    public void setExpertiseReating(double reate){
        int width = expertiseReatingSlider.getSize().getWidth();
        Actions move = new Actions(driver);
        move.moveToElement(expertiseReatingSlider, ((width*(int)reate)/100), 0).click();
        move.build().perform();
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public String getFeedbackMessage(){
        return feedbackMessage.getText();
    }

}
