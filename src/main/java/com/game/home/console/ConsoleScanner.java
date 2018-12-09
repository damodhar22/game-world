package com.game.home.console;

import java.util.Scanner;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

public class ConsoleScanner {

    private static final Predicate<String> NOT_EMPTY =
            text -> nonNull(text) && !text.isEmpty() && text.chars().noneMatch(Character::isWhitespace);

    private static final Predicate<String> IS_NUMBER =
            text -> nonNull(text) && text.chars().allMatch(Character::isDigit);

    private static final Predicate<String> NOT_NEGATIVE = line -> Integer.parseInt(line) > 0;

    private static final Predicate<String> VALID_INPUT_CONDITION = NOT_EMPTY.and(IS_NUMBER).and(NOT_NEGATIVE);

    public int scanIntegerUntil(Predicate<String> userCondition, Runnable onFail) {
        Predicate<String> retryCondition = VALID_INPUT_CONDITION.and(userCondition).negate();
        String line = null;
        do {
            if (nonNull(line)) {
                onFail.run();
            }
            line = scanString();
        } while (retryCondition.test(line));
        return Integer.parseInt(line);
    }

    private Scanner scanner() {
        return new Scanner(System.in, "UTF-8");
    }

    public String scanString() {
        return scanner().nextLine();
    }
}
