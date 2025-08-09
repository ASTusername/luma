package tests;

import data.TestData;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;
import runner.BaseTest;
import org.junit.jupiter.api.extension.ExtendWith;
import helpers.ConsoleTestLogger;

@ExtendWith(ConsoleTestLogger.class)
@Tag("registration")
public class RegistrationTestLuma extends BaseTest { // что-то
    RegisterPage registerPage = new RegisterPage();
    TestData testData = new TestData();

        @Test
        @Owner("Denis Nikitin | tg: @nikk113")
        @Severity(SeverityLevel.NORMAL)
        public void successfulRegistrationTest() {
            registerPage.openPage()
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setEmail(testData.email)
                    .setPassword(testData.password)
                    .setConfirmPassword(testData.password)
                    .submit();

            registerPage.shouldSeeSuccessMessage("Thank you for registering with Main Website Store.");
        }

        @Test
        @Owner("Denis Nikitin | tg: @nikk113")
        @Severity(SeverityLevel.NORMAL)
        public void registrationEmptyFieldsTest() {
            registerPage.openPage()
                    .setFirstName("")
                    .setLastName("")
                    .setEmail("")
                    .setPassword("")
                    .setConfirmPassword("")
                    .submit();

            registerPage.shouldSeeValidationError("This is a required field.");
        }

        @Test
        @Owner("Denis Nikitin | tg: @nikk113")
        @Severity(SeverityLevel.NORMAL)
        public void registrationWithInvalidPasswordTest() {
            String invalidPassword = "123"; // слишком короткий пароль

            registerPage.openPage()
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setEmail("testuser" + System.currentTimeMillis() + "@mail.ru")
                    .setPassword(invalidPassword)
                    .setConfirmPassword(invalidPassword)
                    .submit();

            registerPage.shouldSeeValidationError("Minimum length of this field must be equal or greater than 8 symbols.");
        }
    }
