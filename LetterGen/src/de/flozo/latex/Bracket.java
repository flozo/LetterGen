package de.flozo.latex;

public enum Bracket {
    NONE(""),
    LEFT_PARENTHESIS("("),
    RIGHT_PARENTHESIS(")"),
    LEFT_SQUARE_BRACKET("["),
    RIGHT_SQUARE_BRACKET("]"),
    LEFT_CURLY_BRACE("{"),
    RIGHT_CURLY_BRACE("}");

    private final String bracketString;

    Bracket(String bracket) {
        this.bracketString = bracket;
    }

    @Override
    public String toString() {
        return bracketString;
    }
}
