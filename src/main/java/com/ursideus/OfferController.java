package com.ursideus;

import ch.qos.logback.core.pattern.color.GreenCompositeConverter;
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

//    @RequestMapping(value = "/", method= RequestMethod.GET)
//    public @ResponseBody Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
//    }

//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting(counter.incrementAndGet(),
//                String.format(template, name));
//    }

    @RequestMapping(
            value = "/api/offers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Offer>> getAllOffers() {
        Collection<Offer> offers = offersMap.values();

        return new ResponseEntity<Collection<Offer>>(offers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/offers/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> getOffer(@PathVariable("id") BigInteger id) {
        Offer offer = offersMap.get(id);

        if (offer == null)
            return new ResponseEntity<Offer>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Offer>(offer, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/offers",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer savedOffer = addOffer(offer);

        return new ResponseEntity<Offer>(savedOffer, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/api/offers/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> updateOffer(@RequestBody Offer offer) {
        Offer updatedcOffer = updatOffer(offer);

        if (updatedcOffer == null)
            return new ResponseEntity<Offer>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<Offer>(updatedcOffer, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/offers/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
            //produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> deleteOffer(BigInteger offerId) {
        boolean isDeleted = remove(offerId);

        if (!isDeleted)
            return new ResponseEntity<Offer>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Offer>(HttpStatus.NO_CONTENT);
    }
}

