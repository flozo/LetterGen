package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    // constants for option defaults
    public static final String OPENING_KEYWORD = "begin";
    public static final String CLOSING_KEYWORD = "end";
    public static final Bracket ENVIRONMENT_NAME_BRACKET = Bracket.CURLY_BRACES;
    public static final String SPACE_BEFORE_OPTION_LIST = " ";
    public static final String INDENT_CHARACTER = "\t";
    public static final Bracket DEFAULT_OPTION_BRACKET = Bracket.SQUARE_BRACKETS;
    public static final boolean DEFAULT_INLINE_OPTIONS = true;
    public static final boolean TRAILING_OPENING_BRACKET_OPTIONS = true;
    public static final boolean TRAILING_OPENING_BRACKET_BODY = true;

    // required
    private final EnvironmentName name;
    private final ExpressionList body;

    // optional
    private final ExpressionList optionalArguments;
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


    public List<String> getBlock() {
        List<String> codeLines = new ArrayList<>();
        Code argumentList = new Code.CodeBuilder(optionalArguments)
                .terminator(StatementTerminator.COMMA)
                .skipLast(true)
                .brackets(DEFAULT_OPTION_BRACKET)
                .inlineSpacing(true)
                .build();
        Code bodyLines = new Code.CodeBuilder(body)
                .brackets(Bracket.CURLY_BRACES)
                .build();

        String openingTag = buildTag(OPENING_KEYWORD);
        String closingTag = buildTag(CLOSING_KEYWORD);
        if (inlineOptions) {
            codeLines.add(buildFirstLine(argumentList, bodyLines));
        } else {
            codeLines.add(openingTag);
            codeLines.addAll(indent(argumentList.getBlock()));
            if (trailingOpeningBracketBody) {
                int lastLine = argumentList.getBlock().size();
                codeLines.set(lastLine, codeLines.get(lastLine) + " " + bodyLines.getBrackets().getLeftBracket());
            }
        }
        int startAtLine = trailingOpeningBracketBody ? 1 : 0;
        int stopAtLine = bodyLines.getBlock().size();
        codeLines.addAll(indent(bodyLines.getBlock().subList(startAtLine, stopAtLine)));
        codeLines.add(closingTag);
        return codeLines;
    }

    private String buildFirstLine(Code argumentList, Code body) {
        return buildTag(OPENING_KEYWORD) + SPACE_BEFORE_OPTION_LIST + argumentList.getInline() +
                (trailingOpeningBracketBody ? " " + body.getBrackets().getLeftBracket() : "");
    }

    private String buildTag(String keyword) {
        return "\\" + keyword + ENVIRONMENT_NAME_BRACKET.getLeftBracket() +
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
        private final ExpressionList body;

        // optional; defaults specified
        private ExpressionList optionalArguments = new ExpressionList("");
        private boolean inlineOptions = DEFAULT_INLINE_OPTIONS;
        private boolean trailingOpeningBracketOption = TRAILING_OPENING_BRACKET_OPTIONS;
        private boolean trailingOpeningBracketBody = TRAILING_OPENING_BRACKET_BODY;

        public EnvironmentBuilder(EnvironmentName name, ExpressionList body) {
            this.name = name;
            this.body = body;
        }

        public EnvironmentBuilder optionalArguments(ExpressionList optionalArguments) {
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


//    public static final String OPENING_KEYWORD = "begin";
//    public static final String CLOSING_KEYWORD = "end";
//    public static final String INDENT_CHARACTER = "\t";
//    public static final Bracket DEFAULT_OPTION_BRACKET = ArgumentList.DEFAULT_BRACKETS;
//
//    private final EnvironmentName name;
//    //    private final String argument;
//    private final ArgumentList optionalParameters;
//    private final boolean inlineOptions;
//    private final ExpressionList body;
//    private final List<String> lines;
//
//    private final String openingTag;
//    private final String closingTag;
//
//    public Environment(EnvironmentName name, ArgumentList optionalParameters, boolean inlineOptions, ExpressionList body) {
//        this.name = name;
//        this.optionalParameters = optionalParameters;
//        this.inlineOptions = inlineOptions;
//        this.body = body;
//        this.lines = new ArrayList<>();
//        this.openingTag = "\\" + OPENING_KEYWORD + "{" + name.getString() + "}";
//        this.closingTag = "\\" + CLOSING_KEYWORD + "{" + name.getString() + "}";
//    }
//
//    public List<String> getLines() {
//        if (inlineOptions) {
//            lines.add(openingTag + " " + optionalParameters.getInline());
//        } else {
//            lines.add(openingTag + " " + DEFAULT_OPTION_BRACKET.getLeftBracket());
//            lines.addAll(indent(optionalParameters.getLines().subList(1, optionalParameters.len() + 2)));
//        }
//        lines.addAll(indent(body.getLines()));
//        lines.add(closingTag);
//        return lines;
//    }
//
//    private List<String> indent(List<String> code) {
//        List<String> indentedCode = new ArrayList<>(code);
//        indentedCode.replaceAll(s -> INDENT_CHARACTER + s);
//        return indentedCode;
//    }
//
//    public ArgumentList getOptionalParameters() {
//        return optionalParameters;
//    }
//
//    public ExpressionList getBody() {
//        return body;
//    }
}
