package prv.carhebti.business.dto;

import java.math.BigDecimal;

import prv.carhebti.business.entities.ICarhebtiEntity;

public class BudgetByTypeItem implements ICarhebtiEntity {

	private String type;
	private BigDecimal value;
	
	public BudgetByTypeItem() {
	}
	
	public BudgetByTypeItem(String type, BigDecimal value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public Integer getId() {
		return null;
	}
}
