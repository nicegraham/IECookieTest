import junit.framework.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Calendar;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IECookieTest {


  @Before
  public void setEnvVars() {
    String iePath = this.getClass().getClassLoader().getResource("IEDriverServer.exe").getPath();
    System.out.println(iePath);
    System.setProperty("webdriver.ie.driver", iePath);
  }

  @Test
  public void firefoxTest() {
    runTestSteps(new FirefoxDriver());
  }

  @Test
  public void internetExplorerTest() {
    try {
      runTestSteps(new InternetExplorerDriver());
    } catch(WebDriverException e) {
      e.printStackTrace();
      Assert.assertTrue("IE not working", false);
    }
  }

  private void runTestSteps(WebDriver driver) {
    driver.navigate().to("http://www.google.ie");

    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.HOUR, 2);
    Cookie cookie = new Cookie("myTestCookie", "Arbitrary Value", null, "/", cal.getTime(), false);
    driver.manage().addCookie(cookie);

    Assert.assertTrue("List of cookies contains my new cookie", driver.manage().getCookies().contains(cookie));

    driver.quit();
  }
}