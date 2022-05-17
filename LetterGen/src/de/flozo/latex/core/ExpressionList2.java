package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class ExpressionList2 {

    // constants
    public static final String INLINE_SEPARATOR = " ";

    // constants for option defaults
    public static final StatementTerminator DEFAULT_TERMINATOR = StatementTerminator.NONE;
    public static final boolean DEFAULT_INLINE_SPACING = true;
    public static final Bracket DEFAULT_BRACKETS = Bracket.NONE;
    public static final boolean DEFAULT_SKIP_LAST_TERMINATOR = true;

    // required
    private final List<String> expressions;

    // optional
    private final StatementTerminator terminator;
    private final Bracket brackets;
    private final boolean inlineSpacing;
    private final boolean skipLastTerminator;

    private ExpressionList2(ExpressionList2Builder builder) {
        this.expressions = builder.expressions;
        this.terminator = builder.terminator;
        this.brackets = builder.brackets;
        this.inlineSpacing = builder.inlineSpacing;
        this.skipLastTerminator = builder.skipLastTerminator;
    }

    // Return raw expressions
    public List<String> getExpressions() {
        return expressions;
    }

    // Return assembled code enclosed in brackets as new ArrayList
    public List<String> getBlock() {
        return new ArrayList<>(assembleCode(true));
    }

    // Return assembled code with optional additional spacing
    public String getInline() {
        return brackets.getLeftBracket() +
                String.join(inlineSpacing ? INLINE_SEPARATOR : "", assembleCode(false)) +
                brackets.getRightBracket();
    }

    // Return expression with terminator and brackets added
    private List<String> assembleCode(boolean addBrackets) {
        List<String> codeLines = new ArrayList<>(expressions);
        if (terminator != StatementTerminator.NONE) {
            addTerminator(codeLines);
        }
        if (brackets != Bracket.NONE && addBrackets) {
            encloseInBrackets(codeLines);
        }
        return codeLines;
    }

    private void addTerminator(List<String> codeLines) {
        if (skipLastTerminator) {
            // Append terminator to each code line, except the last one
            codeLines.subList(0, codeLines.size() - 1).replaceAll(s -> s + terminator.getString());
        } else {
            // Append terminator to each code line
            codeLines.replaceAll(s -> s + terminator.getString());
        }
    }

    private void encloseInBrackets(List<String> codeLines) {
//        if (!skipOpeningBracket) {
        codeLines.add(0, brackets.getLeftBracket());
//        }
        codeLines.add(brackets.getRightBracket());
    }


    public static class ExpressionList2Builder {

        // required
        private final List<String> expressions;

        // optional
        private StatementTerminator terminator = DEFAULT_TERMINATOR;
        private Bracket brackets = DEFAULT_BRACKETS;
        private boolean inlineSpacing = DEFAULT_INLINE_SPACING;
        private boolean skipLastTerminator = DEFAULT_SKIP_LAST_TERMINATOR;

        // Accept List<String> or any number of Strings for constructor

        public ExpressionList2Builder(String... lines) {
            this.expressions = new ArrayList<>(List.of(lines));
        }

        public ExpressionList2Builder(List<String> expressions) {
            this.expressions = expressions;
        }

        // Accept List<String> or any number of Strings to prepend or append

        public ExpressionList2Builder prepend(String... lines) {
            return prepend(new ArrayList<>(List.of(lines)));
        }

        public ExpressionList2Builder prepend(List<String> prepend) {
            this.expressions.addAll(0, prepend);
            return this;
        }

        public ExpressionList2Builder append(String... lines) {
            return append(new ArrayList<>(List.of(lines)));
        }

        public ExpressionList2Builder append(List<String> append) {
            this.expressions.addAll(append);
            return this;
        }

        public ExpressionList2Builder terminator(StatementTerminator terminator) {
            this.terminator = terminator;
            return this;
        }

        public ExpressionList2Builder brackets(Bracket brackets) {
            this.brackets = brackets;
            return this;
        }

        public ExpressionList2Builder inlineSpacing(boolean inlineSpacing) {
            this.inlineSpacing = inlineSpacing;
            return this;
        }

        public ExpressionList2Builder skipLastTerminator(boolean skipLastTerminator) {
            this.skipLastTerminator = skipLastTerminator;
            return this;
        }


        public ExpressionList2 build() {
            return new ExpressionList2(this);
        }
    }
}
