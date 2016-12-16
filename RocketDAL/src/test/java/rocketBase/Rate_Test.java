package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class Rate_Test {
	
	@Test
	public void rateTest() throws RateException {
		// this test checks: getAllRates method, assert given credit score returns
		//   given interest rate, throws exception if does not match table
		for (RateDomainModel r : RateDAL.getAllRates()) {
			if (r.getiMinCreditScore() == 600) {
				assertTrue(r.getdInterestRate() == 5);
				System.out.println("600 -> 5");
			}
			else if (r.getiMinCreditScore() == 650) {
				assertTrue(r.getdInterestRate() == 4.5);
				System.out.println("650 -> 4.5");
			}
			else if (r.getiMinCreditScore() == 700) {
				assertTrue(r.getdInterestRate() == 4);
				System.out.println("700 -> 4");
			}
			else if (r.getiMinCreditScore() == 750) {
				assertTrue(r.getdInterestRate() == 3.75);
				System.out.println("750 -> 3.75");
			}
			else if (r.getiMinCreditScore() == 800) {
				assertTrue(r.getdInterestRate() == 3.5);
				System.out.println("800 -> 3.5");
			}
			else {
				throw new RateException("The given credit score does not match given interest rate.");
			}
		}
	}

	@Test
	public void givenTest() {
		// this test was given to us, asserts there is data in the table
		//    and prints size
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println ("Rates size: " + rates.size());
		assert(rates.size() > 0);
		
		assert(1==1);
	}

}
