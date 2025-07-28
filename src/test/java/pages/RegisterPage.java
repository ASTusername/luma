package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;


public class RegisterPage {

    private SelenideElement firstNameInput = $("#firstname");
    private SelenideElement lastNameInput = $("#lastname");
    private SelenideElement emailInput = $("#email_address");
    private SelenideElement passwordInput = $("#password");
    private SelenideElement confirmPasswordInput = $("#password-confirmation");
    private SelenideElement submitButton = $("button.action.submit.primary");

    public RegisterPage openPage() {
        open("customer/account/create/");
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
}
