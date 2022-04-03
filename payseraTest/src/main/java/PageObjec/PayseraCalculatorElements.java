package PageObjec;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PayseraCalculatorElements {
    public SelenideElement sellInput = $(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/div[1]/form/div[1]/input"));
    public SelenideElement buyInput = $(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/div[1]/form/div[3]/input"));
    public SelenideElement changeCountryButton = $(By.xpath("/html/body/footer/div[2]/div/div/div[2]/div/span"));
    public SelenideElement countryButton = $(By.id("countries-dropdown"));
    public ElementsCollection tableHeaders = $(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/table/thead")).$$(By.tagName("th"));
    public ElementsCollection tableRows = $(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/table/tbody")).$$(By.tagName("tr"));
    public SelenideElement payseraRate =$(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/table/thead//*[contains(text(),'Paysera rate')]"));
    public SelenideElement payseraAmount =$(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/table/thead/tr/th[4]"));
    public SelenideElement currency = $(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/table/thead//*[contains(text(),'Currency')]"));
    public SelenideElement swedbankAmount = $(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/table/thead//*[contains(text(),'Swedbank LV amount')]"));
    public SelenideElement sellCurrency = $(By.xpath("//*[@id=\"currency-exchange-app\"]/div/div/div[2]/div[1]/form/div[1]/div/div[1]/span/span[2]/span"));
    public SelenideElement countryByName(String countryName) {
        return $(new By.ByLinkText(countryName));
    }
}
