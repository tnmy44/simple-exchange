package io.github.tnmy44;

import java.util.*;

public class OrderBook{

	PriorityQueue<Order> buyOrders;
	PriorityQueue<Order> sellOrders;
	
	
	public OrderBook(){
		buyOrders = new PriorityQueue<Order>();
		sellOrders = new PriorityQueue<Order>();
	}
	
	public double getBestBuyPrice(){
		return(buyOrders.peek().getPrice());
	}
	
	public Order getBestBuyOrder(){
		return(buyOrders.peek());
	}
	
	public void addBuyOrder(Order order){
		buyOrders.add(order);
	}
	
	public void removeBestBuyOrder(){
		buyOrders.poll();
	}
		
	public double getBestSellPrice(){
		return(sellOrders.peek().getPrice());
	}
	
	public Order getBestSellOrder(){
		return(sellOrders.peek());
	}
	public void removeBestSellOrder(){
		sellOrders.poll();
	}
	public void addSellOrder(Order order){
		sellOrders.add(order);
	}
}