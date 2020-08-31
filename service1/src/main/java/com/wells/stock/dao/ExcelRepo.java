package com.wells.stock.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wells.stock.entity.StockPriceEntity;


public interface ExcelRepo extends JpaRepository<StockPriceEntity,Integer> {

}
