package com.investment.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.investment.exception.InvalidArgumentException;
import com.investment.exception.MappingAlreadyExistException;
import com.investment.exception.RecordNotFoundException;
import com.investment.models.Fund;
import com.investment.models.Holding;
import com.investment.models.Investor;
import com.investment.service.InvestmentService;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

	private static Logger logger = LoggerFactory.getLogger(InvestmentController.class);
	
	@Autowired
	InvestmentService investmentService;
	
	@GetMapping("marketValue/investor/{investorId}")
	public Integer getMarketValueByInvestmentId(@PathVariable Long investorId) throws InvalidArgumentException, RecordNotFoundException {
		logger.info("Inside InvestmentController : getMarketValueByInvestmentId: "+ investorId);
		return investmentService.markerValueInvertorId(investorId);
	}
	
	@GetMapping("marketValue/fund/{fundId}")
	public Integer getMarketValueByFundId(@PathVariable Long fundId) throws InvalidArgumentException, RecordNotFoundException {
		logger.info("Inside InvestmentController : getMarketValueByFundId: "+ fundId);
		return investmentService.markerValueFundId(fundId);
	}
	
	@GetMapping("marketValue/{fundId}/{excludedHoldingId}")
	public Integer markerValueWithExclusionList(@PathVariable Long fundId, @PathVariable Long excludedHoldingId) throws InvalidArgumentException, RecordNotFoundException {
		logger.info("Inside InvestmentController : markerValueWithExclusionList: "+ fundId+ " excludedHoldingId: "+ excludedHoldingId);
		Integer marketValueAfterExclusion=0;
		
		//checking if the fund is valid or exist in system
		Optional<Fund> fundOptional=investmentService.getFundById(fundId);
		Fund fund= fundOptional.orElseThrow(()->new RecordNotFoundException("No record found for provided fundId."));
		
		//checking if the holding is valid or exist in system
		Optional<Holding> holdingOptional =investmentService.getHoldigById(excludedHoldingId);
		Holding excludedHolding =holdingOptional.orElseThrow(()->new RecordNotFoundException("No holding found for provided holdingId."));
		
		//checking if the provided holding is already associated with the provided fund
		if(fund.getHoldings().contains(excludedHolding)) {
			 marketValueAfterExclusion=investmentService.markerValueWithExclusionList(fund, excludedHolding);
		}else {
			throw new RuntimeException("Provided holding for exclusion is not associated with the provided fund.");
		}
		return marketValueAfterExclusion;
		
	}
		
	@PostMapping("/mapping/fund_holding/{fundId}/{holdingId}")
	public Fund mapHoldingToFund(@PathVariable Long fundId, @PathVariable Long holdingId) throws MappingAlreadyExistException, RecordNotFoundException {
		logger.info("Inside InvestmentController: mapHoldingToFund : fundId : " + fundId + " holdingId: " + holdingId);
		// checking validity of fund with provided fund id
		Fund fund = investmentService.getFundById(fundId).orElseThrow(
				() -> new RecordNotFoundException("No fund with fundId " + fundId + " present in the system."));
		// checking validity of holding with provided holding id
		Holding holding = investmentService.getHoldigById(holdingId).orElseThrow(() -> new RecordNotFoundException(
				"No holding with holdingId " + holdingId + " present in the system."));

		// checking if the holding is already associated with the fund
		if (fund.getHoldings().contains(holding)) {
			throw new MappingAlreadyExistException("Provided holding is already mapped to fund");
		} else {
			fund.getHoldings().add(holding);
			logger.info("Holding with holding id "+ holdingId+ " added successfully to fund with fundId "+ fundId);
		}
		return fund;
	}
	
	@PostMapping("/mapping/investor_fund/{investorId}/{fundId}")
	public Investor mapFundToInvestor(@PathVariable Long investorId, @PathVariable Long fundId) throws RecordNotFoundException, MappingAlreadyExistException {
		logger.info("Inside InvestmentController: mapFundToInvestor : fundId : " + fundId + " investorId: " + investorId);
		// checking the provided investor exist in the system
		Investor investor = investmentService.getInvestorById(investorId).orElseThrow(() -> new RecordNotFoundException(
				"No investor with investorId " + investorId + " present in the system."));

		// checking validity of fund
		Fund fund = investmentService.getFundById(fundId).orElseThrow(
				() -> new RecordNotFoundException("No fund with fundId " + fundId + " present in the system."));

		//checking if the investor already contains the provided fund
		if (investor.getFunds().contains(fund)) {
			throw new MappingAlreadyExistException("Provided fund is already mapped to investor");
		} else {
			investor.getFunds().add(fund);
			logger.info("Fund with fundId "+ fundId+ " has been successfully mapped to investor with investorId "+ investorId);
		}
		return investor;
	}

	@PostMapping("/fund/addFund")
	public Long addFund(@RequestBody Fund fund) {
		logger.info("Inside InvestmentController : addFunds: " + fund.getFundId());
		Long fundId=investmentService.addFund(fund);
		return fundId;
	}
	
	@PostMapping("/holding/addHolding")
	public Long addHolding(@RequestBody Holding holding) {
		Long holdingId=investmentService.addHolding(holding);
		return holdingId;
	}
	
	@PostMapping("/investor/addInvestor")
	public Long addInvestor(@RequestBody Investor investor) {
		logger.info("inside investmentController: addInvestor id: "+ investor.getInvestorId());
		Long investerId= investmentService.addInvestor(investor);
		return investerId;
	}
	
	@GetMapping("/investors/getAllInvestors")
	public List<Investor> getAllInvestors(){
		return investmentService.getInvestorList();
	}
	
	@GetMapping("/funds/getFunds")
	public List<Fund> getAllFunds(){
		return investmentService.getFundList();
	}
	
	@GetMapping("/holdings/getHoldings")
	public List<Holding> getAllHoldings(){
		return investmentService.getHoldingList();
	}
	
}
