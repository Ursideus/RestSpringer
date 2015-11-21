package com.ursideus.services;

import com.ursideus.entities.Offer;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dovw on 11/21/15.
 */
@Service
public class OffersServiceBean implements OffersService {

    private static Map<BigInteger, Offer> offersMap = new HashMap<>();

    static {
        Offer offer1 = new Offer();
        offer1.setId(BigInteger.TEN);
        offer1.setName("Dave");
        offer1.setEmail("dave@email.com");
        offer1.setText("Mighty offer");
        offersMap.put(offer1.getId(), offer1);

        Offer offer2 = new Offer();
        offer2.setId(BigInteger.valueOf(11));
        offer2.setName("Dana");
        offer2.setEmail("dana@email.com");
        offer2.setText("Ohh, a nice offer");
        offersMap.put(offer2.getId(), offer2);

    }

    private static Offer addOffer(Offer offer) {
        offersMap.put(offer.getId(), offer);
        return offer;
    }

    private static Offer updatOffer(Offer offer) {
        Offer oldOffer = offersMap.get(offer.getId());

        if (oldOffer == null)
            return null;

        offersMap.remove(offer.getId());
        offersMap.put(offer.getId(), offer);
        return offer;

    }

    private static boolean remove(BigInteger offerId) {

        Offer deletedOffer = offersMap.remove(offerId);
        if (deletedOffer == null)
            return false;
        return true;
    }


    @Override
    public Collection<Offer> findAll() {
        Collection<Offer> offers = offersMap.values();
        return offers;
    }

    @Override
    public Offer findOne(BigInteger id) {
        Offer offer = offersMap.get(id);
        return offer;
    }

    @Override
    public Offer create(Offer offer) {
        Offer savedOffer = addOffer(offer);
        return savedOffer   ;
    }

    @Override
    public Offer update(Offer offer) {
        Offer updatedcOffer = updatOffer(offer);
        return updatedcOffer;
    }

    @Override
    public void delete(BigInteger id) {
        boolean isDeleted = remove(id);
    }
}