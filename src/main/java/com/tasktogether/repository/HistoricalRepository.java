package com.tasktogether.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasktogether.model.Historical;

public interface HistoricalRepository extends JpaRepository<Historical, Long>{

}
