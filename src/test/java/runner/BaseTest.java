package runner;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import helpers.ConsoleTestLogger;

import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public abstract class BaseTest {

    @BeforeAll
    static void beforeAll() {
        Logger logger = LoggerFactory.getLogger(BaseTest.class);

        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://magento.softwaretestingboard.com/";
        Configuration.pageLoadStrategy = "eager";

        // Проверяем системное свойство
        String remoteDriver = System.getProperty("remoteDriver");
        if ("selenoid".equalsIgnoreCase(remoteDriver)) {
            logger.info("========== Remote driver detected ==========");
            Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
        else {
            logger.info("========== No remote driver detected ==========");
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void logTestStart(TestInfo info) {
        String className = info.getTestClass()
                .map(Class::getSimpleName)
                .orElse("UnknownClass");

        String methodName = info.getTestMethod()
                .map(method -> method.getName() + "()")
                .orElse("UnknownTest");

        ConsoleTestLogger.logStart(className + "." + methodName);
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("----- Running afterAll in BaseTest -----");
        helpers.ConsoleTestLogger.printSummary();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last Screen");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}