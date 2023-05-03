package com.youngjustin.marketnation.codechallenge.logic;

import com.youngjustin.marketnation.codechallenge.entity.InputNumberEntity;
import com.youngjustin.marketnation.codechallenge.generator.RandomNumberSupplier;
import com.youngjustin.marketnation.codechallenge.json.JsonSerde;
import com.youngjustin.marketnation.codechallenge.json.JsonSerializationException;
import com.youngjustin.marketnation.codechallenge.queue.RabbitMQSender;
import com.youngjustin.marketnation.codechallenge.repository.InputNumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

@Slf4j
public class JobLogic implements Runnable {

    final RandomNumberSupplier randomNumberSupplier;

    final InputNumberRepository inputNumberRepository;

    final JsonSerde jsonSerde;

    @Autowired
    RabbitMQSender rabbitMQSender;

    public JobLogic(final RandomNumberSupplier randomNumberSupplier, final InputNumberRepository inputNumberRepository, final JsonSerde jsonSerde) {
        this.randomNumberSupplier = randomNumberSupplier;
        this.inputNumberRepository = inputNumberRepository;
        this.jsonSerde = jsonSerde;
    }

    @Override
    public void run() {
        long number = randomNumberSupplier.get();

        log.trace(String.valueOf(number));

        // prepare entity for saving to repository
        InputNumberEntity inputNumberEntity = new InputNumberEntity();
        inputNumberEntity.setNumber(number);
        // and save it
        this.inputNumberRepository.save(inputNumberEntity);

        // serialize it to JSON
        try {
            String json = this.jsonSerde.serialize(inputNumberEntity);
        }
        catch (JsonSerializationException ex) {
            throw new RuntimeException(ex);
        }

        // submit it to the queue
        this.rabbitMQSender.send(inputNumberEntity);
    }
}
