package de.flozo.latex;

import java.util.ArrayList;
import java.util.List;

public class ExpressionList {

    public static final String INLINE_SEPARATOR = " ";

    private List<String> lines;


    public ExpressionList(String... lines) {
        this.lines = new ArrayList<>(List.of(lines));
    }


    public ExpressionList append(ExpressionList toAppend) {
        lines.addAll(toAppend.lines);
        return this;    // allow chaining
    }


    // Overloaded getLines() methods with optional parameters

    public List<String> getLines() {
        return lines;
    }

    public List<String> getLines(StatementTerminator terminator) {
        return getLines(terminator, false);
    }

    public List<String> getLines(StatementTerminator terminator, boolean skipLastTerminator) {
        return getLines(terminator, skipLastTerminator, Bracket.NONE);
    }

    public List<String> getLines(StatementTerminator terminator, boolean skipLastTerminator, Bracket brackets) {
        return getLines(terminator, skipLastTerminator, brackets, false);
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
        return getInline(StatementTerminator.COMMA, Bracket.SQUARE_BRACKETS);
    }

    public String getInline(StatementTerminator terminator, Bracket brackets) {
        getLines(terminator, true, Bracket.NONE, false);
        return brackets.getLeftBracket() + String.join(INLINE_SEPARATOR, getLines(terminator, true)) + brackets.getRightBracket();
    }

}
