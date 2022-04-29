package de.flozo.latex;

public enum StatementTerminator {
    NONE(""),
    SEMICOLON(";"),
    COMMA(","),
    DOUBLE_BACKSLASH("\\\\");

    private final String statementTerminator;

    StatementTerminator(String statementTerminator) {
        this.statementTerminator = statementTerminator;
    }

    @Override
    public String toString() {
        return statementTerminator;
    }
}
