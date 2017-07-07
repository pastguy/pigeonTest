package ymm.ico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Request {
	private static CloseableHttpClient httpClient = null;

	public CloseableHttpClient getHttpClient() {
		if (httpClient == null) {
			synchronized (Request.class) {
				if (httpClient == null) {
					RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD)
							.setConnectionRequestTimeout(6000).setConnectTimeout(6000).build();
					httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
				}
			}
		}
		return httpClient;
	}

	public static Coin obainCnyPrice(CoinName coinNameFrom, CoinName coinNameto) {
		Coin eos = new Coin();
		eos.setName(coinNameFrom.getName());
		String from = coinNameFrom.getName();
		String to = coinNameto.getName();

		StringBuffer url = new StringBuffer();
		url.append("http://bter.com/json_svr/query/?u=11&c=751735&type=ask_bid_list_table&symbol=eos_cny");
		url.append(from);
		url.append("_");
		url.append(to);
		HttpGet httpGet = new HttpGet(url.toString());

		httpGet.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
		httpGet.addHeader("Cookie",
				"captcha_reg=f9784ad779833897e32249b5120dd840; __cfduid=da551c431824962f828250cd2a9b3acbd1498708656; captcha=283d51bdd7799c5259ec95c3e27a604f; lasturl=%2Ftrade%2FEOS_CNY");
		httpGet.addHeader("Host", "bter.com");
		httpGet.addHeader("Referer", "https://bter.com/trade/" + from + "_" + to);
		httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
		httpGet.addHeader("Connection", "keep-alive");
		httpGet.addHeader("Referer", "https://bter.com/trade/" + from + "_" + to);
		httpGet.addHeader("X-Requested-With", "XMLHttpRequest");

		InputStream in = null;
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
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
		System.out.println(str1 + ":" + str2);
		eos.setCnyEachCoinBuy(Double.valueOf(str2));
		eos.setCnyEachCoinSell(Double.valueOf(str1));
		
		// Pattern p = Pattern.compile(" \"ask_rate0\":(.*),\"bid_rate0\" ");
		// "ask_rate0":19.78,"bid_rate0":19.7,"ask_list_table":
		// Matcher m = p.matcher(html);
		// if(m.matches()){
		// String s = m.group(1);
		// System.out.println(s);
		// }
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
}
