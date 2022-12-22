package pageObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import baseClass.BaseClass;

public class FlightSearchMMT extends BaseClass {

	JavascriptExecutor jsExe = (JavascriptExecutor) driver;
	Actions act = new Actions(driver);

	private By flightTab = By.xpath("//ul[@class=\"makeFlex font12\"]/li[@data-cy=\"menu_Flights\"]");

	private By fromBtn = By.xpath("//label[@for='fromCity']");

	private By toButton = By.xpath("//label[@for='toCity']");

	private By fromInputField = By.xpath("//label[@for='fromCity']/..//div[@role='combobox']/input");

	private By toInputField = By.xpath("//label[@for='toCity']/..//div[@role='combobox']/input");

	private By fromCitySug = By.xpath("//*[text()='DEL']/ancestor::li");

	private By toCitySug = By.xpath("//*[text()='BOM']/ancestor::li");

	private By searchBtn = By.xpath("//*[@class='makeFlex vrtlCenter ']/a");

	private By popupOkBtn = By.xpath("//*[text()='OKAY, GOT IT!']");

	private By sortbyDetartureBtn = By.xpath("//*[text()='Departure']");

	// searching method to select flight from Delhi to Mumbai
	public void SearchFlight() {
		try {
			jsExe.executeScript("arguments[0].click();", driver.findElement(flightTab));

			act.moveToElement(driver.findElement(fromBtn)).build().perform();
			act.moveToElement(driver.findElement(fromBtn)).click().perform();
			driver.findElement(fromBtn).click();
			act.moveToElement(driver.findElement(fromInputField)).build().perform();
			act.moveToElement(driver.findElement(fromInputField)).click().perform();
			driver.findElement(fromInputField).click();
			driver.findElement(fromInputField).sendKeys("DEL");
			act.moveToElement(driver.findElement(fromCitySug)).click().perform();

			act.moveToElement(driver.findElement(toButton)).build().perform();
			jsExe.executeScript("arguments[0].click();", driver.findElement(toButton));
			act.moveToElement(driver.findElement(toInputField)).build().perform();
			driver.findElement(toInputField).click();
			driver.findElement(toInputField).sendKeys("BOM");
			act.moveToElement(driver.findElement(toCitySug)).build().perform();
			driver.findElement(toCitySug).click();

			driver.findElement(searchBtn).click();

			driver.findElement(popupOkBtn).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	// method for selecting date from Departure date menu
	public void sortByDetarture() {
		try {
			jsExe.executeScript("arguments[0].scrollIntoView();", driver.findElement(sortbyDetartureBtn));
			jsExe.executeScript("arguments[0].click();", driver.findElement(sortbyDetartureBtn));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	// method for getting the 2 lowest price of flight with name after the search
	public void getFlightPrice() {
		try {
			List<WebElement> priceList = driver
					.findElements(By.xpath("//*[@class='priceSection']//div[@class='textRight flexOne']/p"));
			TreeSet<Integer> Price = new TreeSet<>();
			ArrayList<String> price = new ArrayList<>();

			for (WebElement e : priceList) {
				Price.add(Integer.parseInt(e.getText().replaceAll("[^0-9]", "")));
				price.add(e.getText());
			}
			int temp = (int) (Price.toArray())[1];
			String secLowestPrice = "";
			for (String e : price) {
				if (temp == Integer.parseInt(e.replaceAll("[^0-9]", ""))) {
					secLowestPrice = e.substring(2);
					break;
				}
			}
			System.out.println("₹ " + secLowestPrice);

			String filghtName = driver
					.findElement(By.xpath("//*[contains(text(),'" + secLowestPrice
							+ "')]/ancestor::div[@class='listingCard']//p[@class='boldFont blackText airlineName']"))
					.getText();

			System.out.println("This " + filghtName + " has 2nd lowest price of ₹ " + secLowestPrice);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
