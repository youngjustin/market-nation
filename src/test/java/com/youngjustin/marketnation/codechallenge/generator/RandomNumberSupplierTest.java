package com.youngjustin.marketnation.codechallenge.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RandomNumberSupplierTest {

  private RandomNumberSupplier randomNumberSupplier;

  @BeforeAll
  void setup() {
    this.randomNumberSupplier = new RandomNumberSupplier();
  }

  @BeforeEach
  void reset() {}

  @Test
  void getDoesNotThrow() {
    Assertions.assertDoesNotThrow(() -> this.randomNumberSupplier.get());
  }

  @Test
  void getsDifferentNumbers() {
    long random1 = this.randomNumberSupplier.get();
    long random2 = this.randomNumberSupplier.get();

    Assertions.assertNotEquals(random1, random2);
  }
}
