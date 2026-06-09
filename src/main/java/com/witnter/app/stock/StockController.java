package com.witnter.app.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/**")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	public String getMaretPrice() throws Exception {
		return stockService.test();
	}
	

}
