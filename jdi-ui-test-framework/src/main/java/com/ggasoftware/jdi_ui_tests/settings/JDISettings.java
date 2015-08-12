package com.ggasoftware.jdi_ui_tests.settings;

import com.ggasoftware.jdi_ui_tests.core.drivers.DriverFactory;
import com.ggasoftware.jdi_ui_tests.core.drivers.JElement;
import com.ggasoftware.jdi_ui_tests.selenium.DriverTypes;
import com.ggasoftware.jdi_ui_tests.selenium.elements.base.SlmDriver;
import com.ggasoftware.jdi_ui_tests.asserter.IAsserter;
import com.ggasoftware.jdi_ui_tests.logger.ListLogger;
import com.ggasoftware.jdi_ui_tests.logger.Log4JLogger;
import com.ggasoftware.jdi_ui_tests.logger.base.ILogger;
import com.ggasoftware.jdi_ui_tests.asserter.TestNGAsserter;
import com.ggasoftware.jdi_ui_tests.logger.TestNGLogger;
import com.ggasoftware.jdi_ui_tests.testRunner.ITestRunner;
import com.ggasoftware.jdi_ui_tests.testRunner.TestNGRunner;
import com.ggasoftware.jdi_ui_tests.utils.linqInterfaces.JFuncTT;
import org.openqa.selenium.WebDriver;

import static com.ggasoftware.jdi_ui_tests.core.drivers.DriverFactory.registerDriver;
import static com.ggasoftware.jdi_ui_tests.selenium.RemoteRunner.remoteUrl;
import static com.ggasoftware.jdi_ui_tests.utils.common.PropertyReader.fillAction;
import static com.ggasoftware.jdi_ui_tests.utils.common.PropertyReader.getProperties;
import static java.lang.Integer.parseInt;

/**
 * Created by Roman_Iovlev on 6/9/2015.
 */
public class JDISettings {
    public static ILogger logger = new ListLogger(new TestNGLogger(), new Log4JLogger());
    public static IAsserter asserter = new TestNGAsserter(true);
    public static ITestRunner testRunner = new TestNGRunner();

    public static void useDriver(WebDriver driver) { registerDriver(() -> new SlmDriver(() -> driver)); }
    public static void useDriver(DriverTypes driver)  { registerDriver(() -> new SlmDriver(driver)); }

    public static TimeoutSettings timeouts = new TimeoutSettings();
    private static String jdiSettingsPath = "test.properties";
    public static boolean isDemoMode = false;
    public static HighlightSettings highlightSettings = new HighlightSettings();
    public static JFuncTT<JElement, Boolean> elementSearchCriteria = JElement::isDisplayed;

    public void setJDISettingsPath(String jdiSettingsPath) { JDISettings.jdiSettingsPath = jdiSettingsPath; }
    public void runInDemoMode() { isDemoMode = true; }

    public static void initJDIFromProperties() throws Exception {
        getProperties(jdiSettingsPath);
        fillAction("driver", DriverFactory::registerDriver);
        fillAction("run.type", SlmDriver::setRunType);
        fillAction("domain", p -> domain = p);
        fillAction("remote.url", p -> remoteUrl = p);
        fillAction("timeout.wait.element.sec", p -> timeouts.waitElementSec = parseInt(p));
        fillAction("timeout.wait.page.sec", p -> timeouts.waitPageLoadSec = parseInt(p));
        fillAction("timeout.retry.msec", p -> timeouts.retryMSec = parseInt(p));
        fillAction("demo", p -> isDemoMode = p.toLowerCase().equals("1") || p.toLowerCase().equals("true"));
    }
    public static void initJDIFromProperties(String propertyPath) throws Exception {
        jdiSettingsPath = propertyPath;
        initJDIFromProperties();
    }

    public static String domain;
    public static boolean hasDomain() {
        return domain != null && !domain.contains("://");
    }
}
