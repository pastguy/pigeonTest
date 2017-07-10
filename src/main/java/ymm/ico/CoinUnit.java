package ymm.ico;

public enum CoinUnit {
	CNY("cny"),BTC("btc"),ETH("eth");
	
	
	CoinUnit(String name){
		this.name = name ;
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
