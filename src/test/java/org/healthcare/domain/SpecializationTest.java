/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.healthcare.domain;

import java.util.Set;
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
public class SpecializationTest {
    
    public SpecializationTest() {
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
     * Test of getId method, of class Specialization.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Specialization instance = new Specialization();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Specialization.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Specialization instance = new Specialization();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Specialization.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Specialization instance = new Specialization();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Specialization.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Specialization instance = new Specialization();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDoctorSpecializations method, of class Specialization.
     */
    @Test
    public void testGetDoctorSpecializations() {
        System.out.println("getDoctorSpecializations");
        Specialization instance = new Specialization();
        Set<DoctorSpecialization> expResult = null;
        Set<DoctorSpecialization> result = instance.getDoctorSpecializations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDoctorSpecializations method, of class Specialization.
     */
    @Test
    public void testSetDoctorSpecializations() {
        System.out.println("setDoctorSpecializations");
        Set<DoctorSpecialization> doctorSpecializations = null;
        Specialization instance = new Specialization();
        instance.setDoctorSpecializations(doctorSpecializations);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
