
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PathfinderTest {
    
    public PathfinderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of aStar method, of class Pathfinder.
     */
    @Test
    public void testAStar() {
        System.out.println("aStar");
        Pathfinder instance = null;
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValid method, of class Pathfinder.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        Node current = null;
        int i = 0;
        int j = 0;
        Pathfinder instance = null;
        boolean expResult = false;
        boolean result = instance.isValid(current, i, j);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcCost method, of class Pathfinder.
     */
    @Test
    public void testCalcCost() {
        System.out.println("calcCost");
        Node current = null;
        Node naapuri = null;
        Pathfinder instance = null;
        int expResult = 0;
        int result = instance.calcCost(current, naapuri);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
