package com.ursideus.services;

import com.ursideus.AbstractTest;
import com.ursideus.entities.Offer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.net.NoRouteToHostException;
import java.util.Collection;

/**
 * Created by dovw on 11/24/15.
 */
@Transactional
public class OffersServiceBeanTests extends AbstractTest {

    @Autowired
    private OffersService service;

    @Before
    public void setUp() {
        service.create(new Offer("John", "john@email.com", "Very nice offer"));
        service.create(new Offer("Marry", "marry@email.com", "Looooooong offer details"));
        service.flushCache();
    }

    @After
    public void tearDown() {
        Collection<Offer> collection =  service.findAll();
        for (Offer offer : collection) {
            service.delete(offer.getId());
        }
        service.flushCache();
    }

    @Test
    public void testFindAll() throws Exception {
        /// Arrange
        //service.create(new Offer("John", "john@email.com", "Very nice offer"));
        //service.create(new Offer("Marry", "marry@email.com", "Looooooong offer details"));

        /// Act
        Collection<Offer> collection = service.findAll();

        /// Assert
        Assert.assertNotNull("fail - expected not null", collection);
        Assert.assertEquals("fail - expected not null", 2, collection.size());
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        /// Arrange
        Exception ex = null;
        Offer entity = new Offer();
        entity.setId(Long.MAX_VALUE);
        entity.setText("Test Text");

        /// Act
        try {
            service.update(entity);
        } catch (Exception rtex) {
            ex = rtex;
        }

        /// Assert
        Assert.assertNotNull("fail - expected exception null", ex);
        Assert.assertTrue("fail - expected NoResultException", (ex instanceof NoResultException || ex instanceof EntityNotFoundException || ex instanceof JpaObjectRetrievalFailureException));
    }

    @Test
    public void testDeleteOffer() throws Exception {
        /// Arrange
        Collection<Offer> initialCollection = service.findAll();
        Long id = initialCollection.iterator().next().getId();
        //Long id = new Long(1);
        Offer entityToDelete = service.findOne(id);

        /// Act
        service.delete(id);
        Collection<Offer> collection = service.findAll();

        Offer entity = service.findOne(id);
        /// Assert
        Assert.assertNotNull("fail - expected not null", entityToDelete);
        Assert.assertEquals("fail - expected size 1", 1, collection.size());
        //Assert.assertNull("fail - expected null", entity);

    }

}
