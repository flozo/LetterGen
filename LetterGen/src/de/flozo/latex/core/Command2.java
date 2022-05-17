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


    // required
    private final CommandName name;

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
    }

    public List<String> getBlock() {
        List<String> codeLines = new ArrayList<>();
        codeLines.add(COMMAND_MARKER_CHAR + name.getString());
        if (optionList != null) {
            if (indentOptions) {
                codeLines.addAll(indent(assembleOptionList().getBlock()));
            } else {
                codeLines.addAll(assembleOptionList().getBlock());
            }
        }
        if (body != null) {
            if (indentBody) {
                codeLines.addAll(indent(assembleBody().getBlock()));
            } else {
                codeLines.addAll(assembleBody().getBlock());
            }
        }
        return codeLines;
    }


    public List<String> getInlineOptions() {
        List<String> codeLines = new ArrayList<>();
        codeLines.add(COMMAND_MARKER_CHAR + name.getString() + assembleOptionList().getInline());
        codeLines.addAll(assembleBody().getBlock());
        return codeLines;
    }

    public String getInline() {
        return COMMAND_MARKER_CHAR + name.getString() + assembleOptionList().getInline() + assembleBody().getInline();
    }


    private ExpressionList2 assembleOptionList() {
        return new ExpressionList2.ExpressionList2Builder(optionList)
                .terminator(optionTerminator)
                .brackets(optionBrackets)
                .skipLastTerminator(skipLastTerminatorOptions)
                .inlineSpacing(inlineSpacingOptions)
                .build();
    }

    private ExpressionList2 assembleBody() {
        return new ExpressionList2.ExpressionList2Builder(body)
                .terminator(bodyTerminator)
                .brackets(bodyBrackets)
                .skipLastTerminator(skipLastTerminatorBody)
                .inlineSpacing(inlineSpacingBody)
                .build();
    }

    private List<String> indent(List<String> code) {
        List<String> indentedCode = new ArrayList<>(code);
        indentedCode.replaceAll(s -> INDENT_CHARACTER + s);
        return indentedCode;
    }

    public static class Command2Builder {

        // required
        private final CommandName name;

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


        public Command2Builder(CommandName name) {
            this.name = name;
        }

        // Accept ExpressionList, List<String>, or any number of Strings as option list.
        // In case of List<String> and String... use specified settings.
        // In case of ExpressionList, take it as is.

        public Command2Builder optionList(String... optionList) {
            return optionList(new ArrayList<>(List.of(optionList)));
        }

        public Command2Builder optionList(List<String> optionList) {
            this.optionList = optionList;
            return this;
        }

//        public Command2Builder optionList(ExpressionList2 optionList) {
//            this.optionList = optionList.getBlock();
//            return this;
//        }


        // Accept ExpressionList, List<String>, or any number of Strings as body.
        // In case of List<String> and String... use specified settings.
        // In case of ExpressionList, take it as is.

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

        public Command2 build() {
            return new Command2(this);
        }
    }


}
