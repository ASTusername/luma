package tests;

import data.TestData;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;
import runner.BaseTest;

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
    }
}