package com.investment.models;

import java.util.ArrayList;
import java.util.List;

public class Investor {

	private Long investorId;
	private String investorName;
	private List<Fund> funds;
	
	
	public Investor(String investorName, Long investorId) {
		this.investorId= investorId;
		this.investorName = investorName;
		this.funds= new ArrayList<Fund>();
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public List<Fund> getFunds() {
		return funds;
	}

	public void setFunds(List<Fund> funds) {
		this.funds = funds;
	}

	public Long getInvestorId() {
		return investorId;
	}

	public void setInvestorId(Long investorId) {
		this.investorId = investorId;
	}

	@Override
	public String toString() {
		return "Investor [investorId=" + investorId + ", investorName=" + investorName + ", funds=" + funds + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((investorId == null) ? 0 : investorId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Investor other = (Investor) obj;
		if (investorId == null) {
			if (other.investorId != null)
				return false;
		} else if (!investorId.equals(other.investorId))
			return false;
		return true;
	}
	
	
	
}
