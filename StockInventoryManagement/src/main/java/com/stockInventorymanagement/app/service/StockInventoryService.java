package com.stockInventorymanagement.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockInventorymanagement.app.dao.StockInventoryRepository;
import com.stockInventorymanagement.app.entity.StockDetails;

@Service
public class StockInventoryService {
	
	@Autowired
	StockInventoryRepository stockInventoryRepository;

	public StockDetails saveStock(StockDetails stockDetails) {
	 return stockInventoryRepository.save(stockDetails);
	}
	
	public StockDetails updateStock(StockDetails stockDetails) {
	 StockDetails stock = stockInventoryRepository.findById(stockDetails.getStockNumber()).get();
	 stock.setStockName(stockDetails.getStockName());
	 stock.setPurchasingPrice(stockDetails.getPurchasingPrice());
	 stock.setPurchaseDate(stockDetails.getPurchaseDate());
	 stock.setQuantity(stockDetails.getQuantity());
	 return stockInventoryRepository.save(stockDetails);
	}
	
	public List<StockDetails> displaystock() {
	 return stockInventoryRepository.findAll();
	}
	
	public void deleteStock() {
	 List<StockDetails> stockList = stockInventoryRepository.findAll();
	 for(StockDetails stock : stockList) {
	   if(stock.getQuantity()==0) {
		 stockInventoryRepository.delete(stock);  
	   }
	  }
	}
	
	public StockDetails sellingStock(int stockNumber, int quantity) {
	 StockDetails stock = stockInventoryRepository.findById(stockNumber).get();
	 int qty = stock.getQuantity()-quantity;
	 stock.setQuantity(qty);
	 return stockInventoryRepository.save(stock);
	}
}
