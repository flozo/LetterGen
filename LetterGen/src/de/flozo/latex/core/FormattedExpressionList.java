package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class FormattedExpressionList implements ExpressionList {

    // constants
    public static final String INLINE_SEPARATOR = " ";
    public static final String INDENT_CHARACTER = "\t";

    // constants for option defaults
    public static final StatementTerminator DEFAULT_TERMINATOR = StatementTerminator.NONE;
    public static final boolean DEFAULT_INLINE_SPACING = true;
    public static final Bracket DEFAULT_BRACKETS = Bracket.NONE;
    public static final boolean DEFAULT_SKIP_LAST_TERMINATOR = true;
    public static final boolean DEFAULT_INDENT = false;
    public static final BracketMode DEFAULT_OPENING_BRACKET_MODE = BracketMode.SEPARATE_LINE;
    public static final BracketMode DEFAULT_CLOSING_BRACKET_MODE = BracketMode.SEPARATE_LINE;

    // required
    private final List<String> expressions;

    // optional
    private final StatementTerminator terminator;
    private final Bracket brackets;
    private final boolean inlineSpacing;
    private final boolean skipLastTerminator;
    private final boolean indentBlock;
    private final BracketMode openingBracketMode;
    private final BracketMode closingBracketMode;

    private FormattedExpressionList(Builder builder) {
        this.expressions = builder.expressions;
        this.terminator = builder.terminator;
        this.brackets = builder.brackets;
        this.inlineSpacing = builder.inlineSpacing;
        this.skipLastTerminator = builder.skipLastTerminator;
        this.indentBlock = builder.indentBlock;
        this.openingBracketMode = builder.openingBracketMode;
        this.closingBracketMode = builder.closingBracketMode;
    }

    // Return raw expressions
    public List<String> getExpressions() {
        return expressions;
    }


    @Override
    public List<String> getBlock() {
        return new ArrayList<>(assembleCode(openingBracketMode, closingBracketMode));
    }

    // Return assembled code with optional additional spacing
    @Override
    public String getInline() {
        if (expressions == null) {
            return "";
        } else {
            return brackets.getLeftBracket() +
                    String.join(inlineSpacing ? INLINE_SEPARATOR : "", assembleCode(BracketMode.SKIP, BracketMode.SKIP)) +
                    brackets.getRightBracket();
        }
    }

    // Return expression with terminator and brackets added
    private List<String> assembleCode(BracketMode openingBracketMode, BracketMode closingBracketMode) {
        List<String> codeLines = new ArrayList<>();
        if (expressions != null) {
            codeLines.addAll(expressions);
            if (terminator != StatementTerminator.NONE) {
                addTerminator(codeLines);
            }
            if (brackets != Bracket.NONE) {
                encloseInBrackets(codeLines, openingBracketMode, closingBracketMode);
            }
        }
        if (indentBlock) {
            return indent(codeLines);
        }
        return codeLines;
    }

    private void addTerminator(List<String> codeLines) {
        if (skipLastTerminator && codeLines.size() <= 1) {
            return;
        }
        if (skipLastTerminator) {
            // Append terminator to each code line, except the last one
//            System.out.println("*****");
//            for (String line : codeLines) {
//                System.out.println(line);
//            }
            codeLines.subList(0, codeLines.size() - 1).replaceAll(s -> s + terminator.getString());
        } else {
            // Append terminator to each code line
            codeLines.replaceAll(s -> s + terminator.getString());
        }
    }

    private void encloseInBrackets(List<String> codeLines, BracketMode openingBracketMode, BracketMode closingBracketMode) {
        if (openingBracketMode != BracketMode.SKIP) {
            codeLines.add(0, brackets.getLeftBracket());
        }
        if (closingBracketMode != BracketMode.SKIP) {
            codeLines.add(brackets.getRightBracket());
        }
    }

    private List<String> indent(String... code) {
        return indent(new ArrayList<>(List.of(code)));
    }

    private List<String> indent(List<String> code) {
        List<String> indentedCode = new ArrayList<>(code);
        indentedCode.replaceAll(s -> INDENT_CHARACTER + s);
        return indentedCode;
    }


    @Override
    public String toString() {
        return "FormattedExpressionList{" +
                "expressions=" + expressions +
                ", terminator=" + terminator +
                ", brackets=" + brackets +
                ", inlineSpacing=" + inlineSpacing +
                ", skipLastTerminator=" + skipLastTerminator +
                ", indentBlock=" + indentBlock +
                ", openingBracketMode=" + openingBracketMode +
                ", closingBracketMode=" + closingBracketMode +
                '}';
    }

    public static class Builder {

        // required
        private final List<String> expressions;

        // optional
        private StatementTerminator terminator = DEFAULT_TERMINATOR;
        private Bracket brackets = DEFAULT_BRACKETS;
        private boolean inlineSpacing = DEFAULT_INLINE_SPACING;
        private boolean skipLastTerminator = DEFAULT_SKIP_LAST_TERMINATOR;
        private boolean indentBlock = DEFAULT_INDENT;
        private BracketMode openingBracketMode = DEFAULT_OPENING_BRACKET_MODE;
        private BracketMode closingBracketMode = DEFAULT_CLOSING_BRACKET_MODE;

        // Accept List<String> or any number of Strings for constructor

        public Builder(String... lines) {
            this.expressions = new ArrayList<>(List.of(lines));
        }

        public Builder(List<String> expressions) {
            this.expressions = expressions;
        }

        // Accept List<String> or any number of Strings to prepend or append

        public Builder prepend(String... lines) {
            return prepend(new ArrayList<>(List.of(lines)));
        }

        public Builder prepend(List<String> prepend) {
            this.expressions.addAll(0, prepend);
            return this;
        }

        public Builder append(String... lines) {
            return append(new ArrayList<>(List.of(lines)));
        }

        public Builder append(List<String> append) {
            this.expressions.addAll(append);
            return this;
        }

        public Builder terminator(StatementTerminator terminator) {
            this.terminator = terminator;
            return this;
        }

        public Builder brackets(Bracket brackets) {
            this.brackets = brackets;
            return this;
        }

        public Builder inlineSpacing(boolean inlineSpacing) {
            this.inlineSpacing = inlineSpacing;
            return this;
        }

        public Builder skipLastTerminator(boolean skipLastTerminator) {
            this.skipLastTerminator = skipLastTerminator;
            return this;
        }

        public Builder indentBlock(boolean indentBlock) {
            this.indentBlock = indentBlock;
            return this;
        }

        public Builder openingBracketMode(BracketMode openingBracketMode) {
            this.openingBracketMode = openingBracketMode;
            return this;
        }

        public Builder closingBracketMode(BracketMode closingBracketMode) {
            this.closingBracketMode = closingBracketMode;
            return this;
        }


        public FormattedExpressionList build() {
            return new FormattedExpressionList(this);
        }
    }
}
