package io.github.tnmy44;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

//import tnmy44.Exchange;

/**
 * Unit test for exchange.
 */
public class ExchangeTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ExchangeTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ExchangeTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testExchange()
    {
		Exchange exchange = Exchange.getExchange();
        OrderParser orderParser = new OrderParser();
		
		Order order1 = orderParser.parseOrder("#1 09:45 BAC sell 100 240.10");
		Order order2 = orderParser.parseOrder("#2 09:45 BAC sell 90 237.45");
		Order order3 = orderParser.parseOrder("#3 09:47 BAC buy 80 238.10");
		
		Assert.assertTrue(exchange.placeOrder(order1).isEmpty());
		Assert.assertTrue(exchange.placeOrder(order2).isEmpty());
		Assert.assertEquals(orderParser.formatMatch(exchange.placeOrder(order3).get(0)), "#2 80 237.45 #3");
    }
}
