package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void test() {
		assert(1==1);
	}

	@Test
	public void test_exception() throws RateException{
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		try{
			double rate = RateBLL.getRate(0);
		}	catch(RateException e){
			throw e;
		}
	}

	
	@Test
	public void test_payment(){
		double payment = rocketBase.RateBLL.getPayment(5, 5, 5, 5, false);
		System.out.println(payment);
	}
	
	}

