package com.witnter.app.stock;

import com.witnter.app.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/**")
public class StockController {

    private final Test test;
	
	@Autowired
	private StockService stockService;

    StockController(Test test) {
        this.test = test;
    }
	
	@GetMapping("/marketPrice")
	public String getMarketPrice() throws Exception {
		return stockService.getMarketPrice();
	}
	

}