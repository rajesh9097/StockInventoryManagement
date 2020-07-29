package com.stockInventorymanagement.app.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<StockDetails> customSort(String name) {
		List<StockDetails> list = stockInventoryRepository.findAll();
		List<StockDetails> result = null;
		if (name.equals("stockName")) {
		 result = list.stream().sorted(Comparator.comparing(StockDetails::getStockName)).collect(Collectors.toList());
		}
		if (name.equals("purchasingPrice")) {
		result = list.stream().sorted(Comparator.comparing(StockDetails::getPurchasingPrice)).collect(Collectors.toList());
		}
		if (name.equals("purchaseDate")) {
		result = list.stream().sorted(Comparator.comparing(StockDetails::getPurchaseDate)).collect(Collectors.toList());
		}
       return result;
	}
}
