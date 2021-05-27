package io.github.tnmy44;

import java.util.*;

class OrderParser
{
	
	Timestamp today;
	
	private long parseOrderId(String strOrderId)
	{
		return(Long.parseLong(strOrderId.substring(1)));
	};
	
	private Timestamp parseTimestamp(String strTimestamp){
		String[] hourMinute = strTimestamp.split(":");
		return(new Timestamp(Integer.parseInt(hourMinute[0]), Integer.parseInt(hourMinute[1])));
	}
	
	private OrderType parseOrderType(String strOrderType){
		if("BUY".equalsIgnoreCase(strOrderType))
			return(OrderType.BUY);
		if("SELL".equalsIgnoreCase(strOrderType))
			return(OrderType.SELL);
		throw new IllegalArgumentException("Invalid oder type");
	}
	
	public Order parseOrder(String line)
	{
		String[] splitted = line.split(" ");
		
		long orderId = parseOrderId(splitted[0]);
		Timestamp timestamp = parseTimestamp(splitted[1]);
		String stock = splitted[2];
		OrderType orderType = parseOrderType(splitted[3]);
		long quantity = Integer.parseInt(splitted[4]);
		double price = Double.parseDouble(splitted[5]);
		
		
		Order order = new Order(orderId, timestamp, stock, orderType, quantity, price);
		
		//System.out.println(order);
		return(order);
	}
	
	public String formatMatch(Match match)
	{
		return String.format("#%d %d %.2f #%d", match.sellOrderId, match.quantity, match.price, match.buyOrderId);
	}
}	