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
public class DoctorSpecializationTest {
    
    public DoctorSpecializationTest() {
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
     * Test of getId method, of class DoctorSpecialization.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DoctorSpecialization instance = new DoctorSpecialization();
        DoctorSpecializationKey expResult = null;
        DoctorSpecializationKey result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class DoctorSpecialization.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        DoctorSpecializationKey id = null;
        DoctorSpecialization instance = new DoctorSpecialization();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDoctor method, of class DoctorSpecialization.
     */
    @Test
    public void testGetDoctor() {
        System.out.println("getDoctor");
        DoctorSpecialization instance = new DoctorSpecialization();
        Doctor expResult = null;
        Doctor result = instance.getDoctor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDoctor method, of class DoctorSpecialization.
     */
    @Test
    public void testSetDoctor() {
        System.out.println("setDoctor");
        Doctor doctor = null;
        DoctorSpecialization instance = new DoctorSpecialization();
        instance.setDoctor(doctor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpecialization method, of class DoctorSpecialization.
     */
    @Test
    public void testGetSpecialization() {
        System.out.println("getSpecialization");
        DoctorSpecialization instance = new DoctorSpecialization();
        Specialization expResult = null;
        Specialization result = instance.getSpecialization();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSpecialization method, of class DoctorSpecialization.
     */
    @Test
    public void testSetSpecialization() {
        System.out.println("setSpecialization");
        Specialization specialization = null;
        DoctorSpecialization instance = new DoctorSpecialization();
        instance.setSpecialization(specialization);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
