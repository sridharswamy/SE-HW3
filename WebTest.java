package selenium.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;


public class WebTest
{
	private static WebDriver driver;
	
	@BeforeClass
	public static void setUp() throws Exception 
	{
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public static void  tearDown() throws Exception
	{
		driver.close();
		driver.quit();
	}

	
	@Test
	public void googleExists() throws Exception
	{
		driver.get("http://www.google.com");
        assertEquals("Google", driver.getTitle());		
	}
	

	@Test
	public void Closed() throws Exception
	{
		driver.get("http://www.checkbox.io/studies.html");

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='CLOSED']")));
		List<WebElement> spans = driver.findElements(By.xpath("//a[@class='status']/span[.='CLOSED']"));

		assertNotNull(spans);
		assertEquals(5, spans.size());
	}
	
	

	@Test
	public void frustrationOfSD() throws Exception
	{
		driver.get("http://www.checkbox.io/studies.html");		

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Frustration of Software Developers']/../../following-sibling::div//span[@class='backers']")));
		WebElement frustrationSD = driver.findElement(By.xpath("//span[.='Frustration of Software Developers']/../../following-sibling::div//span[@class='backers']"));
		
		int participantCount = Integer.parseInt(frustrationSD.getText());			
		
		assertNotNull(frustrationSD);
		assertEquals(55, participantCount);
	}
	
	
	@Test
	public void studyOpenParticipateClick() throws Exception
	{
		driver.get("http://www.checkbox.io/studies.html");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='OPEN']/../following-sibling::div/button")));
		List<WebElement> openStudies = driver.findElements(By.xpath("//a[@class='status']/span[.='OPEN']/../following-sibling::div/button"));
		
		assertNotNull(openStudies);
		for(WebElement participate: openStudies)	
		{			
			    assertTrue(participate.isDisplayed());
			    assertTrue(participate.isEnabled());
			    participate.click();
		}
	}
	
	@Test
	public void amazonRewardSoftwareChanges() throws Exception
	{
		driver.get("http://www.checkbox.io/studies.html");
	
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='OPEN']/../following-sibling::div/button")));
		List<WebElement> amazonRewardImage = driver.findElements(By.xpath("//a[@class='status']/span[.='OPEN']/../following-sibling::div/button"));
	}	
}
