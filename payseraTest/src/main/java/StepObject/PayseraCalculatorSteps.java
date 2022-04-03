package StepObject;

import PageObjec.PayseraCalculatorElements;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class PayseraCalculatorSteps {
    PayseraCalculatorElements elements ;
    public PayseraCalculatorSteps(){
        elements = new PayseraCalculatorElements();
    }
    public void scrollToInputs(){
        elements.buyInput.scrollIntoView(true);
    }
    public void fillSellInput(String amount){
        elements.sellInput.sendKeys(amount);
    }
    public void fillBuyInput(String amount){
        elements.buyInput.sendKeys(amount);
    }
    public void checkSellInput(){
        Assert.assertEquals(elements.sellInput.text(),"");
    }
    public void checkBuyInput(){
        Assert.assertEquals(elements.sellInput.text(),"");
    }
    public void changeCountry(String countryName){
        elements.changeCountryButton.scrollIntoView(false);
        elements.changeCountryButton.click();
        elements.countryButton.click();
        elements.countryByName(countryName).click();
        scrollToInputs();
        elements.tableRows.get(0).shouldBe(Condition.visible,Duration.ofSeconds(7000));

    }
    public int getRateIndex(){
        return  elements.tableHeaders.indexOf(elements.payseraRate);
    }
    public int getPayseraAmountIndex(){
        return  elements.tableHeaders.indexOf(elements.payseraAmount);
    }

    public int getCurrencyIndex(){
        return  elements.tableHeaders.indexOf(elements.currency);
    }
    public int getSwedbankIndex(){
        return  elements.tableHeaders.indexOf(elements.swedbankAmount);
    }
    public Map<String,String> getRateCurrencyMap(){
        Map<String,String> currencyRatesAndValues = new HashMap<>();
        elements.tableRows.get(0).shouldBe(Condition.visible,Duration.ofSeconds(10000));
        for (SelenideElement el : elements.tableRows){
            currencyRatesAndValues.put(el.$$(By.tagName("td")).get(getCurrencyIndex()).text(),
                    el.$$(By.tagName("td")).get(getRateIndex()).text());
        }
        return currencyRatesAndValues;
    }
    public void checkRateBankDifference(){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        elements.tableRows.get(0).shouldBe(Condition.visible,Duration.ofSeconds(10000));
        double payseraAmount,swedBankAmount;
        int size;
        for (SelenideElement el : elements.tableRows){
            if(!el.$$(By.tagName("td")).get(getSwedbankIndex()).text().equals("-")  ){
                payseraAmount = Double.parseDouble(el.$$(By.tagName("td")).get(getPayseraAmountIndex()).text().replace(",",""));
                swedBankAmount = Double.parseDouble(el.$$(By.tagName("td")).get(getSwedbankIndex()).$(By.className("ng-binding")).text().replace(",",""));
                if((payseraAmount-swedBankAmount)>0) {
                    size=el.$$(By.tagName("td")).get(getSwedbankIndex()).$(By.xpath("span/span/span[2]")).text().length();
                    Assert.assertEquals(decimalFormat.format(swedBankAmount-payseraAmount),el.$$(By.tagName("td")).get(getSwedbankIndex()).$(By.xpath("span/span/span[2]")).text().substring(0,size).substring(1,size-1));
                }
          }

        }
    }
    public void checkSellCurrency(String currency){
        Assert.assertEquals(currency,elements.sellCurrency.text());
    }
    public void checkPayseraRates( Map<String,String> fMap, Map<String,String> sMap){
        fMap.forEach((key,value)->
                Assert.assertNotEquals(sMap.get(key),value));

    }
}
