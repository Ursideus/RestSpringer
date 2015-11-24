package com.ursideus.controllers;

import com.ursideus.entities.Offer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
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

    @Test
    public void testGetOfferNotFound() throws Exception {
        /// Arrange
        String uri = "/api/offers/{id}";
        Long id = new Long(Long.MAX_VALUE);

        /// Act
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id).accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        /// Assert
        //assertEquals("Failed : expected HTTP status 404", 404, status);
        assertTrue("Failed: expected HTTP response body to be empty", content.trim().length() == 0);
    }

    @Test
    public void testCreateOffer() throws Exception {
        /// Arrange
        String uri = "/api/offers";
        Offer offer = new Offer("Michael", "michael@email.com", "Very nice offer");
        String offerJson = super.mapToJson(offer);


        /// Act
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(offerJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        /// Assert
        assertEquals("Failed : expected HTTP status 201", 201, status);
        assertTrue("Failed: expected HTTP response body to be empty", content.trim().length() > 0);
        Offer newOffer = super.mapJsonToObj(content, Offer.class);
        assertNotNull("Failed: expected not null", newOffer);
        assertNotNull("Failed: expected not null", newOffer.getId());
        assertNotNull("Failed: expected not null", newOffer.getName());
    }
}
