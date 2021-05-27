package io.github.tnmy44;

import java.util.*;

class Order implements Comparable<Order>{
	private long orderId;
	private Timestamp timestamp;
	private String stock;
	private OrderType type;
	private long quantity;
	private double price;
	
	
	public Order(long orderId, Timestamp timestamp, String stock, OrderType type, long quantity, double price){
		this.orderId = orderId;
		this.timestamp = timestamp;
		this.stock = stock;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
	}
	
	// Remove given quantity from order's quantity
	public void fillQuantity(long filledQuantity){
		if(quantity - filledQuantity < 0)
			throw new IllegalArgumentException("Fill Quantity must be less than order quantity");

		quantity -= filledQuantity;
	}
	
	public long getQuantity(){
		return(quantity);
	}
	
	public double getPrice(){
		return(price);
	}
	
	public String toString(){
		return(String.format("[Order<#%d,%s,%s,%s,%d,%f>]", orderId, timestamp, stock, type, quantity, price));
	}
	
	public long getOrderId(){
		return(orderId);
	}
	public OrderType getType(){
		return(type);
	}
	
	public String getStock(){
		return stock;
	}
	
	public int compareTo(Order order){
		if(type != order.type)
			throw new IllegalArgumentException("Can't compare buy and sell orders");
		
		int typeSign = type == OrderType.BUY ? -1 : 1;
		
		int comparison = Double.compare(price,order.price);
		if(comparison != 0) return typeSign * comparison;
		
		comparison = timestamp.compareTo(order.timestamp);
		return comparison;
	}
}
