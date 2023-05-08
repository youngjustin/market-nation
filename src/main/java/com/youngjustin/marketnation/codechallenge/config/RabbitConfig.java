package com.youngjustin.marketnation.codechallenge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Autowired private QueueSettings queueSettings;
}
