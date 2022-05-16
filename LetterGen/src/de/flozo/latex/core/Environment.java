package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    // constants
    public static final String COMMAND_MARKER_CHAR = "\\";
    public static final String OPENING_KEYWORD = "begin";
    public static final String CLOSING_KEYWORD = "end";
    public static final String OPENING_COMMAND = COMMAND_MARKER_CHAR + OPENING_KEYWORD;
    public static final String CLOSING_COMMAND = COMMAND_MARKER_CHAR + CLOSING_KEYWORD;
    public static final Bracket ENVIRONMENT_NAME_BRACKET = Bracket.CURLY_BRACES;
    public static final String SPACE_BEFORE_OPTION_LIST = " ";
    public static final String INDENT_CHARACTER = "\t";
    public static final Bracket DEFAULT_OPTION_BRACKET = Bracket.SQUARE_BRACKETS;
    public static final boolean DEFAULT_INLINE_OPTIONS = true;
    public static final boolean TRAILING_OPENING_BRACKET_OPTIONS = true;
    public static final boolean TRAILING_OPENING_BRACKET_BODY = true;

    // required
    private final EnvironmentName name;
    //    private final ExpressionList body;
    private final Code body;

    // optional
    private final Code optionalArguments;
    //    private final ExpressionList optionalArguments;
    private final boolean inlineOptions;
    private final boolean trailingOpeningBracketOption;
    private final boolean trailingOpeningBracketBody;


    private Environment(EnvironmentBuilder builder) {
        this.name = builder.name;
        this.body = builder.body;
        this.optionalArguments = builder.optionalArguments;
        this.inlineOptions = builder.inlineOptions;
        this.trailingOpeningBracketOption = builder.trailingOpeningBracketOption;
        this.trailingOpeningBracketBody = builder.trailingOpeningBracketBody;
    }

    public ExpressionList getExpressionList() {
        return new ExpressionList(getBlock());
    }

    public List<String> getBlock() {
        List<String> codeLines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(openingTag());
        if (optionalArguments == null) {
            codeLines.add(sb.toString());
            codeLines.addAll(indent(body.getBlock()));
            codeLines.add(closingTag());
            return codeLines;
        }
        if (inlineOptions) {
            sb.append(optionalArguments.getInline());
            codeLines.add(sb.toString());
            codeLines.addAll(indent(body.getBlock()));
            codeLines.add(closingTag());
            return codeLines;
        }
        codeLines.add(sb.toString());
        codeLines.addAll(indent(optionalArguments.getBlock()));
        codeLines.addAll(indent(body.getBlock()));
        codeLines.add(closingTag());

        return codeLines;
    }

    private String buildFirstLine(Code argumentList, Code body) {
        // Example: \begin{tikzpicture} [inner xsep=0pt, inner ysep=0pt] {
        return buildTag(OPENING_COMMAND) + SPACE_BEFORE_OPTION_LIST + argumentList.getInline() +
                (trailingOpeningBracketBody ? " " + body.getBrackets().getLeftBracket() : "");
    }

    private String openingTag() {
        // Example: \begin
        return buildTag(OPENING_COMMAND);
    }

    private String closingTag() {
        // Example: \end
        return buildTag(CLOSING_COMMAND);
    }

    private String buildTag(String command) {
        // Example: \begin{tikzpicture}
        return command + ENVIRONMENT_NAME_BRACKET.getLeftBracket() +
                name.getString() + ENVIRONMENT_NAME_BRACKET.getRightBracket();
    }

    private List<String> indent(List<String> code) {
        List<String> indentedCode = new ArrayList<>(code);
        indentedCode.replaceAll(s -> INDENT_CHARACTER + s);
        return indentedCode;
    }

    public static class EnvironmentBuilder {

        // required
        private final EnvironmentName name;
        private final Code body;

        // optional; defaults specified
        private Code optionalArguments;
        private boolean inlineOptions = DEFAULT_INLINE_OPTIONS;
        private boolean trailingOpeningBracketOption = TRAILING_OPENING_BRACKET_OPTIONS;
        private boolean trailingOpeningBracketBody = TRAILING_OPENING_BRACKET_BODY;


        public EnvironmentBuilder(EnvironmentName name, ExpressionList body) {
            this(name, new Code.CodeBuilder(body)
                    .brackets(Bracket.CURLY_BRACES)
                    .build());
        }

        public EnvironmentBuilder(EnvironmentName name, Code body) {
            this.name = name;
            this.body = body;
        }


        public EnvironmentBuilder optionalArguments(ExpressionList optionalArguments) {
            Code argumentList = new Code.CodeBuilder(optionalArguments)
                    .terminator(StatementTerminator.COMMA)
                    .skipLast(true)
                    .brackets(DEFAULT_OPTION_BRACKET)
                    .inlineSpacing(true)
                    .build();
            return optionalArguments(argumentList);
        }


        public EnvironmentBuilder optionalArguments(Code optionalArguments) {
            this.optionalArguments = optionalArguments;
            return this;
        }

        public EnvironmentBuilder inlineOptions(boolean inlineOptions) {
            this.inlineOptions = inlineOptions;
            return this;
        }

        public EnvironmentBuilder trailingOpeningBracketOption(boolean trailingOpeningBracketOption) {
            this.trailingOpeningBracketOption = trailingOpeningBracketOption;
            return this;
        }

        public EnvironmentBuilder trailingOpeningBracketBody(boolean trailingOpeningBracketBody) {
            this.trailingOpeningBracketBody = trailingOpeningBracketBody;
            return this;
        }


        public Environment build() {
            return new Environment(this);
        }

    }


}
