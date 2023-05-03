package com.youngjustin.marketnation.codechallenge;

import com.youngjustin.marketnation.codechallenge.config.SchedulerSettings;
import com.youngjustin.marketnation.codechallenge.scheduler.Scheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Startup {

    private final Scheduler scheduler;

    private final Runnable runnable;

    private final SchedulerSettings schedulerSettings;

    @Autowired
    public Startup(final Scheduler scheduler, final Runnable runnable, final SchedulerSettings schedulerSettings) {
        this.scheduler = scheduler;
        this.runnable = runnable;
        this.schedulerSettings = schedulerSettings;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {
        log.info("Application ready!");

        this.startScheduler();
    }

    private void startScheduler() {
        this.scheduler.start(this.runnable, this.schedulerSettings.getDelay(), this.schedulerSettings.getInterval());
    }

}
