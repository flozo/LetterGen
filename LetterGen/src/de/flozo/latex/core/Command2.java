package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class Command2 {

    // constants
    public static final String COMMAND_MARKER_CHAR = "\\";
    public static final String INDENT_CHARACTER = "\t";

    // constants for option defaults
    public static final StatementTerminator DEFAULT_OPTION_TERMINATOR = StatementTerminator.COMMA;
    public static final StatementTerminator DEFAULT_BODY_TERMINATOR = StatementTerminator.NONE;
    public static final Bracket DEFAULT_OPTION_BRACKETS = Bracket.SQUARE_BRACKETS;
    public static final Bracket DEFAULT_BODY_BRACKETS = Bracket.CURLY_BRACES;
    public static final boolean DEFAULT_SKIP_LAST_TERMINATOR_OPTIONS = true;
    public static final boolean DEFAULT_SKIP_LAST_TERMINATOR_BODY = true;
    public static final boolean DEFAULT_INLINE_SPACING_OPTIONS = true;
    public static final boolean DEFAULT_INLINE_SPACING_BODY = true;
    public static final boolean DEFAULT_INDENT_BODY = true;
    public static final boolean DEFAULT_INDENT_OPTIONS = true;
    public static final boolean DEFAULT_TRAILING_OPENING_BRACKET = true;
    public static final boolean DEFAULT_INTER_BRACKET_SPACE = true;

    // required
    private final String name;

    // optional
    private final List<String> optionList;
    private final List<String> body;
    private final Bracket optionBrackets;
    private final Bracket bodyBrackets;
    private final StatementTerminator optionTerminator;
    private final StatementTerminator bodyTerminator;
    private final boolean skipLastTerminatorOptions;
    private final boolean skipLastTerminatorBody;
    private final boolean inlineSpacingOptions;
    private final boolean inlineSpacingBody;
    private final boolean indentBody;
    private final boolean indentOptions;
    private final boolean trailingOpeningBracket;
    private final boolean interBracketSpace;


    private Command2(Command2Builder builder) {
        this.name = builder.name;
        this.optionList = builder.optionList;
        this.body = builder.body;
        this.optionBrackets = builder.optionBrackets;
        this.bodyBrackets = builder.bodyBrackets;
        this.optionTerminator = builder.optionTerminator;
        this.bodyTerminator = builder.bodyTerminator;
        this.skipLastTerminatorOptions = builder.skipLastTerminatorOptions;
        this.skipLastTerminatorBody = builder.skipLastTerminatorBody;
        this.inlineSpacingOptions = builder.inlineSpacingOptions;
        this.inlineSpacingBody = builder.inlineSpacingBody;
        this.indentBody = builder.indentBody;
        this.indentOptions = builder.indentOptions;
        this.trailingOpeningBracket = builder.trailingOpeningBracket;
        this.interBracketSpace = builder.interBracketSpace;
    }

    public List<String> getBlock() {
        List<String> codeLines = new ArrayList<>();
        codeLines.add(getFirstLine());
        if (optionList != null) {
            codeLines.addAll(getOptionBlock());
        }
        if (trailingOpeningBracket && optionList != null) {
            codeLines.add(bracketLine());
        }
        if (body != null) {
            codeLines.addAll(getBodyBlock());
        }
        return codeLines;
    }

    public List<String> getInlineOptions() {
        List<String> codeLines = new ArrayList<>();
        codeLines.add(getFirstLineInline());
        codeLines.addAll(getBodyBlock());
        return codeLines;
    }

    public String getInline() {
        return COMMAND_MARKER_CHAR + name + assembleOptionList(false).getInline() + assembleBody(false).getInline();
    }


    private List<String> getOptionBlock() {
        boolean skipClosingBracket = body != null && trailingOpeningBracket;
        return new ArrayList<>(assembleOptionList(indentOptions).getBlock(trailingOpeningBracket, skipClosingBracket));
    }

    private List<String> getBodyBlock() {
        return new ArrayList<>(assembleBody(indentBody).getBlock(trailingOpeningBracket, false));
    }

    private ExpressionList2 assembleOptionList(boolean indent) {
        return new ExpressionList2.ExpressionList2Builder(optionList)
                .terminator(optionTerminator)
                .brackets(optionBrackets)
                .skipLastTerminator(skipLastTerminatorOptions)
                .indentBlock(indent)
                .inlineSpacing(inlineSpacingOptions)
                .build();
    }

    private ExpressionList2 assembleBody(boolean indent) {
        return new ExpressionList2.ExpressionList2Builder(body)
                .terminator(bodyTerminator)
                .brackets(bodyBrackets)
                .skipLastTerminator(skipLastTerminatorBody)
                .indentBlock(indent)
                .inlineSpacing(inlineSpacingBody)
                .build();
    }

    private String getFirstLine() {
        StringBuilder firstLine = new StringBuilder(COMMAND_MARKER_CHAR + name);
        if (optionList == null && body == null) {
            return firstLine.toString();
        }
        firstLine.append(interBracketSpace ? " " : "");
        String trailingBracket = optionList == null ? bodyBrackets.getLeftBracket() : optionBrackets.getLeftBracket();
        if (trailingOpeningBracket) {
            firstLine.append(trailingBracket);
        }
        return firstLine.toString();
    }

    private String getFirstLineInline() {
        StringBuilder firstLine = new StringBuilder(COMMAND_MARKER_CHAR + name);
        if (optionList == null && body == null) {
            return firstLine.toString();
        }
        firstLine.append(assembleOptionList(false).getInline());
        firstLine.append(interBracketSpace ? " " : "");
        if (trailingOpeningBracket && body != null) {
            firstLine.append(bodyBrackets.getLeftBracket());
        }
        return firstLine.toString();
    }

    private String bracketLine() {
        StringBuilder line = new StringBuilder();
        if (indentBody) {
            line.append(INDENT_CHARACTER);
        }
        if (optionList != null) {
            line.append(optionBrackets.getRightBracket());
            line.append(interBracketSpace ? " " : "");
        }
        line.append(bodyBrackets.getLeftBracket());
        return line.toString();
    }


    public static class Command2Builder {

        // required
        private final String name;

        // optional
        private List<String> body;
        private List<String> optionList;
        private Bracket optionBrackets = DEFAULT_OPTION_BRACKETS;
        private Bracket bodyBrackets = DEFAULT_BODY_BRACKETS;
        private StatementTerminator optionTerminator = DEFAULT_OPTION_TERMINATOR;
        private StatementTerminator bodyTerminator = DEFAULT_BODY_TERMINATOR;
        private boolean skipLastTerminatorOptions = DEFAULT_SKIP_LAST_TERMINATOR_OPTIONS;
        private boolean skipLastTerminatorBody = DEFAULT_SKIP_LAST_TERMINATOR_BODY;
        private boolean inlineSpacingOptions = DEFAULT_INLINE_SPACING_OPTIONS;
        private boolean inlineSpacingBody = DEFAULT_INLINE_SPACING_BODY;
        private boolean indentBody = DEFAULT_INDENT_BODY;
        private boolean indentOptions = DEFAULT_INDENT_OPTIONS;
        private boolean trailingOpeningBracket = DEFAULT_TRAILING_OPENING_BRACKET;
        private boolean interBracketSpace = DEFAULT_INTER_BRACKET_SPACE;


        public Command2Builder(String name) {
            this.name = name;
        }

        // Accept List<String> or any number of Strings as option list.

        public Command2Builder optionList(String... optionList) {
            return optionList(new ArrayList<>(List.of(optionList)));
        }

        public Command2Builder optionList(List<String> optionList) {
            this.optionList = optionList;
            return this;
        }


        // Accept List<String> or any number of Strings as body.

        public Command2Builder body(String... body) {
            return body(new ArrayList<>(List.of(body)));
        }

        public Command2Builder body(List<String> body) {
            this.body = body;
            return this;
        }

        public Command2Builder optionBrackets(Bracket optionBrackets) {
            this.optionBrackets = optionBrackets;
            return this;
        }

        public Command2Builder bodyBrackets(Bracket bodyBrackets) {
            this.bodyBrackets = bodyBrackets;
            return this;
        }

        public Command2Builder optionTerminator(StatementTerminator optionTerminator) {
            this.optionTerminator = optionTerminator;
            return this;
        }

        public Command2Builder bodyTerminator(StatementTerminator bodyTerminator) {
            this.bodyTerminator = bodyTerminator;
            return this;
        }

        public Command2Builder skipLastTerminatorOptions(boolean skipLastTerminatorOptions) {
            this.skipLastTerminatorOptions = skipLastTerminatorOptions;
            return this;
        }

        public Command2Builder skipLastTerminatorBody(boolean skipLastTerminatorBody) {
            this.skipLastTerminatorBody = skipLastTerminatorBody;
            return this;
        }

        public Command2Builder inlineSpacingOptions(boolean inlineSpacingOptions) {
            this.inlineSpacingOptions = inlineSpacingOptions;
            return this;
        }

        public Command2Builder inlineSpacingBody(boolean inlineSpacingBody) {
            this.inlineSpacingBody = inlineSpacingBody;
            return this;
        }

        public Command2Builder indentBody(boolean indentBody) {
            this.indentBody = indentBody;
            return this;
        }

        public Command2Builder indentOptions(boolean indentOptions) {
            this.indentOptions = indentOptions;
            return this;
        }

        public Command2Builder trailingOpeningBracket(boolean trailingOpeningBracket) {
            this.trailingOpeningBracket = trailingOpeningBracket;
            return this;
        }

        public Command2Builder interBracketSpace(boolean interBracketSpace) {
            this.interBracketSpace = interBracketSpace;
            return this;
        }


        public Command2 build() {
            return new Command2(this);
        }
    }


}
