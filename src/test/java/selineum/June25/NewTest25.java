package selineum.June25;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import june25mon.LoginPageObject;
import utility.ExcelUtility;

public class NewTest25 {
	
	private WebDriver driver;
  @Test(priority=1, dataProvider="loginData")
  public void login(String txtusername,String txtpassword) {
	  LoginPageObject.uname.sendKeys(txtusername);
	//driver.findElement(By.name("userName")).sendKeys("invalidUN");
	  LoginPageObject.psw.sendKeys(txtpassword);
	//driver.findElement(By.name("password")).sendKeys("invalidPW");
	  LoginPageObject.login_button.click();
	//driver.findElement(By.name("login")).click();
	  Assert.assertEquals("Find a Flight: Mercury Tours:", driver.getTitle());
	  
  }
  
  @Test(priority=2)
  public void findFlight()
  {
	  Select pass=new Select(driver.findElement(By.name("passCount"))); 
	  pass.selectByValue("2");
	  
	  
	  Select departing =new Select(driver.findElement(By.name("fromPort")));
	  departing.selectByValue("London");
	  
	  Select  toPort=new Select(driver.findElement(By.name("toPort")));
	  toPort.selectByValue("New York");
	  
	  driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/font/font")).click();
	  
	  Select airline=new Select(driver.findElement(By.name("airline")));
	  airline.selectByValue("Unified Airlines");
	
	  driver.findElement(By.name("findFlights")).click();
	  
  }
  
	  
	   @DataProvider(name="loginData")
	   public String[][] login_data()throws Exception 
	  {
		  
		  ExcelUtility.setExcelPath("Sheet1","C:\\Users\\A06438_P5.Training\\Documents\\Book1.xlsx");
		  String username=ExcelUtility.getCellData(1,1);
		  String password=ExcelUtility.getCellData(1,2);
		  
		  return new String[][] {
				  new String[] {username,password},
	  };
		  
		  
	  }
	  
  
  @BeforeTest
  public void beforeTest() {
	  
	  System.setProperty("webdriver.gecko.driver","C:\\SeleniumJune\\Selenium Drivers\\geckodriver-v0.20.1-win64\\geckodriver.exe");
	  driver=new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get("http://newtours.demoaut.com/");
	  
	  PageFactory.initElements(driver, LoginPageObject.class);
  
  }

  @AfterTest
  public void afterTest() {
  }

}
