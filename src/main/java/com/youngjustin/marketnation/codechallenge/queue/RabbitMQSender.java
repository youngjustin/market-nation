package com.youngjustin.marketnation.codechallenge.queue;

import com.youngjustin.marketnation.codechallenge.config.QueueSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@Slf4j
public class RabbitMQSender {

    @Autowired
    private QueueSettings queueSettings;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Object obj) {
        this.rabbitTemplate.convertAndSend(this.queueSettings.getExchange(), this.queueSettings.getRoutingKey(), obj);

        log.trace(MessageFormat.format("Sending object: {0}", obj));
    }
}
