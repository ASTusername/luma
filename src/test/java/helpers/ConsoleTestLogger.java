package helpers;


import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleTestLogger implements TestWatcher {
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

    // Список для хранения результатов
    private static final Map<String, String> testResults = new LinkedHashMap<>();

    @Override
    public void testSuccessful(ExtensionContext context) {
        String testName = context.getDisplayName();
        testResults.put(testName, "PASSED");
        System.out.println(GREEN + "[PASS] Test passed: " + testName + RESET);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        testResults.put(testName, "FAILED");
        System.out.println(RED + "[FAIL] Test failed: " + testName + RESET);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        String testName = context.getDisplayName();
        testResults.put(testName, "SKIPPED");
        System.out.println(YELLOW + "[SKIP] Test skipped: " + testName + RESET);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        testResults.put(testName, "ABORTED");
        System.out.println(YELLOW + "[ABORTED] Test aborted: " + testName + RESET);
    }

    // Вывести результаты в конце (вызывать вручную)
    public static void printSummary() {
        System.out.println(CYAN + "\nTest Summary:" + RESET);
        for (Map.Entry<String, String> entry : testResults.entrySet()) {
            String testName = entry.getKey();
            String status = entry.getValue();
            String color;

            switch (status) {
                case "PASSED":
                    color = GREEN;
                    break;
                case "FAILED":
                    color = RED;
                    break;
                case "SKIPPED":
                case "ABORTED":
                    color = YELLOW;
                    break;
                default:
                    color = RESET;
            }

            System.out.printf("%s - %s : %s%s%n", CYAN, color + testName + RESET, color, status + RESET);
        }
    }

    public static void logStart(String testName) {
        System.out.println(CYAN + "[START] Starting test: " + testName + RESET);
    }
}
