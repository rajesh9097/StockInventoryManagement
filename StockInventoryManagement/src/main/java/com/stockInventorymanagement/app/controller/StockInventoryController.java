package com.stockInventorymanagement.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockInventorymanagement.app.entity.StockDetails;
import com.stockInventorymanagement.app.service.StockInventoryService;

@RestController
@RequestMapping("/stock")
public class StockInventoryController {

	@Autowired
	StockInventoryService stockInventoryService;

	@PostMapping(path = "/createstock")
	public ResponseEntity<StockDetails> createStock(@RequestBody StockDetails stockDetails) {
		stockDetails = stockInventoryService.saveStock(stockDetails);
		if (stockDetails == null) {
			return new ResponseEntity<StockDetails>(stockDetails, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<StockDetails>(stockDetails, HttpStatus.CREATED);
	}

	@PutMapping(path = "/updatestock")
	public ResponseEntity<StockDetails> updateStock(@RequestBody StockDetails stockDetails) {
		stockDetails = stockInventoryService.updateStock(stockDetails);
		if (stockDetails == null) {
			return new ResponseEntity<StockDetails>(stockDetails, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<StockDetails>(stockDetails, HttpStatus.OK);
	}

	@GetMapping(path = "displaystock")
	public ResponseEntity<List<StockDetails>> displayStock() throws Exception {
		List<StockDetails> stocks = null;
		try {
			stocks = stockInventoryService.displaystock();
		} catch (Exception e) {
			throw new Exception();
		}
		if (stocks == null) {
			return new ResponseEntity<List<StockDetails>>(stocks, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<StockDetails>>(stocks, HttpStatus.OK);
	}

	@PutMapping(path = "sell/{stockno}/{qty}")
	public ResponseEntity<StockDetails> sellingStock(@PathVariable int stockno, @PathVariable int qty) {
		StockDetails stockDetails = stockInventoryService.sellingStock(stockno, qty);
		if (stockDetails == null) {
			return new ResponseEntity<StockDetails>(stockDetails, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<StockDetails>(stockDetails, HttpStatus.OK);
	}

	@DeleteMapping(path = "deletestock")
	public void deletestock() {
		stockInventoryService.deleteStock();
	}
}
