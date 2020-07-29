package com.stockInventorymanagement.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockInventorymanagement.app.entity.StockDetails;

@Repository
public interface StockInventoryRepository extends JpaRepository<StockDetails, Integer>{

}