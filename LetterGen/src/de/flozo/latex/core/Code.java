package de.flozo.latex.core;

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
    private final ExpressionList expressionList;

    // optional
    private final StatementTerminator terminator;
    private final boolean inlineSpacing;
    private final boolean skipLast;
    private final Bracket brackets;
    private final Code prepend;
    private final Code append;
//    private final boolean trailingClosingBracket;


    private Code(CodeBuilder builder) {
        this.expressionList = builder.expressionList;
        this.terminator = builder.terminator;
        this.inlineSpacing = builder.inlineSpacing;
        this.skipLast = builder.skipLast;
        this.brackets = builder.brackets;
        this.prepend = builder.prepend;
        this.append = builder.append;
    }

    public ExpressionList getExpressionList() {

        return expressionList;
    }

    public Bracket getBrackets() {
        return brackets;
    }

    public List<String> getBlock() {
        return getBlock(false);
    }

    public List<String> getBlock(boolean skipOpeningBracket) {
        return getBlock(skipOpeningBracket, false);
    }

    public List<String> getBlock(boolean skipOpeningBracket, boolean skipClosingBracket) {
        List<String> codeLines = new ArrayList<>(expressionList.getLines());
        if (terminator != StatementTerminator.NONE) {
            addTerminator(codeLines);
        }
        if (brackets != Bracket.NONE) {
            encloseInBrackets(codeLines, skipOpeningBracket, skipClosingBracket);
        }
        if (prepend != null) {
            codeLines.addAll(0, prepend.getBlock());
        }
        if (append != null) {
            codeLines.addAll(append.getBlock());
        }
        return codeLines;
    }


//    public List<String> getBlock() {
//        List<String> codeLines = new ArrayList<>(expressionList.getLines());
//        if (terminator != StatementTerminator.NONE) {
//            addTerminator(codeLines);
//        }
//        if (brackets != Bracket.NONE) {
//            encloseInBrackets(codeLines);
//        }
//        return codeLines;
//
//}

    public String getInline() {
        return getInline(false, false);
    }

    public String getInline(boolean skipOpeningBracket) {
        return getInline(skipOpeningBracket, false);
    }

    public String getInline(boolean skipOpeningBracket, boolean skipClosingBracket) {
        List<String> codeLines = new ArrayList<>(expressionList.getLines());
        String spacer = INLINE_SEPARATOR;
        if (!inlineSpacing) {
            spacer = "";
        }
        StringBuilder sb = new StringBuilder();
        if (!skipOpeningBracket) {
            sb.append(brackets.getLeftBracket());
        }
        sb.append(String.join(spacer, addTerminator(codeLines)));
        if (!skipClosingBracket) {
            sb.append(brackets.getRightBracket());
        }
        return sb.toString();
    }


    private List<String> addTerminator(List<String> codeLines) {
        if (skipLast) {
            codeLines.subList(0, codeLines.size() - 1).replaceAll(s -> s + terminator.getString());
        } else {
            codeLines.replaceAll(s -> s + terminator.getString());
        }
        return codeLines;
    }

    private void encloseInBrackets(List<String> codeLines, boolean skipOpeningBracket, boolean skipClosingBracket) {
        if (!skipOpeningBracket) {
            codeLines.add(0, brackets.getLeftBracket());
        }
        if (!skipClosingBracket) {
            codeLines.add(brackets.getRightBracket());
        }
    }


    public static class CodeBuilder {

        // required
        private final ExpressionList expressionList;

        // optional; defaults specified
        private StatementTerminator terminator = DEFAULT_TERMINATOR;
        private boolean inlineSpacing = DEFAULT_INLINE_SPACING;
        private boolean skipLast = DEFAULT_SKIP_LAST;
        private Bracket brackets = DEFAULT_BRACKETS;
        private Code prepend = null;
        private Code append = null;


        public CodeBuilder(ExpressionList expressionList) {
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

        public CodeBuilder prepend(Code prepend) {
            this.prepend = prepend;
            return this;
        }

        public CodeBuilder append(Code append) {
            this.append = append;
            return this;
        }


        public Code build() {
            return new Code(this);
        }
    }

}
