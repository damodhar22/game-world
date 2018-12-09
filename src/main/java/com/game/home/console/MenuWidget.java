package com.game.home.console;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MenuWidget<T extends Enum> implements Component {

    private static final int MENU_ITEM_OFFSET = 1;

    private static final Function<? super Enum, String> ENUM_TO_STRING =
            theEnum -> theEnum.ordinal() + MENU_ITEM_OFFSET + ". " + theEnum;

    private final ConsoleScanner reader = new ConsoleScanner();

    private final String title;

    private final T[] items;

    private final Runnable rePaintWithWarningMessage = () -> {
        repaint();
        paintMenuFooter(true);
    };

    private final IntegerRange acceptableItems;

    @SafeVarargs
    public MenuWidget(String title, T... items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("There are no items in the menu");
        }
        this.title = title;
        this.items = items;

        this.acceptableItems = IntegerRange.of(1, items.length);
    }

    @Override
    public void paint() {
        System.out.println(title);
        Stream.of(items).map(ENUM_TO_STRING).forEach(System.out::println);
    }

    public T selectItem() {
        paintMenuFooter(false);
        return items[getItemIndex()];
    }

    private void paintMenuFooter(boolean hasToPrintWarning) {
        if (hasToPrintWarning) {
            System.out.println("Entered number is incorrect. Please, enter correct one.");
        }
        System.out.println("Enter number of operation which you want to do:");
    }

    private int getItemIndex() {
        return reader.scanIntegerUntil(isItemInAcceptableRange(), rePaintWithWarningMessage) - MENU_ITEM_OFFSET;
    }

    private Predicate<String> isItemInAcceptableRange() {
        return line -> acceptableItems.contains(Integer.parseInt(line));
    }
}
