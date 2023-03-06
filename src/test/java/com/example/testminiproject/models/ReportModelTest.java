package com.example.testminiproject.models;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ReportModelTest {

    ReportModel obj1 = new ReportModel(1L,"poo","km3",1,"ห้องแคบไป","mmm.png");
    @Test
    public void test01() {
        assertNotEquals(obj1.getName(),"poo");

    }

    @Test
    public void test02() {
        assertEquals(obj1.getName(), "ob");
    }

    @Test
    public void test03() {
        assertTrue(obj1.getRoom() == 1);
    }

    @Test
    public void test04() {
        assertFalse(obj1.getRoom() == 3);
    }

    @Test
    public void test05(){
        assertSame(obj1.getImage(),"mmm.png");
    }

    @Test
    public void test06(){
        assertNotSame(obj1.getDetails(),obj1.getRoom());
    }

    @Test
    public void test07(){
        assertNotNull(obj1);
    }
}