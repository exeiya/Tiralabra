/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Krista
 */
public class TileMapTest {
    
    public TileMapTest() {
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
     * Test of getWidth method, of class TileMap.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        TileMap instance = new TileMap();
        int expResult = 20;
        int result = instance.getWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getHeight method, of class TileMap.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        TileMap instance = new TileMap();
        int expResult = 20;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getType method, of class TileMap.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        int x = 0;
        int y = 0;
        TileMap instance = new TileMap();
        int expResult = 0;
        int result = instance.getType(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
