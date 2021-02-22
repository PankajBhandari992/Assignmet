package com.investment.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.investment.exception.InvalidArgumentException;
import com.investment.exception.RecordNotFoundException;

class InvestmentServiceTest {

	
	InvestmentService is;
	@BeforeEach
	void setup() {
		is= new InvestmentServiceImpl();
	}
	
	@Test
	void testMarkerValueInvertorIdForRecordNotFoundException() {
		
		Assertions.assertThrows(RecordNotFoundException.class,()-> is.markerValueInvertorId(5001l));
	}
	
	@Test
	void testMarkerValueInvertorIdForInvalidArgumentException() {
		Assertions.assertThrows(InvalidArgumentException.class,()-> is.markerValueInvertorId(null));
	}
	
	@Test
	void testMarkerValueInvertorId() {
		is.initilizeValues();
		Integer result=null;
		try {
			result=is.markerValueInvertorId(50001l);
		} catch (InvalidArgumentException | RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertEquals(6000, result);
	}

	
	@Test
	void testMarkerValueFundIdForRecordNotFoundException() {
		Assertions.assertThrows(RecordNotFoundException.class,()-> is.markerValueFundId(5001l));
	}
	
	@Test
	void testMarkerValueFundIdForInvalidArgumentException() {
		Assertions.assertThrows(InvalidArgumentException.class,()-> is.markerValueInvertorId(null));
	}
	
	@Test
	void testMarkerValueFundId() {
		is.initilizeValues();
		Integer result=null;
		try {
			result=is.markerValueFundId(20001l);
		} catch (InvalidArgumentException | RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertEquals(4000, result);
	}
	
	@AfterEach
	void tearDown() {
		is= null;
	}
	
}
