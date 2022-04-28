package de.flozo.latex;

public enum Bracket {
    LEFT_PARENTHESIS('('),
    RIGHT_PARENTHESIS(')'),
    LEFT_SQUARE_BRACKET('['),
    RIGHT_SQUARE_BRACKET(']'),
    LEFT_CURLY_BRACE('{'),
    RIGHT_CURLY_BRACE('{');

    private final char bracketChar;

    Bracket(char bracket) {
        this.bracketChar = bracket;
    }

    @Override
    public String toString() {
        return String.valueOf(bracketChar);
    }
}
