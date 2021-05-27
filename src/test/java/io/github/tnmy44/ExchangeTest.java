package io.github.tnmy44;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
        
		
		
		//exchange.sell(orderId, timestamp, stock, qty, price);
		
		
		assertTrue( exchange != null );
		//assertTrue( exchange2 != null );
    }
}
