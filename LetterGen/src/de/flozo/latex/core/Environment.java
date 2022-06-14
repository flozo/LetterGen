package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class Environment {

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


    private Environment(EnvironmentBuilder builder) {
        this.name = builder.name;
        this.requiredArguments = builder.requiredArguments;
        this.optionalArguments = builder.optionalArguments;
        this.body = builder.body;
        this.indentBody = builder.indentBody;
        this.optionBrackets = builder.optionBrackets;
        this.bodyBrackets = builder.bodyBrackets;
    }


    public List<String> getBlock() {
        Command2 command = new Command2.Command2Builder(buildOpeningTag())
                .optionList(optionalArguments)
                .body(body)
                .bodyBrackets(bodyBrackets)
                .optionBrackets(optionBrackets)
                .indentBody(indentBody)
                .build();
        List<String> codeLines = new ArrayList<>(command.getBlock());
        codeLines.add(COMMAND_MARKER_CHAR + buildTag(CLOSING_KEYWORD));
        return codeLines;
    }

    private String buildTag(String tagKeyword) {
        return tagKeyword + ENVIRONMENT_NAME_BRACKET.getLeftBracket() + name.getString() + ENVIRONMENT_NAME_BRACKET.getRightBracket();
    }

    private String buildOpeningTag() {
        if (requiredArguments == null) {
            return buildTag(OPENING_KEYWORD);
        } else {
            ExpressionList args = new FormattedExpressionList.FormattedExpressionListBuilder(requiredArguments)
                    .brackets(Bracket.CURLY_BRACES)
                    .terminator(StatementTerminator.COMMA)
                    .build();
            return buildTag(OPENING_KEYWORD) + args.getInline();
        }
    }

    @Override
    public String toString() {
        return "Environment{" +
                "name=" + name +
                ", requiredArguments=" + requiredArguments +
                ", optionalArguments=" + optionalArguments +
                ", body=" + body +
                ", indentBody=" + indentBody +
                ", optionBrackets=" + optionBrackets +
                ", bodyBrackets=" + bodyBrackets +
                '}';
    }


    public static class EnvironmentBuilder {

        // required
        private final EnvironmentName name;

        // optional
        private List<String> requiredArguments;
        private List<String> optionalArguments;
        private List<String> body;
        private boolean indentBody = DEFAULT_INDENT_BODY;
        private Bracket optionBrackets = DEFAULT_OPTIONAL_ARGUMENTS_BRACKET;
        private Bracket bodyBrackets = DEFAULT_BODY_BRACKETS;


        public EnvironmentBuilder(EnvironmentName name) {
            this.name = name;
        }

        public EnvironmentBuilder requiredArguments(String... requiredArguments) {
            return requiredArguments(new ArrayList<>(List.of(requiredArguments)));
        }

        public EnvironmentBuilder requiredArguments(List<String> requiredArguments) {
            this.requiredArguments = requiredArguments;
            return this;
        }

        public EnvironmentBuilder optionalArguments(String... optionalArguments) {
            return optionalArguments(new ArrayList<>(List.of(optionalArguments)));
        }

        public EnvironmentBuilder optionalArguments(List<String> optionalArguments) {
            this.optionalArguments = optionalArguments;
            return this;
        }

        public EnvironmentBuilder body(String... body) {
            return body(new ArrayList<>(List.of(body)));
        }

        public EnvironmentBuilder body(List<String> body) {
            this.body = body;
            return this;
        }

        public EnvironmentBuilder indentBody(boolean indentBody) {
            this.indentBody = indentBody;
            return this;
        }

        public EnvironmentBuilder optionBrackets(Bracket optionBrackets) {
            this.optionBrackets = optionBrackets;
            return this;
        }

        public EnvironmentBuilder bodyBrackets(Bracket bodyBrackets) {
            this.bodyBrackets = bodyBrackets;
            return this;
        }


        public Environment build() {
            return new Environment(this);
        }

    }

}
