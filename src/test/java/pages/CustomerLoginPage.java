package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CustomerLoginPage {
    private SelenideElement pageTitle = $(".page-title");
    private SelenideElement pageMessages = $(".page.messages");
    private SelenideElement forgotPasswordLink = $(".action.remind");

    public CustomerLoginPage openPage() {
        open("customer/account/login/");
        return this;
    }
    public void checkTitleText(String expectedTitle) {
        pageTitle.shouldHave(text(expectedTitle));
    }

    public void checkMessageText(String expectedMessage) {
        pageMessages.shouldHave(text(expectedMessage));
    }

    public RecoveryPassPage clickForgotPassword() {
        forgotPasswordLink.shouldBe(visible).click();
        return new RecoveryPassPage();
    }
}
