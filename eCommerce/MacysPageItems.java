package eCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import eComm.macys.pages.HomePage;
import eComm.macys.pages.Utilities;
import eComm.macys.pages.ResultPage;
import eComm.macys.pages.SetUP;

public class MacysPageItems //to verify the count of items in search result
{
	static WebDriver driver= new FirefoxDriver();
	static WebDriverWait wait= new WebDriverWait(driver, 30);

	public static void main (String[] args) throws InterruptedException
	{
		try
		{	//setup
			String url= "https://www.macys.com/";
			SetUP setup= new SetUP();
			driver =setup.setUpBrowser(url, driver);

			//navigate to Jeans Page
			HomePage hp=new HomePage();
			hp.navToJuniorJeans(driver);

			//get no. of items expected
			ResultPage rp=new ResultPage();
			int expectedCount= rp.expectedItemCount(driver);
			
			//get actual item count			
			int [] resultArray= rp.resultItemCount(driver);

			// verify if expected is same as actual
			Utilities op=new Utilities();
			op.compareExpectedActual(expectedCount, resultArray[1]);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			driver.close();
		}
	}


}