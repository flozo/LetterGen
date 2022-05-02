package de.flozo.latex.core;

public enum StatementTerminator {
    NONE(""),
    SPACE(" "),
    SEMICOLON(";"),
    COMMA(","),
    DOUBLE_BACKSLASH("\\\\");

    private final String statementTerminator;

    StatementTerminator(String statementTerminator) {
        this.statementTerminator = statementTerminator;
    }

    public String getString() {
        return statementTerminator;
    }
}
