package ExampleTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IExecutionListener;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TestNG_ExampleTests extends BaseTest implements IExecutionListener {
    public static String _status = "passed";

    @Override
    public void onExecutionStart() {
        System.out.println("onExecutionStart");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("onExecutionEnd");
    }

    @Test(priority = 1, groups = {"SearchTests"})
    @Parameters({"environment"})
    public void test_GoogleSearch(String environment) throws InterruptedException, MalformedURLException, MalformedURLException {
        String search_string = " UNIR";
        String exp_title = "Maestrías en Línea Oficiales SEP - Landings UNIR";

        if (environment.equalsIgnoreCase("local")) {
            setupThread("chrome");
        }
        WebDriver webdriver = getDriver();
        webdriver.navigate().to("https://www.google.com");
        webdriver.manage().window().maximize();
        System.out.println("Started session");

        try {
            /* Enter the search term in the Google Search Box */
            WebElement search_box = webdriver.findElement(By.xpath("//input[@name='q']"));
            search_box.sendKeys(search_string);

            search_box.submit();
            Thread.sleep(3000);

            /* Click on the first result which will open up the LambdaTest homepage */
//            WebElement lt_link = webdriver.findElement(By.xpath("//h1[.='Maestrías en Línea Oficiales SEP - Landings UNIR']"));
//            lt_link.click();
            Thread.sleep(9000);

            String curr_window_title = webdriver.getTitle();
            Assert.assertEquals(curr_window_title, exp_title);
            _status = "passed";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (getDriver() != null)
        {
            tearDownDriver();
        }
    }


}
