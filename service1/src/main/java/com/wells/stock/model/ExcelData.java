package com.wells.stock.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExcelData {
	private int CompanyCode;
	private String StockExchange;
	private float CurrentPrice;
	private LocalDate Date;
	private LocalTime Time;
	public int getCompanyCode() {
		return CompanyCode;
	}
	public void setCompanyCode(int companyCode) {
		CompanyCode = companyCode;
	}
	public String getStockExchange() {
		return StockExchange;
	}
	public void setStockExchange(String stockExchange) {
		StockExchange = stockExchange;
	}
	public float getCurrentPrice() {
		return CurrentPrice;
	}
	public void setCurrentPrice(float currentPrice) {
		CurrentPrice = currentPrice;
	}
	public LocalDate getDate() {
		return Date;
	}
	public void setDate(LocalDate date) {
		Date = date;
	}
	public LocalTime getTime() {
		return Time;
	}
	public void setTime(LocalTime time) {
		Time = time;
	}
}
