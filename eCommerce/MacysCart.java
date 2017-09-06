package eCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import eComm.macys.pages.BagPage;
import eComm.macys.pages.HomePage;
import eComm.macys.pages.ItemPage;
import eComm.macys.pages.ResultPage;
import eComm.macys.pages.SetUP;
import eComm.macys.pages.ShoppingBagPage;

public class MacysCart //to verify item aaded to cart and removed from cart
{
	static WebDriver driver= new FirefoxDriver();
	static WebDriverWait wait=new WebDriverWait(driver, 30);

	public static void main (String[] args) throws InterruptedException{

		//setUp
		//setup
		String url= "https://www.macys.com/";
		SetUP setup= new SetUP();
		driver =setup.setUpBrowser(url, driver);

		try
		{	//navigate to item Page
			HomePage hp= new HomePage();
			hp.navToJuniorJeans(driver);

			ResultPage rp= new ResultPage();
			rp.clickOnItem(driver);
			
			//select size and quantity
			String qt= "2";
			ItemPage ip= new ItemPage();
			ip.selectSizeAndQuantity( driver, qt);
			
			//add to bag
			ip.addToBag(driver);

			// verify no of items added in cart
			BagPage bp=new BagPage();
			bp.noOfItemsInCart(driver, qt);
			
			//view bag and checkout
			bp.viewBagAndCheckout(driver);
			
			//remove item and verify removal
			ShoppingBagPage sp= new ShoppingBagPage();
			sp.itemRemoval(driver);
		
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
