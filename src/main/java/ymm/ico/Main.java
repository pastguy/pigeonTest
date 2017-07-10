package ymm.ico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

	public static String[] coin=new String[]{"eos"};
	
	public static void main(String[] args) {
		
		for (int i = 0; i < coin.length; i++) {
			String name = coin[i];
			Request request = new Request(new Coin(name));
			request.obainCnyPrice(name);
			request.obainEthPrice(name);
//			request.obainBtcPrice(name);
//			request.obainBtcToCnyPrice();
			request.obainEthToCnyPrice();
			double btcprice = request.getCoin().buyBtc1WAndToCny();
			double ethprice = request.getCoin().buyBtc1WAndToCny();
			System.out.println("1W ->coin -> btc -> cny :" + btcprice);
			System.out.println("1W ->coin -> eth -> cny :" + ethprice);
		}
		
        
	}
	
	
	 public static String getStringFromStream(InputStream tInputStream){
	        if (tInputStream != null){
	            try{
	                BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream));
	                StringBuffer tStringBuffer = new StringBuffer();
	                String sTempOneLine = new String("");
	                while ((sTempOneLine = tBufferedReader.readLine()) != null){
	                    tStringBuffer.append(sTempOneLine);
	                }
	                if(tStringBuffer.length() != 0){
	                    return tStringBuffer.toString();
	                }
	            }catch (Exception ex){
	                ex.printStackTrace();
	            }
	        }
	        return null;
	    }
}
