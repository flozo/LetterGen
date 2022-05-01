package de.flozo.latex;

import java.util.ArrayList;
import java.util.List;

public class ExpressionList {

    public static final String INLINE_SEPARATOR = " ";

    private List<String> lines;
    private Bracket brackets;
//    private final StatementTerminator terminator;


    public ExpressionList(String... lines) {
        this.lines = new ArrayList<>(List.of(lines));
        this.brackets = Bracket.NONE;
//        this.terminator = StatementTerminator.NONE;
    }


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


    // Overloaded getLines() methods with optional terminator and inplace parameters

    public List<String> getLines() {
        return lines;
    }

    public List<String> getLines(StatementTerminator terminator) {
        return getLines(terminator, false, false);
    }

    public List<String> getLines(StatementTerminator terminator, boolean skipLastTerminator) {
        return getLines(terminator, skipLastTerminator, false);
    }

    public List<String> getLines(StatementTerminator terminator, boolean skipLastTerminator, boolean inplace) {
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


    // Overloaded methods to add a terminator to each list element with optional skipLats parameter

    private List<String> addStatementTerminator(StatementTerminator terminator) {
        return addStatementTerminator(terminator, false);
    }

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


    // Create inline representation of ExpressionList.getLines()

    public String getInline() {
        return getInline(StatementTerminator.COMMA);
    }

    public String getInline(StatementTerminator terminator) {
        return getInline(terminator, true);
    }

    public String getInline(StatementTerminator terminator, boolean skipLastTerminator) {
        return String.join(INLINE_SEPARATOR, getLines(terminator, skipLastTerminator));
    }


    // Enclose in brackets

//    public



}
