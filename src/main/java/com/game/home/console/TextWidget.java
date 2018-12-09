package com.game.home.console;

public class TextWidget implements Component {

    private final ConsoleScanner scanner;

    private final String title;

    public TextWidget(String title) {
        this.scanner = new ConsoleScanner();
        this.title = title;
    }

    @Override
    public void paint() {
        System.out.println(title);
    }

    public String getValue() {
        return scanner.scanString();
    }
}
