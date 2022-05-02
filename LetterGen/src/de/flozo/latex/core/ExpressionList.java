package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class ExpressionList {

    public static final String INLINE_SEPARATOR = " ";

    public static final Bracket DEFAULT_BRACKET = Bracket.NONE;
    public static final boolean DEFAULT_SKIP_LAST = false;
    public static final boolean DEFAULT_INPLACE = false;

    public static final StatementTerminator DEFAULT_INLINE_TERMINATOR = StatementTerminator.COMMA;
    public static final Bracket DEFAULT_INLINE_BRACKET = Bracket.SQUARE_BRACKETS;
    public static final boolean DEFAULT_INLINE_SKIP_LAST = true;
    public static final boolean DEFAULT_INLINE_INPLACE = false;

    private List<String> lines;


    public ExpressionList(String... lines) {
        this.lines = new ArrayList<>(List.of(lines));
    }


    public ExpressionList append(ExpressionList toAppend) {
        lines.addAll(toAppend.lines);
        return this;    // allow chaining
    }

    public int len() {
        return lines.size();
    }

    // Overloaded getLines() methods with optional parameters

    public List<String> getLines() {
        return lines;
    }

    public List<String> getLines(StatementTerminator terminator) {
        return getLines(terminator, DEFAULT_SKIP_LAST);
    }

    public List<String> getLines(StatementTerminator terminator, boolean skipLastTerminator) {
        return getLines(terminator, skipLastTerminator, DEFAULT_BRACKET);
    }

    public List<String> getLines(StatementTerminator terminator, boolean skipLastTerminator, Bracket brackets) {
        return getLines(terminator, skipLastTerminator, brackets, DEFAULT_INPLACE);
    }

    public List<String> getLines(StatementTerminator terminator, boolean skipLastTerminator, Bracket brackets, boolean inplace) {
        List<String> newList = new ArrayList<>(lines);
        if (terminator != StatementTerminator.NONE) {
            addTerminator(newList, terminator, skipLastTerminator);
        }
        if (brackets != Bracket.NONE) {
            encloseInBrackets(newList, brackets);
        }
        if (inplace) {
            lines = newList;
            return lines;
        } else {
            return newList;
        }
    }


    private void addTerminator(List<String> expressions, StatementTerminator terminator, boolean skipLast) {
        if (terminator == StatementTerminator.NONE) {
            return;
        }
        if (skipLast) {
            expressions.subList(0, expressions.size() - 1).replaceAll(s -> s + terminator.getString());
        } else {
            expressions.replaceAll(s -> s + terminator.getString());
        }
    }


    private void encloseInBrackets(List<String> expressions, Bracket brackets) {
        if (brackets == Bracket.NONE) {
            return;
        }
        expressions.add(0, brackets.getLeftBracket());
        expressions.add(brackets.getRightBracket());
    }


    // Create inline representation of ExpressionList.getLines()

    public String getInline() {
        return getInline(DEFAULT_INLINE_TERMINATOR, DEFAULT_INLINE_BRACKET);
    }

    public String getInline(StatementTerminator terminator, Bracket brackets) {
        getLines(terminator, DEFAULT_INLINE_SKIP_LAST, Bracket.NONE, DEFAULT_INLINE_INPLACE);
        return brackets.getLeftBracket() + String.join(INLINE_SEPARATOR, getLines(terminator, true)) + brackets.getRightBracket();
    }

}
