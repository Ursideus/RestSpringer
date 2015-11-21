package com.ursideus.entities;

import com.ursideus.AbstractTest;
import com.ursideus.Offer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dovw on 11/21/15.
 */
public class OfferTests extends AbstractTest {

    @Autowired
    protected Offer entity;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetId() {
        assertEquals(entity.getId(), null);
    }

}
