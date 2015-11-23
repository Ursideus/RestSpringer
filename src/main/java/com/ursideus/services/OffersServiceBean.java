package com.ursideus.services;

import com.ursideus.entities.Offer;
import com.ursideus.repositories.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by dovw on 11/21/15.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) // readonly == non modifying method
public class OffersServiceBean implements OffersService {

    @Autowired
    private OffersRepository offersRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Collection<Offer> findAll() {
        Collection<Offer> offers = offersRepository.findAll();
        return offers;
    }

    @Override
    @Cacheable(value = "offers", key = "#id")
    public Offer findOne(Long id) {
        Offer offer = offersRepository.getOne(id);
        return offer;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false) /// override class directive
    @CachePut(value = "offers", key = "#result.id")
    public Offer create(Offer offer) {
        if (offer.getId() != null)
            return null;

        Offer savedOffer = offersRepository.save(offer);
        return savedOffer   ;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false) /// override class directive
    @CachePut(value = "offers", key = "#offer.id")  /// cache value by key from argument to func. offer.
    public Offer update(Offer offer) {
        Offer offerPersisted = findOne(offer.getId());
        if (offerPersisted == null)
            return null;

        Offer updatedOffer = offersRepository.save(offer);
        return updatedOffer;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false) /// override class directive
    @CacheEvict(value = "offers", key = "#id")   /// remove cached value
    public void delete(Long id) {
        offersRepository.delete(id); ;
    }

    @Override
    @CacheEvict(value = "offers", allEntries = true) /// evict all entries from cache
    public void flushCache() {
        /// empty method to hold @CacheEvict annotation
    }
}
