package com.ursideus.services;

import com.ursideus.entities.Offer;

import java.math.BigInteger;
import java.util.Collection;

/**
 * Created by dovw on 11/21/15.
 */
public interface OffersService {

    Collection<Offer> findAll();
    Offer findOne(BigInteger id);
    Offer create(Offer offer);
    Offer update(Offer offer);
    void delete(BigInteger id);
}
