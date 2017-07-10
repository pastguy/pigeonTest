package ymm.ico;

public class Coin {

	private String name;
	
	private double cnyEachCoinBuy;
	private double cnyEachCoinSell;
	private double btcEachCoinBuy;
	private double btcEachCoinSell;
	private double ethEachCoinBuy;
	private double ethEachCoinSell;
	
	private double cnyEachEthBuy;
	private double cnyEachEthSell;
	private double cnyEachBtcBuy;
	private double cnyEachBtcSell;
	
	/**
	 * 1W ->coin -> btc -> cny
	 * @return
	 */
	public double buyBtc1WAndToCny(){
		double coin1W =  cnyEachCoinSell * 10000;
		double btc = btcEachCoinSell * coin1W;
		double price = cnyEachBtcSell * btc;
		return price;
	}
	
	/**
	 * 1W ->coin -> eth -> cny
	 * @return
	 */
	public double buyEth1WAndToCny(){
		double coin1W =  cnyEachCoinSell * 10000;
		double eth = ethEachCoinSell * coin1W;
		double price = cnyEachEthSell * eth;
		return price;
	}
	
	Coin(){}
	Coin(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCnyEachCoinBuy() {
		return cnyEachCoinBuy;
	}

	public void setCnyEachCoinBuy(double cnyEachCoinBuy) {
		this.cnyEachCoinBuy = cnyEachCoinBuy;
	}

	public double getCnyEachCoinSell() {
		return cnyEachCoinSell;
	}

	public void setCnyEachCoinSell(double cnyEachCoinSell) {
		this.cnyEachCoinSell = cnyEachCoinSell;
	}

	public double getBtcEachCoinBuy() {
		return btcEachCoinBuy;
	}

	public void setBtcEachCoinBuy(double btcEachCoinBuy) {
		this.btcEachCoinBuy = btcEachCoinBuy;
	}

	public double getBtcEachCoinSell() {
		return btcEachCoinSell;
	}

	public void setBtcEachCoinSell(double btcEachCoinSell) {
		this.btcEachCoinSell = btcEachCoinSell;
	}

	public double getEthEachCoinBuy() {
		return ethEachCoinBuy;
	}

	public void setEthEachCoinBuy(double ethEachCoinBuy) {
		this.ethEachCoinBuy = ethEachCoinBuy;
	}

	public double getEthEachCoinSell() {
		return ethEachCoinSell;
	}

	public void setEthEachCoinSell(double ethEachCoinSell) {
		this.ethEachCoinSell = ethEachCoinSell;
	}

	public double getCnyEachEthBuy() {
		return cnyEachEthBuy;
	}

	public void setCnyEachEthBuy(double cnyEachEthBuy) {
		this.cnyEachEthBuy = cnyEachEthBuy;
	}

	public double getCnyEachEthSell() {
		return cnyEachEthSell;
	}

	public void setCnyEachEthSell(double cnyEachEthSell) {
		this.cnyEachEthSell = cnyEachEthSell;
	}

	public double getCnyEachBtcBuy() {
		return cnyEachBtcBuy;
	}

	public void setCnyEachBtcBuy(double cnyEachBtcBuy) {
		this.cnyEachBtcBuy = cnyEachBtcBuy;
	}

	public double getCnyEachBtcSell() {
		return cnyEachBtcSell;
	}

	public void setCnyEachBtcSell(double cnyEachBtcSell) {
		this.cnyEachBtcSell = cnyEachBtcSell;
	}

	
}
