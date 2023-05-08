package com.youngjustin.marketnation.codechallenge;

import com.youngjustin.marketnation.codechallenge.config.SchedulerSettings;
import com.youngjustin.marketnation.codechallenge.scheduler.Scheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/** Event listener that schedules a Runnable when Spring is done initializing. */
@Component
@Slf4j
public class Startup {

  private final Scheduler scheduler;

  private final Runnable runnable;

  private final SchedulerSettings schedulerSettings;

  @Autowired
  public Startup(
      final Scheduler scheduler,
      final Runnable runnable,
      final SchedulerSettings schedulerSettings) {
    if (scheduler == null) {
      throw new IllegalArgumentException("scheduler is required");
    }

    if (runnable == null) {
      throw new IllegalArgumentException("runnable is required");
    }

    if (schedulerSettings == null) {
      throw new IllegalArgumentException("schedulerSettings is required");
    }

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
    this.scheduler.start(
        this.runnable, this.schedulerSettings.getDelay(), this.schedulerSettings.getInterval());
  }
}
