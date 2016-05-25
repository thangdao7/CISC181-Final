package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	private RateDomainModel rateDomainModel = null;
	
	public RateDomainModel getRatedomainmodel() {
		return rateDomainModel;
	}
	
	public RateException(RateDomainModel rate) {
		// TODO Auto-generated constructor stub
		
		this.rateDomainModel = rateDomainModel;
		
	}

	
	
	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
}
