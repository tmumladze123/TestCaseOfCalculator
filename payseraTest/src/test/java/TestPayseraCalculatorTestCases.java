import StepObject.PayseraCalculatorSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

public class TestPayseraCalculatorTestCases {
    PayseraCalculatorSteps steps = new PayseraCalculatorSteps();

    public TestPayseraCalculatorTestCases() {
        Configuration.timeout = 10000;
        Configuration.holdBrowserOpen = true;
    }
    @BeforeClass
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tamamumladze\\Desktop\\chromedriver.exe");
        System.setProperty("selenide.browser", "Chrome");
        open("https://www.paysera.lt/v2/en-LT/fees/currency-conversion-calculator#/");

    }
    @Test(priority = 1)
    public void checkInputs(){
        steps.scrollToInputs();
        steps.fillBuyInput("100");
        steps.checkSellInput();
        steps.fillSellInput("100");
        steps.checkBuyInput();
    }
    @Test (priority = 2)
    public void checkAfterChangeCountry(){
        steps.scrollToInputs();
        Map<String,String> currencyRatesAndValuesFirstCountry = steps.getRateCurrencyMap();
        steps.changeCountry("Russia");
        steps.checkSellCurrency("RUB");
        Map<String,String> currencyRatesAndValuesAfterCountryChange = steps.getRateCurrencyMap();
        steps.checkPayseraRates(currencyRatesAndValuesFirstCountry,currencyRatesAndValuesAfterCountryChange);
    }
    @Test (priority = 3)
    public void distinctionCheck(){
        steps.changeCountry("Latvia");
        steps.checkRateBankDifference();
    }

    @AfterClass
    void closeWindow(){
        Selenide.closeWindow();
    }
}
