package com.ursideus.services;

import com.ursideus.entities.Offer;
import com.ursideus.repositories.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

//    private static Map<BigInteger, Offer> offersMap = new HashMap<>();
//
//    static {
//        Offer offer1 = new Offer();
//        offer1.setId(BigInteger.TEN);
//        offer1.setName("Dave");
//        offer1.setEmail("dave@email.com");
//        offer1.setText("Mighty offer");
//        offersMap.put(offer1.getId(), offer1);
//
//        Offer offer2 = new Offer();
//        offer2.setId(BigInteger.valueOf(11));
//        offer2.setName("Dana");
//        offer2.setEmail("dana@email.com");
//        offer2.setText("Ohh, a nice offer");
//        offersMap.put(offer2.getId(), offer2);
//
//    }
//
//    private static Offer addOffer(Offer offer) {
//        offersMap.put(offer.getId(), offer);
//        return offer;
//    }
//
//    private static Offer updatOffer(Offer offer) {
//        Offer oldOffer = offersMap.get(offer.getId());
//
//        if (oldOffer == null)
//            return null;
//
//        offersMap.remove(offer.getId());
//        offersMap.put(offer.getId(), offer);
//        return offer;
//
//    }
//
//    private static boolean remove(BigInteger offerId) {
//
//        Offer deletedOffer = offersMap.remove(offerId);
//        if (deletedOffer == null)
//            return false;
//        return true;
//    }


    @Override
    public Collection<Offer> findAll() {
        Collection<Offer> offers = offersRepository.findAll();
        return offers;
    }

    @Override
    public Offer findOne(Long id) {
        Offer offer = offersRepository.getOne(id);
        return offer;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false) // override class directive
    public Offer create(Offer offer) {
        if (offer.getId() != null)
            return null;

        Offer savedOffer = offersRepository.save(offer);
        return savedOffer   ;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false) // override class directive
    public Offer update(Offer offer) {
        Offer offerPersisted = findOne(offer.getId());
        if (offerPersisted == null)
            return null;

        Offer updatedOffer = offersRepository.save(offer);
        return updatedOffer;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false) // override class directive
    public void delete(Long id) {
        offersRepository.delete(id); ;
    }
}
