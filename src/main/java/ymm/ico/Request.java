package ymm.ico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Request {
	private Coin coin;
	private static CloseableHttpClient httpClient = null;

	public Request(Coin coin){
		this.coin = coin;
	}
	public CloseableHttpClient getHttpClient() {
		if (httpClient == null) {
			synchronized (Request.class) {
				if (httpClient == null) {
//					RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD)
//							.setConnectionRequestTimeout(6000).setConnectTimeout(6000).build();
//					httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
					httpClient = HttpClients.custom().build();
				}
			}
		}
		return httpClient;
	}

	public String[] obainPrice(String coinNameFrom,String to){
		String from = coinNameFrom;
		StringBuffer url = new StringBuffer();
		url.append("http://bter.com/json_svr/query/?u=11&c=751735&type=ask_bid_list_table&symbol=");
		url.append(from);
		url.append("_");
		url.append(to);
		HttpGet httpGet = new HttpGet(url.toString());

		httpGet.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
		httpGet.addHeader("Cookie",
				"captcha_reg=f9784ad779833897e32249b5120dd840; __cfduid=da551c431824962f828250cd2a9b3acbd1498708656; captcha=dfb43e7252d55108e27a9cc6d89be8c3; NB_SRVID=srv253447; lasturl=%2Ftrade%2Feos_cny");
		httpGet.addHeader("Host", "bter.com");
		httpGet.addHeader("Referer", "https://bter.com/trade/" + from + "_" + to);
		httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
		httpGet.addHeader("Connection", "keep-alive");
		httpGet.addHeader("Referer", "https://bter.com/trade/" + from + "_" + to);
		httpGet.addHeader("X-Requested-With", "XMLHttpRequest");

		InputStream in = null;
		try {
			CloseableHttpResponse response = getHttpClient().execute(httpGet);
			in = response.getEntity().getContent();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String html = getStringFromStream(in);
//		Document document = Jsoup.parse(html);

		int sell = html.indexOf(Constants.SELL_TAG);
		int buy = html.indexOf(Constants.BUG_TAG);
		int asklist = html.indexOf(Constants.ASKLIST_TAG);
		String str1 = html.substring(sell + "ask_rate0".length() + 2, buy - 2);
		String str2 = html.substring(buy + "bid_rate0".length() + 2, asklist - 2);
		System.out.println(coinNameFrom +"---"+to +":" + str1 + "--"+str2);
		String[] res = new String[2];
		res[0] = str1;
		res[1] = str2;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	
	public  Coin obainCnyPrice(String coinNameFrom) {
		String[] res = obainPrice(coinNameFrom,CoinUnit.CNY.getName());
		Coin eos = this.getCoin();
		eos.setCnyEachCoinBuy(Double.valueOf(res[1]));
		eos.setCnyEachCoinSell(Double.valueOf(res[0]));
		return eos;
	}

	public  Coin obainBtcPrice(String coinNameFrom) {
		String[] res = obainPrice(coinNameFrom,CoinUnit.BTC.getName());
		Coin eos = this.getCoin();
		eos.setBtcEachCoinBuy(Double.valueOf(res[1]));
		eos.setBtcEachCoinSell(Double.valueOf(res[0]));
		return eos;
		
	}
	
	public  Coin obainEthPrice(String coinNameFrom) {
		String[] res = obainPrice(coinNameFrom,CoinUnit.ETH.getName());
		Coin eos = this.getCoin();
		eos.setEthEachCoinBuy(Double.valueOf(res[1]));
		eos.setEthEachCoinSell(Double.valueOf(res[0]));
		
		return eos;
	}
	
	public  Coin obainBtcToCnyPrice() {

		String[] res = obainPrice(CoinUnit.BTC.getName(),CoinUnit.CNY.getName());
		Coin eos = this.getCoin();
		eos.setCnyEachBtcBuy(Double.valueOf(res[1]));
		eos.setCnyEachBtcSell(Double.valueOf(res[0]));
		return eos;
	}
	
	public  Coin obainEthToCnyPrice() {
		String[] res = obainPrice(CoinUnit.ETH.getName(),CoinUnit.CNY.getName());
		Coin eos = this.getCoin();
		eos.setCnyEachEthBuy(Double.valueOf(res[1]));
		eos.setCnyEachEthSell(Double.valueOf(res[0]));
		return eos;
	}
	
	public static String getStringFromStream(InputStream tInputStream) {
		if (tInputStream != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream));
				StringBuffer tStringBuffer = new StringBuffer();
				String sTempOneLine = new String("");
				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					tStringBuffer.append(sTempOneLine);
				}
				if (tStringBuffer.length() != 0) {
					return tStringBuffer.toString();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	public Coin getCoin(){
		if(this.coin == null){
			synchronized (this) {
				this.coin = new Coin();
			}
		}
		return this.coin;
	}
	
	
}
