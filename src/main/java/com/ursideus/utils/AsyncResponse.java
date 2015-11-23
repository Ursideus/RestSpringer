package com.ursideus.utils;

import java.util.concurrent.*;

/**
 * Created by dovw on 11/23/15.
 */
public class AsyncResponse<V> implements Future<V> {

    private V value;
    private Exception executionException;
    private boolean isCompletedExceptionally = false;
    private boolean isCancelled = false;
    private boolean isDone = false;
    private long checkCompletedInterval = 100;

    public AsyncResponse() {}

    public AsyncResponse(V val) {
        this.value = val;
        this.isDone = true;
    }

    public AsyncResponse(Throwable ex) {
        this.executionException = new ExecutionException(ex);
        this.isCompletedExceptionally = true;
        this.isDone = true;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        this.isCancelled = true;
        this.isDone = true;
        return false;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    public boolean isCompletedExceptionally() {
        return this.isCompletedExceptionally;
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {

        block(0);

        if (isCancelled()) {
            throw new CancellationException();
        }
        if (isCompletedExceptionally()) {
            throw new ExecutionException(this.executionException);
        }
        if (isDone()) {
            return this.value;
        }

        throw new InterruptedException();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {

        long timeoutMilis = unit.toMillis(timeout);
        block(timeoutMilis);

        if (isCancelled()) {
            throw new CancellationException();
        }
        if (isCompletedExceptionally()) {
            throw new ExecutionException(this.executionException);
        }
        if (isDone()) {
            return this.value;
        }

        throw new InterruptedException();
    }

    public boolean complete(V val) {
        this.value = val;
        this.isDone = true;

        return true;
    }

    public boolean completeExceptionally(Throwable ex) {
        this.value = null;
        this.executionException = new ExecutionException(ex);
        this.isCompletedExceptionally = true;
        this.isDone = true;

        return true;
    }

    public void setCheckCompletedInterval(long milis) {
        this.checkCompletedInterval = milis;
    }

    private void block(long timeoutMilis) throws InterruptedException {
        long startMilis = System.currentTimeMillis();

        /// Wait timeout
        while (!isDone() && !isCancelled()) {
            if (timeoutMilis > 0) {
                long nowMilis = System.currentTimeMillis();
                if (nowMilis > startMilis + timeoutMilis) {
                    break;
                }
            }
            Thread.sleep(checkCompletedInterval);

        }

    }

}


