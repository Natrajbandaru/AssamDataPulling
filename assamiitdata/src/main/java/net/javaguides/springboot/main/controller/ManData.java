package net.javaguides.springboot.main.controller;

public class ManData {
	public String id;
	public Integer price;
	public String description;
	public String message;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ManData [id=" + id + ", price=" + price + ", description=" + description + ", message=" + message + "]";
	}

	
	

}
