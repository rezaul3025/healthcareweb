/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.healthcare.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rkarim
 */
public class CommentTest {
    
    public CommentTest() {
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
     * Test of getId method, of class Comment.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Comment instance = new Comment();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Comment.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Comment instance = new Comment();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getComment method, of class Comment.
     */
    @Test
    public void testGetComment() {
        System.out.println("getComment");
        Comment instance = new Comment();
        String expResult = "";
        String result = instance.getComment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setComment method, of class Comment.
     */
    @Test
    public void testSetComment() {
        System.out.println("setComment");
        String comment = "";
        Comment instance = new Comment();
        instance.setComment(comment);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRate method, of class Comment.
     */
    @Test
    public void testGetRate() {
        System.out.println("getRate");
        Comment instance = new Comment();
        Integer expResult = null;
        Integer result = instance.getRate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRate method, of class Comment.
     */
    @Test
    public void testSetRate() {
        System.out.println("setRate");
        Integer rate = null;
        Comment instance = new Comment();
        instance.setRate(rate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
