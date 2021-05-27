package io.github.tnmy44;

import java.util.*;

class Match{
	
	public Match(long sellOrderId, long quantity, double price, long buyOrderId){
		System.out.println(String.format("\nMatch:<%s,%s,%s,%s>\n", sellOrderId, quantity, price, buyOrderId));
	}

}
