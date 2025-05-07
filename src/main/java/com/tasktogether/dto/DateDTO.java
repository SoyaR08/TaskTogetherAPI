package com.tasktogether.dto;

import java.time.LocalDate;

public class DateDTO {

	/*
	 * Esta clase ha sido creada porque al parecer las fechas en json 
	 * se representan como objetos de enteros
	 * */
	
	private Integer year;
	private Integer month;
	private Integer day;
	public DateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DateDTO(Integer year, Integer month, Integer day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	
	public LocalDate toLocalDate() {
		return LocalDate.of(this.year, this.month, this.day);
	}
	
}
