package tests;

import data.TestData;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;
import pages.CustomerLoginPage;
import pages.RecoveryPassPage;
import pages.RegisterPage;
import runner.BaseTest;

public class RecoveryPassTest extends BaseTest { // что-то
    RecoveryPassPage recoveryPage = new RecoveryPassPage();
    CustomerLoginPage customerLoginPage = new CustomerLoginPage();
    TestData testData = new TestData();

    @Test
    @Owner("Alex Alex | tg: @")
    @Severity(SeverityLevel.NORMAL)
    public void successfulRecoveryPassTest() {
        recoveryPage.openPage()
                .setEmail(testData.emailPassRecovery)
                .submit();

        customerLoginPage.checkTitleText("Customer Login");
        customerLoginPage.checkMessageText(
                "If there is an account associated with " + testData.emailPassRecovery +
                        " you will receive an email with a link to reset your password."
        );
    }

    @Test
    @Owner("Alex Alex | tg: @")
    @Severity(SeverityLevel.NORMAL)
    public void successfulNavigationToRecoveryPassTest() {
        customerLoginPage.openPage()
                .checkTitleText("Customer Login");
        customerLoginPage.clickForgotPassword()
                .checkPageOpened();
    }
}