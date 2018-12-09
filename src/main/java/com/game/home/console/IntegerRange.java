package com.game.home.console;

public class IntegerRange {

    private final int min;

    private final int max;

    private IntegerRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static IntegerRange of(int min, int max) {
        if (min > max) {
            throw new IllegalStateException("Max value is less than min. Min: " + min + ", Max: " + max);
        }
        return new IntegerRange(min, max);
    }

    public boolean contains(int value) {
        return value >= min && value <= max;
    }
}
