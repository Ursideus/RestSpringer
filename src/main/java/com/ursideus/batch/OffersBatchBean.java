package com.ursideus.batch;

import com.ursideus.entities.Offer;
import com.ursideus.services.OffersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by dovw on 11/23/15.
 */

@Profile("batch")
@Component
public class OffersBatchBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OffersService offersService;

    ///-- Scheduled task every 1 minute
    //@Scheduled(cron = "0 30 * * * *")
    @Scheduled(cron = "${batch.offers.cron}")
    public void cronBatchStatusTask() {
        logger.info("> batchStatusTask start");

        ///--Task logic here
        Collection<Offer> offers = offersService.findAll();
        logger.info("Current offers saved in DB: {}", offers.size());

        logger.info("<  batchStatusTask end");
    }

    //@Scheduled(initialDelay = 10000, fixedRate = 20000)
    @Scheduled(initialDelayString = "${batch.offers.initialdelay}", fixedRateString = "${batch.offers.fixedrate}")
    public void fixedRateBatchTask() {
        logger.info("> intervalBatchJob start");

        ///--Task logic here
        long waitTimeMili = 5000;
        long startTimeMili = System.currentTimeMillis();
        do {
            if (startTimeMili + waitTimeMili < System.currentTimeMillis())
                break;

        } while (true);

        logger.info("Processing time {} seconds", waitTimeMili / 1000);

        logger.info("<  intervalBatchJob end");

    }

    //@Scheduled(initialDelay = 10000, fixedDelay = 20000)
    @Scheduled(initialDelayString = "${batch.offers.initialdelay}", fixedDelayString = "${batch.offers.fixeddelay}")
    public void fixedDelayBatchTask() {
        logger.info("> fixedDelayBatchTask start");

        ///--Task logic here
        long waitTimeMili = 5000;
        long startTimeMili = System.currentTimeMillis();
        do {
            if (startTimeMili + waitTimeMili < System.currentTimeMillis())
                break;

        } while (true);

        logger.info("Processing time {} seconds", waitTimeMili / 1000);

        logger.info("<  fixedDelayBatchTask end");

    }
}
