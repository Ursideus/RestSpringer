package com.ursideus;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


/**
 * Created by dovw on 11/20/15.
 */

@RestController
public class OfferController {

    //private final AtomicLong counter = new AtomicLong();
    private static Map<BigInteger, Offer> offersMap = new HashMap<BigInteger, Offer>();

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
    

    @RequestMapping(
            value = "/api/offers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Offer>> getOffers() {
        Collection<Offer> offers = offersMap.values();

        return new ResponseEntity<Collection<Offer>>(offers, HttpStatus.OK);
    }
}

