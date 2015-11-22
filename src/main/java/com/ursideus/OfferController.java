package com.ursideus;

import com.ursideus.entities.Offer;
import com.ursideus.services.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;


/**
 * Created by dovw on 11/20/15.
 */

@RestController
public class OfferController {

    @Autowired
    private OffersService offersService;

    @RequestMapping(
            value = "/api/offers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Offer>> getAllOffers() {
        Collection<Offer> offers = offersService.findAll();

        return new ResponseEntity<Collection<Offer>>(offers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/offers/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> getOffer(@PathVariable("id") Long id) {
        Offer offer = offersService.findOne(id);

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
        Offer savedOffer = offersService.create(offer);

        return new ResponseEntity<Offer>(savedOffer, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/api/offers",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> updateOffer(@RequestBody Offer offer) {
        Offer updatedcOffer = offersService.update(offer);

        if (updatedcOffer == null)
            return new ResponseEntity<Offer>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<Offer>(updatedcOffer, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/offers/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
            //produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> deleteOffer(@PathVariable("id") Long offerId) {

        offersService.delete(offerId);
//        boolean isDeleted = offersService.delete(offerId);
//
//        if (!isDeleted)
//            return new ResponseEntity<Offer>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Offer>(HttpStatus.NO_CONTENT);
    }
}

