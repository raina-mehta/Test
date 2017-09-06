package eCommerce;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import eComm.macys.pages.*;

public class MacysTestCases 
{	
	WebDriver driver= new FirefoxDriver();
	HomePage hp;
	ResultPage rp;
	ItemPage ip;
	BagPage bp;
	ShoppingBagPage sp;
	AccountPage ap;	
	PlaceOrderPage pp;
	
	@BeforeTest(alwaysRun=true)
	public void setUp() throws IOException 
	{	//setup
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Rajneesh Mehta\\Desktop\\Selenium\\gecko\\geckodriver.exe");
		String url= "https://www.macys.com/";
		driver.get(url);
		hp=new HomePage(driver);
		rp=new ResultPage(driver);
		ip= new ItemPage(driver);
		bp=new BagPage(driver);
		sp= new ShoppingBagPage(driver);
		ap=new AccountPage(driver);	
		pp= new PlaceOrderPage(driver);
		
	}

	@Test(priority=0,testName = "TC01", description = "verify Result items count")
	public void resultItemCount()
	{
		try
		{	
			hp.navToJuniorJeans();		//navigate to Jeans Page
			int expectedCount= rp.expectedItemCount(driver);	//get no. of items expected
			int [] resultArray= rp.resultItemCount(driver);//get actual item count	
			assertEquals(expectedCount,resultArray[1]);	// verify if expected is same as actual
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test(priority=1, testName= "TC02",description = "verify add to cart and remove functionality")
	public void cartAddAndRemove()
	{
		try
		{	
			hp.navToJuniorJeans();	//navigate to item Page
			rp.clickOnItem(driver);	//click on an item
			String qt= "2";					//select size and quantity
			ip.selectSizeAndQuantity( driver, qt);
			ip.addToBag(driver);		//add to bag
			bp.noOfItemsInCart(driver, qt);	// verify no of items added in cart
			bp.viewBagAndCheckout(driver);	//view bag and checkout
			assertEquals(sp.itemRemoval(driver), "Your Current Shopping Bag is empty.");	//remove item and verify removal	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test(priority=2, testName= "TC03", description="Verify all the mandatory fields during checkout")
	public void mandatoryFields()
	{
		try
		{	
			hp.navToJuniorJeans(); 	//navigate to item Page
			rp.clickOnItem(driver);
			String qt= "2";		//select size and quantity
			ip.selectSizeAndQuantity( driver, qt);
			ip.addToBag(driver);		//add to cart
			bp.viewBagAndCheckout(driver);	//view bag and checkout
			sp.proceedToCheckOut(driver);		//proceed to checkout
			ap.checkOutAsGuest(driver);		//guest checkout
			pp.clickOnContinue(driver);		//click on continue with any input in fields
			//pp.printMandatoryFields(driver);		// get mandatory fields
			assertEquals(pp.printMandatoryFields(driver), 10);
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	

	@AfterSuite
	public void closeDriver()
	{
		driver.close();

	}

}
