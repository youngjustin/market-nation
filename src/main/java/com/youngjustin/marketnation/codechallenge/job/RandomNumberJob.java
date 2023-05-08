package com.youngjustin.marketnation.codechallenge.job;

import com.youngjustin.marketnation.codechallenge.entity.InputNumberEntity;
import com.youngjustin.marketnation.codechallenge.generator.RandomNumberSupplier;
import com.youngjustin.marketnation.codechallenge.queue.RabbitMQSender;
import com.youngjustin.marketnation.codechallenge.repository.InputNumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RandomNumberJob implements Runnable {

  private final RandomNumberSupplier randomNumberSupplier;

  private final InputNumberRepository inputNumberRepository;

  private final RabbitMQSender rabbitMQSender;

  @Autowired
  public RandomNumberJob(
      final RandomNumberSupplier randomNumberSupplier,
      final InputNumberRepository inputNumberRepository,
      final RabbitMQSender rabbitMQSender) {
    this.randomNumberSupplier = randomNumberSupplier;
    this.inputNumberRepository = inputNumberRepository;
    this.rabbitMQSender = rabbitMQSender;
  }

  @Override
  public void run() {
    long number = randomNumberSupplier.get();

    log.trace(String.valueOf(number));

    // prepare entity for saving to repository
    InputNumberEntity inputNumberEntity = new InputNumberEntity();
    inputNumberEntity.setNumber(number);

    // save it to the database
    this.inputNumberRepository.save(inputNumberEntity);

    // submit it to the queue
    this.rabbitMQSender.send(inputNumberEntity);
  }
}
