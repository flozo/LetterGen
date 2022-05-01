package de.flozo.latex;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    private static final String OPENING_KEYWORD = "begin";
    private static final String CLOSING_KEYWORD = "end";
    public static final String INDENT_CHARACTER = "\t";

    private final EnvironmentName name;
    //    private final String argument;
    private final ArgumentList optionalParameters;
    private final boolean inlineOptions;
    private final ExpressionList body;
    private final List<String> lines;

    public Environment(EnvironmentName name, ArgumentList optionalParameters, boolean inlineOptions, ExpressionList body) {
        this.name = name;
        this.optionalParameters = optionalParameters;
        this.inlineOptions = inlineOptions;
        this.body = body;
        this.lines = new ArrayList<>();
    }


    public List<String> getLines() {
        String openingTag = "\\" + OPENING_KEYWORD + "{" + name + "}";
        String closingTag = "\\" + CLOSING_KEYWORD + "{" + name + "}";
        if (inlineOptions) {
            lines.add(openingTag + " " + optionalParameters.getInline());
        } else {
            lines.add(openingTag + " [");
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
