package mx.naui.agilowen;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AdditionTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AdditionTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AdditionTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAddition()
    {
        assertTrue( true );
    }
}
