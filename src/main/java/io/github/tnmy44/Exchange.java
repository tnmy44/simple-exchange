package io.github.tnmy44;

import java.util.Scanner;
//import java.time.Timestamp;
import java.util.*;



public class Exchange
{
	
	private HashMap<String,OrderBook> orderBookForStock;

	//Singleton pattern
	private static Exchange exchange;
	
	protected Exchange()
	{
		orderBookForStock = new HashMap<String,OrderBook>();
	}
	
	public static Exchange getExchange()
	{
		if (exchange == null)
			exchange = new Exchange();
		return exchange;
	}
	
	private List<Match> placeBuyOrder(Order order, OrderBook orderBook)
	{	
		Order bestSellOrder;
		List<Match> matches = new ArrayList<Match>();
		
		
		while(order.getQuantity() > 0 &&  (bestSellOrder = orderBook.getBestSellOrder()) != null && order.getPrice() >= bestSellOrder.getPrice()){
			long filledQuantity = order.getQuantity() < bestSellOrder.getQuantity() ? order.getQuantity() : bestSellOrder.getQuantity();
			
			Match match = new Match(bestSellOrder.getOrderId(), filledQuantity, bestSellOrder.getPrice(), order.getOrderId());
			matches.add(match);
			
			order.fillQuantity(filledQuantity);
			bestSellOrder.fillQuantity(filledQuantity);
			
			if(bestSellOrder.getQuantity() <= 0){
				orderBook.removeBestSellOrder();
			}
		}
		
		if(order.getQuantity() > 0)
			orderBook.addBuyOrder(order);
		
		return matches;
	}
	
	private List<Match> placeSellOrder(Order order, OrderBook orderBook)
	{	
		Order bestBuyOrder;
		List<Match> matches = new ArrayList<Match>();
		
		
		while(order.getQuantity() > 0 && (bestBuyOrder = orderBook.getBestBuyOrder()) != null && order.getPrice() <= bestBuyOrder.getPrice()){
			long filledQuantity = order.getQuantity() < bestBuyOrder.getQuantity() ? order.getQuantity() : bestBuyOrder.getQuantity();
			
			Match match = new Match(order.getOrderId(), filledQuantity, order.getPrice(), bestBuyOrder.getOrderId());
			matches.add(match);
			
			order.fillQuantity(filledQuantity);
			bestBuyOrder.fillQuantity(filledQuantity);
			
			if(bestBuyOrder.getQuantity() <= 0){
				orderBook.removeBestBuyOrder();
			}
		}
		
		if(order.getQuantity() > 0)
			orderBook.addSellOrder(order);
		
		return matches;
	}
	
	public List<Match> placeOrder(Order order){
		String stock = order.getStock();
		if(!orderBookForStock.containsKey(stock))
			orderBookForStock.put(stock, new OrderBook());
		
		OrderBook orderBook = orderBookForStock.get(stock);
		
		if(order.getType() == OrderType.BUY)
			return placeBuyOrder(order, orderBook);
		
		return placeSellOrder(order, orderBook);
	}
	
}



class SimpleExchange extends Exchange
{

	public SimpleExchange(){
	}

	
	public static void main(String args[])
	{
		
		Exchange exchange = new SimpleExchange();
		
		Scanner input = new Scanner(System.in);
		OrderParser orderParser = new OrderParser();
		
		while (input.hasNextLine()) {
			String line = input.nextLine();
			Order order = orderParser.parseOrder(line);
			List<Match> matches = exchange.placeOrder(order);
			//for(Match match : matches)
			//	System.out.println(orderParser.formatMatch(match));
		}

		
	}
	
}