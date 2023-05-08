package com.youngjustin.marketnation.codechallenge.job;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;

import com.youngjustin.marketnation.codechallenge.entity.InputNumberEntity;
import com.youngjustin.marketnation.codechallenge.generator.RandomNumberSupplier;
import com.youngjustin.marketnation.codechallenge.queue.RabbitMQSender;
import com.youngjustin.marketnation.codechallenge.repository.InputNumberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RandomNumberJobTest {

  private static final long RANDOM_NUMBER = 3;

  @Mock private RandomNumberSupplier randomNumberSupplier;

  @Mock private InputNumberRepository inputNumberRepository;

  @Mock private RabbitMQSender rabbitMQSender;

  @InjectMocks private RandomNumberJob randomNumberJob;

  @BeforeAll
  void setup() {}

  @BeforeEach
  void reset() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void run() {
    Mockito.when(this.randomNumberSupplier.get()).thenReturn(RANDOM_NUMBER);

    Assertions.assertDoesNotThrow(() -> this.randomNumberJob.run());

    Mockito.verify(this.randomNumberSupplier, times(1)).get();
    Mockito.verify(this.inputNumberRepository, times(1))
        .save(argThat(x -> x.getNumber() == RANDOM_NUMBER));
    Mockito.verify(this.rabbitMQSender, times(1))
        .send(
            argThat(
                x ->
                    x instanceof InputNumberEntity
                        && ((InputNumberEntity) x).getNumber() == RANDOM_NUMBER));
  }
}
