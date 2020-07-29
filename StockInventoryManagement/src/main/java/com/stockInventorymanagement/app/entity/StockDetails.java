package com.stockInventorymanagement.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class StockDetails {

	@Id
	@GeneratedValue
	private int stockNumber;

	@Column(name = "stockName")
	@NotEmpty
	private String stockName;

	@Column(name = "purchasingPrice")
	private double purchasingPrice;

	@Column(name = "purchaseDate")
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date purchaseDate;
	
	@Column(name = "quantity")
	private int quantity;
	
	public StockDetails() {
		
	}
	
	public StockDetails(int stockNumber, @NotEmpty String stockName, double purchasingPrice, Date purchaseDate,
			int quantity) {
		super();
		this.stockNumber = stockNumber;
		this.stockName = stockName;
		this.purchasingPrice = purchasingPrice;
		this.purchaseDate = purchaseDate;
		this.quantity = quantity;
	}

	public int getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getPurchasingPrice() {
		return purchasingPrice;
	}

	public void setPurchasingPrice(double purchasingPrice) {
		this.purchasingPrice = purchasingPrice;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
