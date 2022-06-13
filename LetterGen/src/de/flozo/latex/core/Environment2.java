package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class Environment2 {

    // constants
    public static final String COMMAND_MARKER_CHAR = "\\";
    public static final String INDENT_CHARACTER = "\t";
    public static final String OPENING_KEYWORD = "begin";
    public static final String CLOSING_KEYWORD = "end";
    public static final Bracket ENVIRONMENT_NAME_BRACKET = Bracket.CURLY_BRACES;

    // constants for option defaults
    public static final Bracket DEFAULT_OPTIONAL_ARGUMENTS_BRACKET = Bracket.SQUARE_BRACKETS;
    public static final Bracket DEFAULT_REQUIRED_ARGUMENTS_BRACKET = Bracket.CURLY_BRACES;
    public static final Bracket DEFAULT_BODY_BRACKET = Bracket.NONE;
    public static final boolean DEFAULT_INDENT_BODY = true;

    // required
    private final EnvironmentName name;

    // optional
    private final List<String> requiredArguments;
    private final List<String> optionalArguments;
    private final List<String> body;
    private final boolean indentBody;


    private Environment2(Environment2Builder builder) {
        this.name = builder.name;
        this.requiredArguments = builder.requiredArguments;
        this.optionalArguments = builder.optionalArguments;
        this.body = builder.body;
        this.indentBody = builder.indentBody;
    }


    public List<String> getBlock() {
        List<String> codeLines = new ArrayList<>();
        codeLines.add(getFirstLine());
        if (body != null) {
            if (indentBody) {
                codeLines.addAll(indent(buildBody().getBlock()));
            } else {
                codeLines.addAll(buildBody().getBlock());
            }
        }
        codeLines.add(buildTag(CLOSING_KEYWORD));
        return codeLines;
    }

    private String getFirstLine() {
        StringBuilder firstLine = new StringBuilder();
        firstLine.append(buildTag(OPENING_KEYWORD));
        if (requiredArguments != null) {
            firstLine.append(buildRequiredArguments().getInline());
        }
        if (optionalArguments != null) {
            firstLine.append(buildOptionalArguments().getInline());
        }
        return firstLine.toString();
    }

    private String buildTag(String tagKeyword) {
        return COMMAND_MARKER_CHAR + tagKeyword + ENVIRONMENT_NAME_BRACKET.getLeftBracket() + name.getString() + ENVIRONMENT_NAME_BRACKET.getRightBracket();
    }

    private List<String> indent(String... code) {
        return indent(new ArrayList<>(List.of(code)));
    }

    private List<String> indent(List<String> code) {
        List<String> indentedCode = new ArrayList<>(code);
        indentedCode.replaceAll(s -> INDENT_CHARACTER + s);
        return indentedCode;
    }

    private FormattedExpressionList buildRequiredArguments() {
        return new FormattedExpressionList.FormattedExpressionListBuilder(this.requiredArguments)
                .brackets(DEFAULT_REQUIRED_ARGUMENTS_BRACKET)
                .terminator(StatementTerminator.COMMA)
                .build();
    }

    private FormattedExpressionList buildOptionalArguments() {
        return new FormattedExpressionList.FormattedExpressionListBuilder(this.optionalArguments)
                .brackets(DEFAULT_OPTIONAL_ARGUMENTS_BRACKET)
                .terminator(StatementTerminator.COMMA)
                .build();
    }

    private FormattedExpressionList buildBody() {
        return new FormattedExpressionList.FormattedExpressionListBuilder(this.body)
                .brackets(DEFAULT_BODY_BRACKET)
                .terminator(StatementTerminator.NONE)
                .build();
    }

//    private Command2 buildCommand() {
//        return new Command2.Command2Builder(buildTag(OPENING_KEYWORD))
//                .
//                .build();
//    }


    public static class Environment2Builder {

        // required
        private final EnvironmentName name;

        // optional
        private List<String> requiredArguments;
        private List<String> optionalArguments;
        private List<String> body;
        private boolean indentBody = DEFAULT_INDENT_BODY;


        public Environment2Builder(EnvironmentName name) {
            this.name = name;
        }

        public Environment2Builder requiredArguments(String... requiredArguments) {
            return requiredArguments(new ArrayList<>(List.of(requiredArguments)));
        }

        public Environment2Builder requiredArguments(List<String> requiredArguments) {
            this.requiredArguments = requiredArguments;
            return this;
        }

        public Environment2Builder optionalArguments(String... optionalArguments) {
            return optionalArguments(new ArrayList<>(List.of(optionalArguments)));
        }

        public Environment2Builder optionalArguments(List<String> optionalArguments) {
            this.optionalArguments = optionalArguments;
            return this;
        }

        public Environment2Builder body(String... body) {
            return body(new ArrayList<>(List.of(body)));
        }

        public Environment2Builder body(List<String> body) {
            this.body = body;
            return this;
        }

        public Environment2Builder indentBody(boolean indentBody) {
            this.indentBody = indentBody;
            return this;
        }

        public Environment2 build() {
            return new Environment2(this);
        }

    }

}
