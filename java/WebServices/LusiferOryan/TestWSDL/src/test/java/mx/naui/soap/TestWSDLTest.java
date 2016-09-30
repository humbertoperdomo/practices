package mx.naui.soap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TestWSDLTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestWSDLTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestWSDLTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testTestWSDL()
    {
        assertTrue( true );
    }
}
