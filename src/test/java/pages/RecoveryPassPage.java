package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RecoveryPassPage {
    private  SelenideElement pageTitle = $(".page-title");
    private SelenideElement emailInput = $("#email_address");
    private SelenideElement submitButton = $("button.action.submit.primary");

    public RecoveryPassPage openPage() {
        open("customer/account/forgotpassword/");
        return this;
    }

    public RecoveryPassPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public void submit() {
        submitButton.click();
    }

    public RecoveryPassPage checkPageOpened() {
        pageTitle.shouldHave(text("Forgot Your Password?"));
        emailInput.shouldBe(visible).shouldBe(enabled).shouldHave(attribute("aria-required"));
        return this;
    }
}
