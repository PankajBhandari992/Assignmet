package com.investment.models;

import java.util.ArrayList;
import java.util.List;

public class Fund {

		private Long fundId; 
		private String fundName;

		private List<Holding> holdings;
		
		public Fund(String fundName, Long fundId) {
			this.fundId= fundId;
			this.fundName = fundName;
			this.holdings= new ArrayList<Holding>();
			
		}

		public String getFundName() {
			return fundName;
		}

		public void setFundName(String fundName) {
			this.fundName = fundName;
		}

		public List<Holding> getHoldings() {
			return holdings;
		}

		public void setHoldings(List<Holding> holdings) {
			this.holdings = holdings;
		}

		public Long getFundId() {
			return fundId;
		}

		public void setFundId(Long fundId) {
			this.fundId = fundId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((fundId == null) ? 0 : fundId.hashCode());
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
			Fund other = (Fund) obj;
			if (fundId == null) {
				if (other.fundId != null)
					return false;
			} else if (!fundId.equals(other.fundId))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Fund [fundId=" + fundId + ", fundName=" + fundName + ", holdings=" + holdings + "]";
		}
		
		
		
}
