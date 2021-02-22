package com.investment.models;

public class Holding {

	private Long holdingId;
	private String holdingName;
	private Integer holdingValue;
	
	public Holding(String holdingName, Integer holdingValue, Long holdingId) {
		this.holdingName = holdingName;
		this.holdingValue = holdingValue;
		this.holdingId= holdingId;
	}

	public String getHoldingName() {
		return holdingName;
	}

	public void setHoldingName(String holdingName) {
		this.holdingName = holdingName;
	}

	public Integer getHoldingValue() {
		return holdingValue;
	}

	public void setHoldingValue(Integer holdingValue) {
		this.holdingValue = holdingValue;
	}

	public Long getHoldingId() {
		return holdingId;
	}

	public void setHoldingId(Long holdingId) {
		this.holdingId = holdingId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((holdingId == null) ? 0 : holdingId.hashCode());
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
		Holding other = (Holding) obj;
		if (holdingId == null) {
			if (other.holdingId != null)
				return false;
		} else if (!holdingId.equals(other.holdingId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Holding [holdingId=" + holdingId + ", holdingName=" + holdingName + ", holdingValue=" + holdingValue
				+ "]";
	}
	
	
	
}
