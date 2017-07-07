package ymm.ico;

public enum CoinName {
	EOS("eos"),SNT("snt");
	
	
	CoinName(String name){
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
