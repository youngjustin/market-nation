package com.youngjustin.marketnation.codechallenge.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("scheduler")
public class SchedulerSettings {

  private long delay;

  private long interval;
}
