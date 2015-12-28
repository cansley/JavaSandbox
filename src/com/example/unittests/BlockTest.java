package com.example.unittests;

import com.example.helloworld.Block;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Charles on 12/23/2015.
 */
public class BlockTest {
    private Block b;

    @Before
    public void setUp() throws Exception {
        b = new Block(2,2,2);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetWidth() throws Exception {
        assertEquals(2, b.getWidth());
    }

    @Test
    public void testGetLength() throws Exception {
        assertEquals(2, b.getLength());
    }

    @Test
    public void testGetHeight() throws Exception {
        assertEquals(2, b.getHeight());
    }

    @Test
    public void testGetVolume() throws Exception {
        assertEquals(8, b.getVolume());
    }

    @Test
    public void testGetSurfaceArea() throws Exception {
        assertEquals(24, b.getSurfaceArea());
    }
}