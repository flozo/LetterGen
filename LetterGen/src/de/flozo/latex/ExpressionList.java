package de.flozo.latex;

import java.util.ArrayList;
import java.util.List;

public class ExpressionList {

//    public static final String INLINE_SEPARATOR = " ";

    private List<String> lines;
    private final StatementTerminator terminator;
//    private final boolean inline;


    public ExpressionList(String... lines) {
        this.lines = new ArrayList<>(List.of(lines));
        this.terminator = StatementTerminator.NONE;
//        this.inline = false;
    }


//    public ModExtExpressionList(boolean inline, String... lines) {
//        this.lines = new ArrayList<>(List.of(lines));
//        this.terminator = StatementTerminator.NONE;
//        this.inline = inline;
//    }


    public ExpressionList append(ExpressionList toAppend) {
        lines.addAll(toAppend.lines);
        return this;    // allow chaining
    }


    public ExpressionList setTerminator(StatementTerminator terminator) {
        return setTerminator(terminator, false);
    }

    public ExpressionList setTerminator(StatementTerminator terminator, boolean skipLast) {
        lines = addStatementTerminator(terminator, skipLast);
        return this;    // allow chaining
    }


    // Overloaded method without parameter
    public List<String> getLines() {
        return lines;
    }

    public List<String> getLines(StatementTerminator terminator) {
        return getLines(terminator, false, false);
    }

    public List<String> getLines(StatementTerminator terminator, boolean inplace) {
        return getLines(terminator, inplace, false);
    }

    public List<String> getLines(StatementTerminator terminator, boolean inplace, boolean skipLastTerminator) {
        if (terminator == StatementTerminator.NONE) {
            return lines;
        }
        if (inplace) {
            lines = addStatementTerminator(terminator, skipLastTerminator);
            return lines;
        } else {
            return addStatementTerminator(terminator, skipLastTerminator);
        }
    }


    // Overloaded method without skipLast parameter
    private List<String> addStatementTerminator(StatementTerminator terminator) {
        return addStatementTerminator(terminator, false);
    }

    // Overloaded method with skipLast parameter
    private List<String> addStatementTerminator(StatementTerminator terminator, boolean skipLast) {
        List<String> terminatedList = new ArrayList<>(lines);
        if (terminator != StatementTerminator.NONE) {
            if (skipLast) {
                terminatedList.subList(0, terminatedList.size() - 1).replaceAll(s -> s + terminator.getString());
            } else {
                terminatedList.replaceAll(s -> s + terminator.getString());
            }
        }
        return terminatedList;
    }


//    public String makeInline() {
//
//        return String.join(INLINE_SEPARATOR, getLines(1));
//    }

}
