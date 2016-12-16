package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class rate_test {
	
	//RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void test() {
		assert(1==1);
	}
	
	@Test
	public void pitiTest() {
		// testing piti formula
		assert(-1432.25 == RateBLL.getPayment(.04/12,360.0,300000,0,false)); 
	}
	
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

}
