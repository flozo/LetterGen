package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

// Make use of Builder Pattern

public class Code {

    public static final String INLINE_SEPARATOR = " ";
    public static final String INTER_BRACKET_SEPARATOR = " ";

    // constants for option defaults
    public static final StatementTerminator DEFAULT_TERMINATOR = StatementTerminator.COMMA;
    public static final boolean DEFAULT_INLINE_SPACING = true;
    public static final Bracket DEFAULT_BRACKETS = Bracket.NONE;
    public static final boolean DEFAULT_SKIP_LAST = true;
    public static final boolean DEFAULT_MERGE_BRACKET_LINES = true;
    public static final boolean DEFAULT_INTER_BRACKET_SPACING = true;

    // required
    private final ExpressionList expressionList;

    // optional
    private final StatementTerminator terminator;
    private final boolean inlineSpacing;
    private final boolean skipLast;
    private final Bracket brackets;
    private final Code prepend;
    private final Code append;
    private final boolean mergeBracketLines;
    private final boolean interBracketSpacing;


    // Constructor using settings from subclass CodeBuilder
    private Code(CodeBuilder builder) {
        this.expressionList = builder.expressionList;
        this.terminator = builder.terminator;
        this.inlineSpacing = builder.inlineSpacing;
        this.skipLast = builder.skipLast;
        this.brackets = builder.brackets;
        this.prepend = builder.prepend;
        this.append = builder.append;
        this.mergeBracketLines = builder.mergeBracketLines;
        this.interBracketSpacing = builder.interBracketSpacing;
    }

    public ExpressionList getExpressionList() {
        return expressionList;
    }

    public Bracket getBrackets() {
        return brackets;
    }

    public List<String> getBlock() {
        List<String> codeLines = new ArrayList<>();
        // Prepend code if given
        if (prepend != null) {
            if (mergeBracketLines) {
                // Prepend code without last line
                codeLines.addAll(getSubBlock(prepend.getBlock(), 0, 1));
            } else {
                codeLines.addAll(prepend.getBlock());
            }
        }
        // Add code of this instance
        codeLines.addAll(assembleCode());
        // Append code if given
        if (append != null) {
            if (mergeBracketLines) {
                // Append code without first line
                codeLines.addAll(getSubBlock(append.getBlock(), 1, 0));
            } else {
                codeLines.addAll(append.getBlock());
            }
        }
        return codeLines;
    }

    // Allow to skip leading or trailing lines easily
    private List<String> getSubBlock(List<String> codeBlock, int skipFirst, int skipLast) {
        return codeBlock.subList(skipFirst, codeBlock.size() - skipLast);
    }


    public String getInline() {
        List<String> codeLines = new ArrayList<>(expressionList.getLines());
        String spacer = INLINE_SEPARATOR;
        if (!inlineSpacing) {
            spacer = "";
        }
        StringBuilder sb = new StringBuilder();
        // Prepend code if given
        if (prepend != null) {
            sb.append(prepend.brackets.getLeftBracket());
            sb.append(String.join(spacer, prepend.getInline()));
            sb.append(prepend.brackets.getRightBracket());
        }
        // Add code of this instance
        addTerminator(codeLines);
        sb.append(brackets.getLeftBracket());
        sb.append(String.join(spacer, codeLines));
        sb.append(brackets.getRightBracket());
        // Append code if given
        if (append != null) {
            sb.append(append.brackets.getLeftBracket());
            sb.append(String.join(spacer, append.getInline()));
            sb.append(append.brackets.getRightBracket());
        }
        return sb.toString();
    }

    
    // Assemble code for this instance
    private List<String> assembleCode() {
        List<String> codeLines = new ArrayList<>(expressionList.getLines());
        if (terminator != StatementTerminator.NONE) {
            addTerminator(codeLines);
        }
        if (brackets != Bracket.NONE) {
            encloseInBrackets(codeLines);
        }
        return codeLines;
    }


    private void addTerminator(List<String> codeLines) {
        if (skipLast) {
            // Append terminator to each code line, except the last one
            codeLines.subList(0, codeLines.size() - 1).replaceAll(s -> s + terminator.getString());
        } else {
            // Append terminator to each code line
            codeLines.replaceAll(s -> s + terminator.getString());
        }
    }

    private void encloseInBrackets(List<String> codeLines) {
        String bracketSeparator = interBracketSpacing ? INTER_BRACKET_SEPARATOR : "";
        if (mergeBracketLines) {
            if (prepend != null) {
                // Compose back-to-back brackets from closing bracket of prepended code
                // and opening Bracket from this instance
                codeLines.add(0, prepend.getBrackets().getRightBracket() + bracketSeparator + brackets.getLeftBracket());
                codeLines.add(brackets.getRightBracket());
            }
            if (append != null) {
                codeLines.add(0, brackets.getLeftBracket());
                // Compose back-to-back brackets from closing bracket of this instance
                // and opening Bracket from appended code
                codeLines.add(brackets.getRightBracket() + bracketSeparator + append.getBrackets().getLeftBracket());
            }
            if (prepend == null && append==null) {
                codeLines.add(0, brackets.getLeftBracket());
                codeLines.add(brackets.getRightBracket());
            }
        } else {
            // Surround with brackets
            codeLines.add(0, brackets.getLeftBracket());
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
        private boolean mergeBracketLines = DEFAULT_MERGE_BRACKET_LINES;
        private boolean interBracketSpacing = DEFAULT_INTER_BRACKET_SPACING;


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

        public CodeBuilder mergeBracketLines(boolean mergeBracketLines) {
            // Set to false if nothing is prepended or appended
            if (prepend == null && append == null) {
                this.mergeBracketLines = false;
            } else {
                this.mergeBracketLines = mergeBracketLines;
            }
            return this;
        }

        public CodeBuilder interBracketSpacing(boolean interBracketSpacing) {
            this.interBracketSpacing = interBracketSpacing;
            return this;
        }

        public Code build() {
            return new Code(this);
        }
    }

}
