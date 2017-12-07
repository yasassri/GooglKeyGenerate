package com.wso2.iamtest;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class googleAPIcreation {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    //System.setProperty("webdriver.gecko.driver", "/test/resources/geckodriver");
    //System.setProperty("webdriver.firefox.marionette","/test/resources/geckodriver");
    System.setProperty("webdriver.chrome.driver", "/test/resources/chromedriver");
    driver = new ChromeDriver();
    //baseUrl = "https://www.katalon.com/";

    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitledTestCase() throws Exception {
    driver.get("https://accounts.google.com/signin/v2/identifier?service=cloudconsole&passive=1209600&osid=1&continue=https%3A%2F%2Fconsole.developers.google.com%2Fapis%2F&followup=https%3A%2F%2Fconsole.developers.google.com%2Fapis%2F&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
    driver.findElement(By.id("identifierId")).clear();
    driver.findElement(By.id("identifierId")).sendKeys("username");
    if(driver.findElement(By.id("identifierNext")).isDisplayed()){
      System.out.println("VISBLE-========");
    }
    WebElement link = driver.findElement(By.id("identifierId"));
    Actions builder = new Actions(driver);
    builder.moveToElement(link).click();
    builder.perform();

    String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('onmouseover');}";

    String onClickScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('click', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('onclick');}";
    //((JavascriptExecutor)driver).executeScript("document.getElementById('identifierNext').click()");

    //((JavascriptExecutor)driver).executeScript(mouseOverScript, link);
    //((JavascriptExecutor)driver).executeScript(onClickScript, link);
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    //executor.executeScript("arguments[0].click();", link);
    //executor.executeScript("window.document.getElementById('identifierId').click()");
    executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", link);
    Thread.sleep(3000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("password");
    driver.findElement(By.xpath("//div[@id='passwordNext']/content/span")).click();
    driver.findElement(By.xpath("//a[@id='p6ntest-vulcan-leftnav-credentials']/span")).click();
    driver.findElement(By.linkText("Web client 2")).click();
    driver.findElement(By.xpath("//li[2]")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
