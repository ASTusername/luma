package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class RegisterPage {

    private SelenideElement firstNameInput = $("#firstname");
    private SelenideElement lastNameInput = $("#lastname");
    private SelenideElement emailInput = $("#email_address");
    private SelenideElement passwordInput = $("#password");
    private SelenideElement confirmPasswordInput = $("#password-confirmation");
    private SelenideElement submitButton = $("button.action.submit.primary");
    private SelenideElement successMessage = $(".message-success");
    private ElementsCollection validationErrors = $$(".mage-error");

    public RegisterPage openPage() {
        open("customer/account/create/");
        acceptCookies();
        return this;
    }

    public RegisterPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegisterPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegisterPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegisterPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public RegisterPage setConfirmPassword(String password) {
        confirmPasswordInput.setValue(password);
        return this;
    }

    public void submit() {
        submitButton.click();
    }
    public void shouldSeeSuccessMessage(String expectedText) {
        successMessage.shouldHave(Condition.text(expectedText));
    }

    public void shouldSeeValidationError(String expectedText) {
        validationErrors.findBy(Condition.text(expectedText))
                .shouldBe(Condition.visible);
    }

    public RegisterPage acceptCookies() {
        // Попробуй сначала по тексту кнопки
        $x("//button[contains(., 'Consent')]").shouldBe(visible).click();

        // Если не сработает — можно по роли и тексту
        // $$("button").findBy(text("Consent")).click();
        return this;
    }
}
