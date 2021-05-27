package io.github.tnmy44;

import java.util.*;

class Match{
	
	long sellOrderId;
	long quantity;
	double price;
	long buyOrderId;
	
	public Match(long sellOrderId, long quantity, double price, long buyOrderId){
		this.sellOrderId = sellOrderId;
		this.quantity = quantity;
		this.price = price;
		this.buyOrderId = buyOrderId;
	}

	public String toString(){
		return String.format("\nMatch:<%s,%s,%s,%s>\n", sellOrderId, quantity, price, buyOrderId);
	}
}
