package com.youngjustin.marketnation.codechallenge.queue;

import com.youngjustin.marketnation.codechallenge.config.QueueSettings;
import com.youngjustin.marketnation.codechallenge.entity.InputNumberEntity;
import com.youngjustin.marketnation.codechallenge.entity.OutputNumberEntity;
import com.youngjustin.marketnation.codechallenge.repository.OutputNumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class JMSListener {

  @Autowired private QueueSettings queueSettings;

  @Autowired private OutputNumberRepository outputNumberRepository;

  // TODO:
  // Consider registering a custom DefaultJmsListenerContainerFactory bean. For production purposes,
  // you'll typically fine tune timeouts and recovery settings. Most importantly, the default
  // 'AUTO_ACKNOWLEDGE' mode does not provide reliability guarantees, so make sure to use transacted
  // sessions in case of reliability needs.
  // see
  // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jms/annotation/JmsListener.html

  @RabbitListener(queues = {"${queue.name}"})
  public void receiveMessage(final InputNumberEntity message) {
    log.debug("Received message");

    long inputNumber = message.getNumber();
    long outputNumber = 2 * inputNumber;

    // TODO error handling on bounds

    OutputNumberEntity outputNumberEntity = new OutputNumberEntity();
    outputNumberEntity.setNumber(outputNumber);

    this.outputNumberRepository.save(outputNumberEntity);

    // TODO check message type
  }
}
