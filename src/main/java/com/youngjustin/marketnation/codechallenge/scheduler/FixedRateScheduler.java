package com.youngjustin.marketnation.codechallenge.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FixedRateScheduler implements Scheduler {

    private final ScheduledExecutorService executor;

    private ScheduledFuture future;

    public FixedRateScheduler() {
        this.executor = Executors.newScheduledThreadPool(1);
    }

    /**
     * Schedules code to run on a fixed interval.
     * @param runnable The code to run on each execution
     * @param delay The initial delay to run the code (milliseconds)
     * @param interval The interval between runs of the code (milliseconds)
     */
    public void start(final Runnable runnable, final long delay, final long interval) {
        this.executor.scheduleAtFixedRate(runnable, delay, interval, TimeUnit.MILLISECONDS);

        log.info("Scheduler started");
    }

    public void stop() {
        log.info("Nothing scheduled");

        if (this.future == null) {
            return;
        }

        this.future.cancel(false);

        this.future = null;

        log.info("Scheduler stopped");
    }

    public void cancel() {
        log.info("Nothing scheduled");

        if (this.future == null) {
            return;
        }

        this.future.cancel(true);

        this.future = null;

        log.info("Scheduler cancelled");
    }

}
