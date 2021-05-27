package io.github.tnmy44;

import java.util.*;

class SimpleExchangeApp
{
	public static void main(String args[])
	{
		Exchange exchange = Exchange.getExchange();
		
		Scanner input = new Scanner(System.in);
		OrderParser orderParser = new OrderParser();
		
		while (input.hasNextLine()) {
			String line = input.nextLine();
			Order order = orderParser.parseOrder(line);
			List<Match> matches = exchange.placeOrder(order);
			for(Match match : matches)
				System.out.println(orderParser.formatMatch(match));
		}
	}
}