package eCommerce;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import eComm.macys.pages.AccountPage;
import eComm.macys.pages.BagPage;
import eComm.macys.pages.HomePage;
import eComm.macys.pages.ItemPage;
import eComm.macys.pages.PlaceOrderPage;
import eComm.macys.pages.ResultPage;
import eComm.macys.pages.SetUP;
import eComm.macys.pages.ShoppingBagPage;

public class MandatoryFields // to find all the mandatory fields during Checkout-shipping
{
	static WebDriver driver= new FirefoxDriver();
	static WebDriverWait wait=new WebDriverWait(driver, 30);

	public static void main(String[] args)

	{	
		//setup
		String url= "https://www.macys.com/";
		SetUP setup= new SetUP();
		driver =setup.setUpBrowser(url, driver);

		try
		{
			//navigate to item Page
			HomePage hp= new HomePage();
			hp.navToJuniorJeans(driver);

			ResultPage rp= new ResultPage();
			rp.clickOnItem(driver);

			//select size and quantity
			String qt= "2";
			ItemPage ip= new ItemPage();
			ip.selectSizeAndQuantity(driver, qt);

			//add to cart
			ip.addToBag(driver);
			//view bag and checkout
			BagPage bp=new BagPage();
			bp.viewBagAndCheckout(driver);
			//proceed to checkout
			ShoppingBagPage sp= new ShoppingBagPage();
			sp.proceedToCheckOut(driver);
			//guest checkout
			AccountPage ap=new AccountPage();
			ap.checkOutAsGuest(driver);
			//click on continue with any input in fields
			PlaceOrderPage pp= new PlaceOrderPage();
			pp.clickOnContinue(driver);
			
			// get mandatory fields
			pp.printMandatoryFields(driver);
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
