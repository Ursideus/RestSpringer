package com.ursideus.batch;

import com.ursideus.entities.Offer;
import com.ursideus.services.OffersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by dovw on 11/23/15.
 */

@Component
public class OffersBatchBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OffersService offersService;

    ///-- Scheduled task every 1 minute
    @Scheduled(cron = "0 30 * * * *")
    public void cronBatchStatusTask() {
        logger.info("> batchStatusTask start");

        ///--Task logic here
        Collection<Offer> offers = offersService.findAll();
        logger.info("Current offers saved in DB: {}", offers.size());

        logger.info("<  batchStatusTask end");
    }

    //@Scheduled(initialDelay = 10000, fixedRate = 20000)
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

    @Scheduled(initialDelay = 10000, fixedDelay = 20000)
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
