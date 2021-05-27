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
	
	
	public List<Match> placeBuyOrder(Order order)
	{	
		Order bestSellOrder;
		List<Match> matches = new ArrayList<Match>();
		
		
		while(order.getQuantity() > 0 &&  (bestSellOrder = getBestSellOrder()) != null && order.getPrice() >= bestSellOrder.getPrice()){
			long filledQuantity = order.getQuantity() < bestSellOrder.getQuantity() ? order.getQuantity() : bestSellOrder.getQuantity();
			
			Match match = new Match(bestSellOrder.getOrderId(), filledQuantity, bestSellOrder.getPrice(), order.getOrderId());
			matches.add(match);
			
			order.fillQuantity(filledQuantity);
			bestSellOrder.fillQuantity(filledQuantity);
			
			if(bestSellOrder.getQuantity() <= 0){
				removeBestSellOrder();
			}
		}
		
		if(order.getQuantity() > 0)
			addBuyOrder(order);
		
		return matches;
	}
	
	public List<Match> placeSellOrder(Order order)
	{	
		Order bestBuyOrder;
		List<Match> matches = new ArrayList<Match>();
		
		
		while(order.getQuantity() > 0 && (bestBuyOrder = getBestBuyOrder()) != null && order.getPrice() <= bestBuyOrder.getPrice()){
			long filledQuantity = order.getQuantity() < bestBuyOrder.getQuantity() ? order.getQuantity() : bestBuyOrder.getQuantity();
			
			Match match = new Match(order.getOrderId(), filledQuantity, order.getPrice(), bestBuyOrder.getOrderId());
			matches.add(match);
			
			order.fillQuantity(filledQuantity);
			bestBuyOrder.fillQuantity(filledQuantity);
			
			if(bestBuyOrder.getQuantity() <= 0){
				removeBestBuyOrder();
			}
		}
		
		if(order.getQuantity() > 0)
			addSellOrder(order);
		
		return matches;
	}
	
}