package com.ursideus.services;

import com.ursideus.entities.Offer;
import com.ursideus.utils.AsyncResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by dovw on 11/23/15.
 */
@Service
public class EmailServiceBean implements EmailService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Boolean send(Offer offer) {
        logger.info("> send start");
        Boolean success = Boolean.FALSE;

        /// fake sending process
        long sendWorktime = 4000;
        try{
            Thread.sleep(sendWorktime);
        } catch (Exception ex) {
            logger.warn("Exception {}", ex);
        }
        success = Boolean.TRUE;

        logger.info("< send ended, processing time was {} secs", sendWorktime/1000);
        return success;
    }

    @Async
    @Override
    public void sendAsync(Offer offer) {
        logger.info("> asyncSend start");
        long startMili = System.currentTimeMillis();
        try {
            send(offer);

        } catch (Exception ex) {
            logger.warn("Exception sending async {}", ex);
        }

        logger.info("< asyncSend ended, processing time was {} secs", (startMili - System.currentTimeMillis())/1000);
    }

    @Async
    @Override
    public Future<Boolean> sendAsyncWithResult(Offer offer) {
        logger.info("> asyncSendWithResult start");
        long startMili = System.currentTimeMillis();

        AsyncResponse<Boolean> response = new AsyncResponse<Boolean>();
        try {
            Boolean sucess = send(offer);
            response.complete(sucess);
        } catch (Exception ex) {
            logger.warn("Exception sending async with result {}", ex);
            response.completeExceptionally(ex);
        }

        logger.info("< asyncSendWithResult ended, processing time was {} secs", (startMili - System.currentTimeMillis())/1000);

        return response;
    }
}
