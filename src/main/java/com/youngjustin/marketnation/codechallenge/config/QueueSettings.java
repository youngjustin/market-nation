package com.youngjustin.marketnation.codechallenge.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("queue")
public class QueueSettings {

  private String name;

  private String exchange;

  private String routingKey;
}
