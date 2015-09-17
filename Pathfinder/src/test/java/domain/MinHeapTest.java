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
public class MinHeapTest {
    
    public MinHeapTest() {
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
     * Test of add method, of class MinHeap.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        MinHeap instance = new MinHeap();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        instance.add(1);
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);
        Object expResult2 = 1;
        Object result2 = instance.pollMin();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of pollMin method, of class MinHeap.
     */
    @Test
    public void testPollMin() {
        System.out.println("pollMin");
        MinHeap instance = new MinHeap();
        instance.add(5);
        instance.add(12);
        instance.add(1);
        instance.add(3);
        Object expResult = 1;
        Object result = instance.pollMin();
        assertEquals(expResult, result);
        expResult = 3;
        result = instance.pollMin();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of remove method, of class MinHeap.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        MinHeap instance = new MinHeap();
        instance.add(1);
        instance.remove(1);
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isEmpty method, of class MinHeap.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        MinHeap instance = new MinHeap();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        
        instance.add(1);
        boolean expResult2 = false;
        boolean result2 = instance.isEmpty();
         assertEquals(expResult2, result2);
    }

    
}
