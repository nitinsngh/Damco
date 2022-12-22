package testScript;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObject.FlightSearchMMT;

public class FlightSearchMMT_test extends BaseClass {
	FlightSearchMMT ob;

	@Test(priority = 1)
	@Parameters(value = {"browser","url"})
	public void searcghFlight() {
		ob = new FlightSearchMMT();
		ob.SearchFlight();//searching method to select flight from Delhi to Mumbai
		ob.sortByDetarture();// method for selecting date from Departure date menu
		ob.getFlightPrice();//// method for getting the 2 lowest price of flight with name after the search
	}

}
