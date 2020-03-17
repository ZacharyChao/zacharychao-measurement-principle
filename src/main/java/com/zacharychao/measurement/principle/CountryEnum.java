package com.zacharychao.measurement.principle;

public enum CountryEnum {
	ONE("1","one"),TWO("2","two"),THREE("3","three"),FOUR("4","four"),FIVE("5","five"),SIX("6","six");
	private String retCount;
	private String retmsg;
	private CountryEnum(String retCount, String retmsg) {
		this.retCount = retCount;
		this.retmsg = retmsg;
	}
	public String getRetCount() {
		return retCount;
	}
	public void setRetCount(String retCount) {
		this.retCount = retCount;
	}
	public String getRetmsg() {
		return retmsg;
	}
	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}
	public static CountryEnum getMSG(String key) {
		CountryEnum[] values = CountryEnum.values();
		for(CountryEnum countryEnum : values) {
			if(countryEnum.getRetCount().equals(key)) {
				return countryEnum;
			}
		}
		return null;
	}
}
