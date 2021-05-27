package io.github.tnmy44;

import java.util.*;


class Timestamp implements Comparable<Timestamp>{
	private int hour;
	private int minute;
	
	public Timestamp(int hour, int minute){
		this.hour = hour;
		this.minute = minute;
	}
	
	public String toString(){
		return(String.format("%d:%d", hour, minute));
	}
	
	public int compareTo(Timestamp timestamp){
		int comparison = Integer.compare(hour, timestamp.hour);
		if(comparison != 0) return comparison;
		
		return Integer.compare(minute, timestamp.minute);
	}
	
	
}



enum OrderType{
	BUY,
	SELL,
}

