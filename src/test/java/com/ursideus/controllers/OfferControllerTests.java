package com.ursideus.controllers;

import com.ursideus.AbstractControllerTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import javax.transaction.Transactional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dovw on 11/21/15.
 */
@Transactional
public class OfferControllerTests extends AbstractControllerTest {

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void testFindAll() throws Exception {
        /// Arrange
        String uri = "/api/offers";

        /// Act
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        /// Assert
        assertEquals("Failed : expected HTTP status 200", 200, status);
        assertTrue("Failed: expected HTTP response body to have a value", content.trim().length() > 0);
    }
}
