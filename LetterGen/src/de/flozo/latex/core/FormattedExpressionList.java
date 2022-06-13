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
    public static final boolean DEFAULT_SKIP_OPENING_BRACKET = false;
    public static final boolean DEFAULT_SKIP_CLOSING_BRACKET = false;

    // required
    private final List<String> expressions;

    // optional
    private final StatementTerminator terminator;
    private final Bracket brackets;
    private final boolean inlineSpacing;
    private final boolean skipLastTerminator;
    private final boolean indentBlock;
    private final boolean skipOpeningBracket;
    private final boolean skipClosingBracket;

    private FormattedExpressionList(FormattedExpressionListBuilder builder) {
        this.expressions = builder.expressions;
        this.terminator = builder.terminator;
        this.brackets = builder.brackets;
        this.inlineSpacing = builder.inlineSpacing;
        this.skipLastTerminator = builder.skipLastTerminator;
        this.indentBlock = builder.indentBlock;
        this.skipOpeningBracket = builder.skipOpeningBracket;
        this.skipClosingBracket = builder.skipClosingBracket;
    }

    // Return raw expressions
    public List<String> getExpressions() {
        return expressions;
    }

    // Return assembled code (optionally) enclosed in brackets as new ArrayList
//    @Override
//    public List<String> getBlock() {
//        return getBlock(false, false);
//    }

    @Override
    public List<String> getBlock() {
        return new ArrayList<>(assembleCode(skipOpeningBracket, skipClosingBracket));
    }

    // Return assembled code with optional additional spacing
    @Override
    public String getInline() {
        if (expressions == null) {
            return "";
        } else {
            return brackets.getLeftBracket() +
                    String.join(inlineSpacing ? INLINE_SEPARATOR : "", assembleCode(true, true)) +
                    brackets.getRightBracket();
        }
    }

    // Return expression with terminator and brackets added
    private List<String> assembleCode(boolean skipOpeningBracket, boolean skipClosingBracket) {
        List<String> codeLines = new ArrayList<>();
        if (expressions != null) {
            codeLines.addAll(expressions);
            if (terminator != StatementTerminator.NONE) {
                addTerminator(codeLines);
            }
            if (brackets != Bracket.NONE) {
                encloseInBrackets(codeLines, skipOpeningBracket, skipClosingBracket);
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
            System.out.println("*****");
            for (String line : codeLines) {
                System.out.println(line);
            }
            codeLines.subList(0, codeLines.size() - 1).replaceAll(s -> s + terminator.getString());
        } else {
            // Append terminator to each code line
            codeLines.replaceAll(s -> s + terminator.getString());
        }
    }

    private void encloseInBrackets(List<String> codeLines, boolean skipOpeningBracket, boolean skipClosingBracket) {
        if (!skipOpeningBracket) {
            codeLines.add(0, brackets.getLeftBracket());
        }
        if (!skipClosingBracket) {
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


    public static class FormattedExpressionListBuilder {

        // required
        private final List<String> expressions;

        // optional
        private StatementTerminator terminator = DEFAULT_TERMINATOR;
        private Bracket brackets = DEFAULT_BRACKETS;
        private boolean inlineSpacing = DEFAULT_INLINE_SPACING;
        private boolean skipLastTerminator = DEFAULT_SKIP_LAST_TERMINATOR;
        private boolean indentBlock = DEFAULT_INDENT;
        private boolean skipOpeningBracket = DEFAULT_SKIP_OPENING_BRACKET;
        private boolean skipClosingBracket = DEFAULT_SKIP_CLOSING_BRACKET;

        // Accept List<String> or any number of Strings for constructor

        public FormattedExpressionListBuilder(String... lines) {
            this.expressions = new ArrayList<>(List.of(lines));
        }

        public FormattedExpressionListBuilder(List<String> expressions) {
            this.expressions = expressions;
        }

        // Accept List<String> or any number of Strings to prepend or append

        public FormattedExpressionListBuilder prepend(String... lines) {
            return prepend(new ArrayList<>(List.of(lines)));
        }

        public FormattedExpressionListBuilder prepend(List<String> prepend) {
            this.expressions.addAll(0, prepend);
            return this;
        }

        public FormattedExpressionListBuilder append(String... lines) {
            return append(new ArrayList<>(List.of(lines)));
        }

        public FormattedExpressionListBuilder append(List<String> append) {
            this.expressions.addAll(append);
            return this;
        }

        public FormattedExpressionListBuilder terminator(StatementTerminator terminator) {
            this.terminator = terminator;
            return this;
        }

        public FormattedExpressionListBuilder brackets(Bracket brackets) {
            this.brackets = brackets;
            return this;
        }

        public FormattedExpressionListBuilder inlineSpacing(boolean inlineSpacing) {
            this.inlineSpacing = inlineSpacing;
            return this;
        }

        public FormattedExpressionListBuilder skipLastTerminator(boolean skipLastTerminator) {
            this.skipLastTerminator = skipLastTerminator;
            return this;
        }

        public FormattedExpressionListBuilder indentBlock(boolean indentBlock) {
            this.indentBlock = indentBlock;
            return this;
        }

        public FormattedExpressionListBuilder skipOpeningBracket(boolean skipOpeningBracket) {
            this.skipOpeningBracket = skipOpeningBracket;
            return this;
        }

        public FormattedExpressionListBuilder skipClosingBracket(boolean skipClosingBracket) {
            this.skipClosingBracket = skipClosingBracket;
            return this;
        }


        public FormattedExpressionList build() {
            return new FormattedExpressionList(this);
        }
    }
}
