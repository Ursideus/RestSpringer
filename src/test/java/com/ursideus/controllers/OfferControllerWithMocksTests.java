package com.ursideus.controllers;

import com.ursideus.entities.Offer;
import com.ursideus.services.EmailService;
import com.ursideus.services.OffersService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dovw on 11/24/15.
 */
@Transactional
public class OfferControllerWithMocksTests extends AbstractControllerTest {

    @Mock
    private OffersService offersService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private OfferController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(controller);
    }

    private Collection<Offer> getEntityLidtStubData() {
        Collection<Offer> collection = new ArrayList<Offer>();
        collection.add(getEntityStubData());
        return collection;
    }

    private Offer getEntityStubData() {
        Offer entity = new Offer(1L,"David", "david@email.com", "Nice offer text");
        return entity;
    }

    @Test
    public void testGetOffers() throws Exception {
        /// Get stub data
        Collection<Offer> collection = getEntityLidtStubData();

        ///
        when(offersService.findAll()).thenReturn(collection);

        ///
        String uri = "/api/offers";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        /// Making sure invoked once
        verify(offersService, times(1)).findAll();

        /// Assert
        Assert.assertEquals("failed: expected http 200", 200, status);
        Assert.assertTrue("failed: expected body to have value", content.trim().length() > 0);
    }

}
