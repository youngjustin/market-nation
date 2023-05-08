package com.youngjustin.marketnation.codechallenge.config;

import com.youngjustin.marketnation.codechallenge.generator.RandomNumberSupplier;
import com.youngjustin.marketnation.codechallenge.job.RandomNumberJob;
import com.youngjustin.marketnation.codechallenge.json.JacksonJsonSerde;
import com.youngjustin.marketnation.codechallenge.json.JsonSerde;
import com.youngjustin.marketnation.codechallenge.queue.JMSListener;
import com.youngjustin.marketnation.codechallenge.queue.RabbitMQSender;
import com.youngjustin.marketnation.codechallenge.repository.InputNumberRepository;
import com.youngjustin.marketnation.codechallenge.scheduler.FixedRateScheduler;
import com.youngjustin.marketnation.codechallenge.scheduler.Scheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
@Slf4j
public class Beans {

  @Bean
  public RandomNumberSupplier randomNumberSupplier() {
    return new RandomNumberSupplier();
  }

  @Bean
  public Scheduler scheduler() {
    return new FixedRateScheduler();
  }

  @Bean
  public JsonSerde jsonSerde() {
    return new JacksonJsonSerde();
  }

  @Bean
  @Autowired
  public Runnable runnable(
      final RandomNumberSupplier randomNumberSupplier,
      final InputNumberRepository inputNumberRepository,
      final RabbitMQSender rabbitMQSender) {
    return new RandomNumberJob(randomNumberSupplier, inputNumberRepository, rabbitMQSender);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  @Autowired
  Queue queue(final QueueSettings queueSettings) {
    return new Queue(queueSettings.getName(), false);
  }

  @Bean
  @Autowired
  DirectExchange exchange(final QueueSettings queueSettings) {
    return new DirectExchange(queueSettings.getExchange());
  }

  @Bean
  @Autowired
  Binding binding(
      final QueueSettings queueSettings, final Queue queue, final DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(queueSettings.getRoutingKey());
  }

  //    @Bean
  //    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
  //        DefaultJmsListenerContainerFactory factory
  //                = new DefaultJmsListenerContainerFactory();
  //        factory.setConnectionFactory(new CachingConnectionFactory());
  //        return factory;
  //    }

  @Bean
  JMSListener jmsListener() {
    return new JMSListener();
  }
}
