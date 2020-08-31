package com.wells.stock.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="stock_price")
public class StockPriceEntity {
	@Column(name="CompanyCode")
	private int CompanyCode;
	@Column(name="StockExchange")
	private String StockExchange;
	@Column(name="CurrentPrice")
	private float CurrentPrice;
	@Column(name="Date")
	private LocalDate Date;
	@Column(name="Time")
	private LocalTime Time;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	public int getCompanyCode() {
		return this.CompanyCode;
	}

	public void setCompanyCode(int companyCode) {
		CompanyCode = companyCode;
	}

	public String getStockExchange() {
		return this.StockExchange;
	}

	public void setStockExchange(String stockExchange) {
		StockExchange = stockExchange;
	}

	public float getCurrentPrice() {
		return this.CurrentPrice;
	}

	public void setCurrentPrice(float currentPrice) {
		CurrentPrice = currentPrice;
	}

	public LocalDate getDate() {
		return this.Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}

	public LocalTime getTime() {
		return this.Time;
	}

	public void setTime(LocalTime time) {
		Time = time;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getCompanyCode()+" "+getStockExchange()+" "+getDate()+" "+getTime();
	}
	
}
