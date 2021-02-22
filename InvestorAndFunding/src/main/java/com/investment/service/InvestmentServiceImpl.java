package com.investment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.investment.exception.InvalidArgumentException;
import com.investment.exception.RecordNotFoundException;
import com.investment.models.Fund;
import com.investment.models.Holding;
import com.investment.models.Investor;

@Service
public class InvestmentServiceImpl implements InvestmentService {

	private static Logger logger = LoggerFactory.getLogger(InvestmentServiceImpl.class);
	
	private Set<Fund> fundList= new HashSet<Fund>();
	private Set<Investor> investorList= new HashSet<Investor>();
	private Set<Holding> holdingList= new HashSet<Holding>();
	
	@Override
	public void initilizeValues() {
		
		Holding h1= new Holding("h1", 10,10001l);
		Holding h2= new Holding("h2", 20, 10002l);
		Holding h3= new Holding("h3", 15, 10003l);
		Holding h4= new Holding("h4", 10, 10004l);
		
		holdingList.add(h1);
		holdingList.add(h2);
		holdingList.add(h3);
		holdingList.add(h4);
		
		Fund f1= new Fund("f1", 20001l);
		Fund f2= new Fund("f2", 20002l);
		Fund f3= new Fund("f3", 20003l);
		
		fundList.add(f1);
		fundList.add(f2);
		fundList.add(f3);
		
		Investor i1= new Investor("i1", 50001l);
		Investor i2= new Investor("i2", 50002l);
		
		List<Holding> hList1= new ArrayList<Holding>();
		hList1.add(h1);
		hList1.add(h2);
		hList1.add(h4);
		f1.setHoldings(hList1);
		List<Holding> hList2= new ArrayList<Holding>();
		hList2.add(h1);
		hList2.add(h3);
		f2.setHoldings(hList2);
		List<Holding> hList3= new ArrayList<Holding>();
		hList3.add(h1);
		hList3.add(h4);
		f3.setHoldings(hList3);
		
		List<Fund> fundList1= new ArrayList<Fund>();
		fundList1.add(f1);
		fundList1.add(f3);
		List<Fund> fundList2= new ArrayList<Fund>();
		fundList2.add(f2);
		fundList2.add(f3);
		i1.setFunds(fundList1);
		i2.setFunds(fundList2);
		List<Investor> invList= new ArrayList<Investor>();
		invList.add(i1);
		invList.add(i2);
		investorList.addAll(invList);
		
	}

	@Override
	public Integer markerValueFundId(Long fundId) throws InvalidArgumentException, RecordNotFoundException {
		if(null ==fundId) {
			throw new InvalidArgumentException("Invalid value provided");
		}
		Fund fund=getFundById(fundId)
				.orElseThrow(()-> new RecordNotFoundException("Record is not vailable with provided Fund Id."));
		
		List<Holding> holdings= fund.getHoldings();
		Integer marketValue=holdings.stream()
				.map((h)-> h.getHoldingValue())
				.reduce(0, (i,j)->i + j);
		return marketValue* 100;
	}
	
	@Override
	public Integer markerValueInvertorId(Long investorId) throws InvalidArgumentException, RecordNotFoundException {
		//System.out.println(investor.getInvestorName());
		if(null ==investorId) {
			throw new InvalidArgumentException("Invalid value provided");
		}
		Investor investor= getInvestorById(investorId)
				.orElseThrow(()-> new RecordNotFoundException("Record is not vailable with provided Investor Id"));
		
		List<Fund> funds = investor.getFunds();
		Integer marketValue= funds.stream()
				.map((f)-> {
					Integer i=0;
					try {
						 i= markerValueFundId(f.getFundId());
					} catch (InvalidArgumentException e) {
						// TODO Auto-generated catch block
						logger.info("Invalid Fund Id");
					}catch(RecordNotFoundException e) {
						logger.info("Record not found for the Fund Id");
					}
					return i;
				})
				.reduce(0, (i,j)->i+j);
		return marketValue;
	}
	
	@Override
	public Integer markerValueWithExclusionList(Fund fund,Holding excludedHolding) {
		Integer excluded=excludedHolding.getHoldingValue();
		System.out.println();
		List<Holding> holdings= fund.getHoldings();
		Integer marketValue= holdings.stream()
				.map((h)-> h.getHoldingValue())
				.reduce(0, (i,j)->i+j);
		return (marketValue-excluded)* 100;
	}

	@Override
	public List<Fund> getFundList() {
		return fundList.stream().collect(Collectors.toList());
	}

	@Override
	public Long addFund(Fund fund) {
		this.fundList.add(fund);
		return fund.getFundId();
	}

	@Override
	public List<Investor> getInvestorList() {
		return investorList.stream().collect(Collectors.toList());
	}

	@Override
	public Long addInvestor(Investor investor) {
		this.investorList.add(investor);
		return investor.getInvestorId();
	}

	@Override
	public List<Holding> getHoldingList() {
		return holdingList.stream().collect(Collectors.toList());
	}

	@Override
	public Long addHolding(Holding holding) {
		this.holdingList.add(holding);
		return holding.getHoldingId();
	}
	@Override
	public Optional<Fund> getFundById(Long fundId) {
		logger.info("Inside InvestmentServiceImpl: "+ fundId);
		Optional<Fund> fund=fundList.stream().filter((f)-> f.getFundId().equals(fundId)).findFirst();
		return fund;
	}
	
	@Override
	public Optional<Investor> getInvestorById(Long investorId) {
		Optional<Investor> investor=investorList.stream().filter((i)-> i.getInvestorId().equals(investorId)).findFirst();
		return investor;
	}
	
	@Override
	public Optional<Holding> getHoldigById(Long holdingId){
		Optional<Holding> holding=getHoldingList().stream().filter((h)-> h.getHoldingId().equals(holdingId)).findFirst();
		return holding;
	}
}


