package de.flozo.latex.core;

import de.flozo.latex.SimpleExpressionList;

import java.util.ArrayList;
import java.util.List;

// Make use of Builder Pattern

public class Code {

    public static final String INLINE_SEPARATOR = " ";

    // constants for option defaults
    public static final StatementTerminator DEFAULT_TERMINATOR = StatementTerminator.COMMA;
    public static final boolean DEFAULT_INLINE_SPACING = true;
    public static final Bracket DEFAULT_BRACKETS = Bracket.NONE;
    public static final boolean DEFAULT_SKIP_LAST = true;

    // required
    private final SimpleExpressionList expressionList;

    // optional
    private final StatementTerminator terminator;
    private final boolean inlineSpacing;
    private final boolean skipLast;
    private final Bracket brackets;
//    private final boolean trailingClosingBracket;



    private Code(CodeBuilder builder) {
        this.expressionList = builder.expressionList;
        this.terminator = builder.terminator;
        this.inlineSpacing = builder.inlineSpacing;
        this.skipLast = builder.skipLast;
        this.brackets = builder.brackets;
    }

    public SimpleExpressionList getExpressionList() {
        return expressionList;
    }

    public Bracket getBrackets() {
        return brackets;
    }

    public List<String> getBlock() {
        List<String> codeLines = new ArrayList<>(expressionList.getLines());
        if (terminator != StatementTerminator.NONE) {
            addTerminator(codeLines);
        }
        if (brackets != Bracket.NONE) {
            encloseInBrackets(codeLines);
        }
        return codeLines;
    }

    public String getInline() {
        List<String> codeLines = new ArrayList<>(expressionList.getLines());
        String spacer = INLINE_SEPARATOR;
        if (!inlineSpacing) {
            spacer = "";
        }
        return brackets.getLeftBracket() + String.join(spacer, addTerminator(codeLines)) + brackets.getRightBracket();
    }

    private List<String> addTerminator(List<String> codeLines) {
        if (skipLast) {
            codeLines.subList(0, codeLines.size() - 1).replaceAll(s -> s + terminator.getString());
        } else {
            codeLines.replaceAll(s -> s + terminator.getString());
        }
        return codeLines;
    }

    private void encloseInBrackets(List<String> codeLines) {
        codeLines.add(0, brackets.getLeftBracket());
        codeLines.add(brackets.getRightBracket());
    }


    public static class CodeBuilder {

        // required
        private final SimpleExpressionList expressionList;

        // optional; defaults specified
        private StatementTerminator terminator = DEFAULT_TERMINATOR;
        private boolean inlineSpacing = DEFAULT_INLINE_SPACING;
        private boolean skipLast = DEFAULT_SKIP_LAST;
        private Bracket brackets = DEFAULT_BRACKETS;


        public CodeBuilder(SimpleExpressionList expressionList) {
            this.expressionList = expressionList;
        }

        public CodeBuilder terminator(StatementTerminator terminator) {
            this.terminator = terminator;
            return this;
        }

        public CodeBuilder inlineSpacing(boolean inlineSpacing) {
            this.inlineSpacing = inlineSpacing;
            return this;
        }

        public CodeBuilder skipLast(boolean skipLast) {
            this.skipLast = skipLast;
            return this;
        }

        public CodeBuilder brackets(Bracket brackets) {
            this.brackets = brackets;
            return this;
        }


        public Code build() {
            return new Code(this);
        }
    }

}
