package com.youngjustin.marketnation.codechallenge.scheduler;

public interface Scheduler {

  void start(final Runnable runnable, final long delay, final long interval);

  void stop();

  void cancel();
}
