package com.winter.app.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest; // 2. SpringBootTest 추가
import org.springframework.test.context.TestPropertySource;

import com.witnter.app.stock.StockService;

@SpringBootTest 
@TestPropertySource(locations = { "classpath:application-dev.properties" }) // 5. 띄어쓰기 공백 제거
public class StockServiceTest {

	@Autowired
	private StockService stockService;

	@Test
	void test() throws Exception {
		stockService.getAccessToken();

		assertEquals(1, 1);
	}

}