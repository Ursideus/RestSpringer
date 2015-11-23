package com.ursideus.services;

import com.ursideus.entities.Offer;

import java.util.concurrent.Future;

/**
 * Created by dovw on 11/23/15.
 */
public interface EmailService {

    Boolean send(Offer offer);
    void sendAsync(Offer offer);
    Future<Boolean> sendAsyncWithResult(Offer offer);

}
