package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    public static final String OPENING_KEYWORD = "begin";
    public static final String CLOSING_KEYWORD = "end";
    public static final String INDENT_CHARACTER = "\t";
    public static final Bracket DEFAULT_OPTION_BRACKET = ArgumentList.DEFAULT_BRACKETS;

    private final EnvironmentName name;
    //    private final String argument;
    private final ArgumentList optionalParameters;
    private final boolean inlineOptions;
    private final ExpressionList body;
    private final List<String> lines;

    private final String openingTag;
    private final String closingTag;

    public Environment(EnvironmentName name, ArgumentList optionalParameters, boolean inlineOptions, ExpressionList body) {
        this.name = name;
        this.optionalParameters = optionalParameters;
        this.inlineOptions = inlineOptions;
        this.body = body;
        this.lines = new ArrayList<>();
        this.openingTag = "\\" + OPENING_KEYWORD + "{" + name.getString() + "}";
        this.closingTag = "\\" + CLOSING_KEYWORD + "{" + name.getString() + "}";
    }

    public List<String> getLines() {
        if (inlineOptions) {
            lines.add(openingTag + " " + optionalParameters.getInline());
        } else {
            lines.add(openingTag + " " + DEFAULT_OPTION_BRACKET.getLeftBracket());
            lines.addAll(indent(optionalParameters.getLines().subList(1, optionalParameters.len() + 2)));
        }
        lines.addAll(indent(body.getLines()));
        lines.add(closingTag);
        return lines;
    }

    private List<String> indent(List<String> code) {
        List<String> indentedCode = new ArrayList<>(code);
        indentedCode.replaceAll(s -> INDENT_CHARACTER + s);
        return indentedCode;
    }

    public ArgumentList getOptionalParameters() {
        return optionalParameters;
    }

    public ExpressionList getBody() {
        return body;
    }
}
