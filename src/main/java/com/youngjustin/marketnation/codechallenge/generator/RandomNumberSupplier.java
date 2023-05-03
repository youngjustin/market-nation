package com.youngjustin.marketnation.codechallenge.generator;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Wrapper around Java Random RNG.
 * Note this is not a cryptographically secure implementation.
 */
public class RandomNumberSupplier implements Supplier<Long> {

    private Random rng;

    public RandomNumberSupplier() {
        this.rng = new Random();
    }

    @Override
    public Long get() {
        return this.rng.nextLong();
    }
}
