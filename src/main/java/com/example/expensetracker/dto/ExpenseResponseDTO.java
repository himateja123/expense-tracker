package com.example.expensetracker.dto;

public class ExpenseResponseDTO {
	private Long id;
	private String title;
	private String date;
	private double amount;
	private String category;
	
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDate() {
		return date;
	}
	public double getAmount() {
		return amount;
	}
	public String getCategory() {
		return category;
	}
	public ExpenseResponseDTO(Long id, String title, String date, double amount, String category) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.amount = amount;
		this.category = category;
	}

}
