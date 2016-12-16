package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {
	
	//	* Add RateRomainModel as an attribute
	private RateDomainModel Rate;

	//	* Create a constructor, passing in RateDomainModel
	public RateException(RateDomainModel rate) {
		super();
		this.Rate = rate;
	}

	public RateException(String Message) {
		// constructor created so that I can specify why
		//   the exception was thrown (RocketDAL.Rate_Test and RocketBLL.RateBLL)
	}

	//	* Create a getter (no setter, set value only in Constructor)
	public RateDomainModel getRate() {
		return Rate;
	}
}
