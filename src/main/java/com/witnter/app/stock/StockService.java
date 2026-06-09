package com.witnter.app.stock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class StockService {
	
	@Value("${api.appKey}")
	private String appkey;
	
	@Value("${api.secretKey}")
	private String secretkey;
	
	@Value("${api.accesstoken}")
	private String accesstoken;
	
	public void getMarketPrice() throws Exception {
		RestClient client = RestClient.create();
		
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("FID_COND_MRKT_DIV_CODE", "J"); 
		queryParams.add("FID_INPUT_ISCD", "005930");     

		String result = client.get()
				.uri(uriBuilder -> uriBuilder
						.scheme("https")
						.host("openapi.koreainvestment.com")
						.port(29443)
						.path("/uapi/domestic-stock/v1/quotations/inquire-price")
						.queryParams(queryParams) 
						.build())
				.header("Content-Type", "application/json; charset=UTF-8")
				
				.header("authorization", "Bearer " + accesstoken) 
				.header("appkey", appkey)
				.header("appsecret", secretkey)
				.header("tr_id", "FNHKST01010100") 
				.header("custtype", "P")           
				.retrieve()
				.body(String.class);

		System.out.println("Market Price Result: " + result);
	}
	
	public void getAccessToken() throws Exception {
		RestClient client = RestClient.create(); 
		
		Map<String, String> params = new HashMap<>();
		params.put("grant_type", "client_credentials");
		params.put("appkey", appkey);
		params.put("secretkey", secretkey);
		
		String result = client.post()
				.uri("https://api.example.com/oauth2/token") 
				.header("Content-Type", "application/json; charset=UTF-8")
				.body(params)
				.retrieve()
				.body(String.class);
		
		System.out.println("Access Token Result: " + result);
	}
	
	public String test() throws Exception {
		RestClient client = RestClient.create();
		
		String result = client.get()
		      .uri("https://jsonplaceholder.typicode.com/posts/1")
		      .retrieve()
		      .body(String.class);
	  
		 System.out.println(result);
		 
		 return result;
	}
}