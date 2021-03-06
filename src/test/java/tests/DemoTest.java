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
import pageObjects.HomePage;
import pageObjects.ResearchPage;
import utilities.RandomData;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoTest {
    FakeValuesService faker = new FakeValuesService(new Locale("en-US"), new RandomService());
    private WebDriver driver;
    @BeforeEach
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        this.driver = RunEnvironment.getWebDriver();
        driver.get("https://avaliacao.dextra.tech/");
    }

    @Test
    public void accessingResearchPage() {
        HomePage homePage = new HomePage(RunEnvironment.getWebDriver());
        ResearchPage researchPage = homePage.clickOnResearchButton();
        Assertions.assertTrue(researchPage.researchPageTitleVisibility());
    }

    @Test
    public void fulfillResearch() throws InterruptedException{
        HomePage homePage = new HomePage(RunEnvironment.getWebDriver());
        ResearchPage researchPage = homePage.clickOnResearchButton();
        Assertions.assertTrue(researchPage.researchPageTitleVisibility());
        researchPage.setName(faker.letterify("??????????"));
        researchPage.setLastName(faker.letterify("??????????"));
        String email = faker.letterify("???????@emailteste.com");
        researchPage.setEmail(email);
        researchPage.setConfirmEmail(email);
        researchPage.setPhoneNumber(faker.numerify("(###)###-###-###"));
        researchPage.setAge(RandomData.randomNumber(4,0));
        researchPage.setExperienceTime(RandomData.randomNumber(3,1));
        researchPage.setAutomatedTestsOptions(RandomData.randomNumber(4,0), RandomData.randomNumber(4,0));
        researchPage.setKnownTechnologies(faker.letterify("??????????, ??????????, ???????????"));
        researchPage.setExpertiseReating(RandomData.randomNumber(9,0));
        researchPage.clickSubmitButton();
        Assertions.assertEquals("Obrigado por responder nossa pesquisa. Agradecemos sua participa????o!", researchPage.getFeedbackMessage());
        Thread.sleep(2000);
    }

    @AfterEach
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
