package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class Environment3 {

    // constants
    public static final String COMMAND_MARKER_CHAR = "\\";
    public static final String INDENT_CHARACTER = "\t";
    public static final String OPENING_KEYWORD = "begin";
    public static final String CLOSING_KEYWORD = "end";
    public static final Bracket ENVIRONMENT_NAME_BRACKET = Bracket.CURLY_BRACES;

    // constants for option defaults
    public static final Bracket DEFAULT_OPTIONAL_ARGUMENTS_BRACKET = Bracket.SQUARE_BRACKETS;
    public static final Bracket DEFAULT_REQUIRED_ARGUMENTS_BRACKET = Bracket.CURLY_BRACES;
    public static final Bracket DEFAULT_BODY_BRACKETS = Bracket.NONE;
    public static final boolean DEFAULT_INDENT_BODY = true;

    // required
    private final EnvironmentName name;

    // optional
    private final List<String> requiredArguments;
    private final List<String> optionalArguments;
    private final List<String> body;
    private final boolean indentBody;
    private final Bracket optionBrackets;
    private final Bracket bodyBrackets;



    private Environment3(Environment3Builder builder) {
        this.name = builder.name;
        this.requiredArguments = builder.requiredArguments;
        this.optionalArguments = builder.optionalArguments;
        this.body = builder.body;
        this.indentBody = builder.indentBody;
        this.optionBrackets = builder.optionBrackets;
        this.bodyBrackets = builder.bodyBrackets;
    }


    public List<String> getBlock() {
        Command2 command = new Command2.Command2Builder(OPENING_KEYWORD + ENVIRONMENT_NAME_BRACKET.getLeftBracket() + name.getString() + ENVIRONMENT_NAME_BRACKET.getRightBracket())
                .optionList(optionalArguments)
                .body(body)
                .bodyBrackets(bodyBrackets)
                .optionBrackets(optionBrackets)
                .indentBody(indentBody)
                .build();
        List<String> codeLines = new ArrayList<>(command.getBlock());
        codeLines.add(buildTag(CLOSING_KEYWORD));
        return codeLines;
    }

//    private String getFirstLine() {
//        StringBuilder firstLine = new StringBuilder();
//        firstLine.append(buildTag(OPENING_KEYWORD));
//        if (requiredArguments != null) {
//            firstLine.append(buildRequiredArguments().getInline());
//        }
//        if (optionalArguments != null) {
//            firstLine.append(buildOptionalArguments().getInline());
//        }
//        return firstLine.toString();
//    }

    private String buildTag(String tagKeyword) {
        return COMMAND_MARKER_CHAR + tagKeyword + ENVIRONMENT_NAME_BRACKET.getLeftBracket() + name.getString() + ENVIRONMENT_NAME_BRACKET.getRightBracket();
    }
//
//    private List<String> indent(String... code) {
//        return indent(new ArrayList<>(List.of(code)));
//    }
//
//    private List<String> indent(List<String> code) {
//        List<String> indentedCode = new ArrayList<>(code);
//        indentedCode.replaceAll(s -> INDENT_CHARACTER + s);
//        return indentedCode;
//    }
//
//    private ExpressionList2 buildRequiredArguments() {
//        return new ExpressionList2.ExpressionList2Builder(this.requiredArguments)
//                .brackets(DEFAULT_REQUIRED_ARGUMENTS_BRACKET)
//                .terminator(StatementTerminator.COMMA)
//                .build();
//    }

//    private ExpressionList2 buildOptionalArguments() {
//        return new ExpressionList2.ExpressionList2Builder(this.optionalArguments)
//                .brackets(DEFAULT_OPTIONAL_ARGUMENTS_BRACKET)
//                .terminator(StatementTerminator.COMMA)
//                .build();
//    }
//
//    private ExpressionList2 buildBody() {
//        return new ExpressionList2.ExpressionList2Builder(this.body)
//                .brackets(DEFAULT_BODY_BRACKET)
//                .terminator(StatementTerminator.NONE)
//                .build();
//    }

//    private Command2 buildCommand() {
//        return new Command2.Command2Builder(buildTag(OPENING_KEYWORD))
//                .
//                .build();
//    }


    public static class Environment3Builder {

        // required
        private final EnvironmentName name;

        // optional
        private List<String> requiredArguments;
        private List<String> optionalArguments;
        private List<String> body;
        private boolean indentBody = DEFAULT_INDENT_BODY;
        private Bracket optionBrackets = DEFAULT_OPTIONAL_ARGUMENTS_BRACKET;
        private Bracket bodyBrackets = DEFAULT_BODY_BRACKETS;



        public Environment3Builder(EnvironmentName name) {
            this.name = name;
        }

        public Environment3Builder requiredArguments(String... requiredArguments) {
            return requiredArguments(new ArrayList<>(List.of(requiredArguments)));
        }

        public Environment3Builder requiredArguments(List<String> requiredArguments) {
            this.requiredArguments = requiredArguments;
            return this;
        }

        public Environment3Builder optionalArguments(String... optionalArguments) {
            return optionalArguments(new ArrayList<>(List.of(optionalArguments)));
        }

        public Environment3Builder optionalArguments(List<String> optionalArguments) {
            this.optionalArguments = optionalArguments;
            return this;
        }

        public Environment3Builder body(String... body) {
            return body(new ArrayList<>(List.of(body)));
        }

        public Environment3Builder body(List<String> body) {
            this.body = body;
            return this;
        }

        public Environment3Builder indentBody(boolean indentBody) {
            this.indentBody = indentBody;
            return this;
        }

        public Environment3Builder optionBrackets(Bracket optionBrackets) {
            this.optionBrackets = optionBrackets;
            return this;
        }

        public Environment3Builder bodyBrackets(Bracket bodyBrackets) {
            this.bodyBrackets = bodyBrackets;
            return this;
        }


        public Environment3 build() {
            return new Environment3(this);
        }

    }

}
