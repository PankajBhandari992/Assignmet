package com.investment.service;

import java.util.List;
import java.util.Optional;

import com.investment.exception.InvalidArgumentException;
import com.investment.exception.RecordNotFoundException;
import com.investment.models.Fund;
import com.investment.models.Holding;
import com.investment.models.Investor;

public interface InvestmentService {

	public void initilizeValues();
	public Integer markerValueInvertorId(Long investorId) throws InvalidArgumentException, RecordNotFoundException;
	public Integer markerValueFundId(Long fundId) throws InvalidArgumentException, RecordNotFoundException;
	Integer markerValueWithExclusionList(Fund fund, Holding excludedHolding);
	List<Fund> getFundList();
	List<Investor> getInvestorList();
	List<Holding> getHoldingList();
	Long addFund(Fund fund);
	Long addInvestor(Investor investor);
	Long addHolding(Holding holding);
	Optional<Fund> getFundById(Long fundId);
	Optional<Investor> getInvestorById(Long investorId);
	Optional<Holding> getHoldigById(Long holdingId);

}
