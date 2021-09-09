package tests;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.ResearchPage;

import java.util.Locale;

public class FailScenarios {
    FakeValuesService faker = new FakeValuesService(new Locale("en-US"), new RandomService());
    private WebDriver driver;
    @BeforeEach
    public void startBrowser(){
        EnvironmentManager.initWebDriver();
        this.driver= RunEnvironment.getWebDriver();
        driver.get("https://avaliacao.dextra.tech/pesquisa/");
    }

    @Test
    public void differentEmails(){
        ResearchPage researchPage = new ResearchPage(driver);
        researchPage.setName(faker.letterify("??????????"));
        researchPage.setLastName(faker.letterify("??????????"));
        researchPage.setEmail(faker.letterify("???????@emailteste.com"));
        researchPage.setConfirmEmail(faker.letterify("???????@emailteste.com"));
        researchPage.clickSubmitButton();
        Assertions.assertEquals("Field values do not match.", researchPage.getErrorMessage());
    }

    @Test
    public void emptyLastName(){
        ResearchPage researchPage = new ResearchPage(driver);
        researchPage.setName(faker.letterify("??????????"));
        String email = faker.letterify("???????@emailteste.com");
        researchPage.setEmail(email);
        researchPage.setConfirmEmail(email);
        researchPage.clickSubmitButton();
        Assertions.assertEquals("This field is required.", researchPage.getErrorMessage());
    }

    @Test
    public void emptyName(){
        ResearchPage researchPage = new ResearchPage(driver);
        researchPage.setLastName(faker.letterify("??????????"));
        String email = faker.letterify("???????@emailteste.com");
        researchPage.setEmail(email);
        researchPage.setConfirmEmail(email);
        researchPage.clickSubmitButton();
        Assertions.assertEquals("This field is required.", researchPage.getErrorMessage());
    }

    @AfterEach
    public void tearDown(){EnvironmentManager.shutDownDriver();}
}
