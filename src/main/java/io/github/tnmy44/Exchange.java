package io.github.tnmy44;

import java.util.Scanner;
import java.util.*;



public class Exchange
{
	private HashMap<String,OrderBook> orderBookForStock;

	//Singleton pattern
	private static Exchange exchange;
	
	private Exchange()
	{
		orderBookForStock = new HashMap<String,OrderBook>();
	}
	
	public static Exchange getExchange()
	{
		if (exchange == null)
			exchange = new Exchange();
		return exchange;
	}
	
	public List<Match> placeOrder(Order order){
		String stock = order.getStock();
		if(!orderBookForStock.containsKey(stock))
			orderBookForStock.put(stock, new OrderBook());
		
		OrderBook orderBook = orderBookForStock.get(stock);
		
		if(order.getType() == OrderType.BUY)
			return orderBook.placeBuyOrder(order);
		
		if(order.getType() == OrderType.SELL)
			return orderBook.placeSellOrder(order);
		
		throw new IllegalArgumentException("Invalid order type");
	}
}
