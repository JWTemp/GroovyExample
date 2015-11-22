import javax.swing.plaf.basic.BasicSplitPaneDivider.DividerLayout;

import static org.junit.Assert.*

import org.junit.BeforeClass
import org.junit.AfterClass
import org.junit.Test

import java.util.concurrent.TimeUnit

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.firefox.FirefoxDriver

public class WebsiteTestCase  {
	static final url = "http://guardian.mashery.com/"
	static FirefoxDriver driver
	@BeforeClass
	static void setUp() { 
		driver = new FirefoxDriver() 
		driver.get(url)
	}
	@AfterClass
	static void tearDown() { driver.quit() }
	@Test
	public void basicTitleTest() {
		assertEquals('Guardian Content API - Getting Started', driver.title)
	}
	@Test
	public void testRegisterLink() {
		driver.findElementByLinkText("Register").click()
		assertEquals('Guardian Content API', driver.title)
		assert driver.findElementByXPath("//form[@id='member-register']")
	}
	@Test
	public void testSignInForm() {
		driver.findElementByLinkText("Sign In").click()
		assertEquals('Guardian Secure Login', driver.title)
		driver.findElement(By.id("handle")).sendKeys("JWTest")
		driver.findElement(By.name("passwd")).sendKeys("w0lv3rh4mpt0n")
		driver.findElement(By.id("process-login")).click()
		assertEquals('Signed in as JWTest', driver.findElementByXPath("//*[@id='user-nav']/ul/li[contains(@class,'status')]").text)
		driver.findElementByLinkText("Sign Out").click()
	}
}