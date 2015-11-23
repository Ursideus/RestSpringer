package com.ursideus.services;

import com.ursideus.entities.Offer;

import java.util.concurrent.CompletableFuture;

/**
 * Created by dovw on 11/23/15.
 */
public interface EmailServiceNew {

    Boolean send(Offer offer);
    void sendAsync(Offer offer);
    CompletableFuture<Boolean> sendAsyncWithResult(Offer offer);

}
