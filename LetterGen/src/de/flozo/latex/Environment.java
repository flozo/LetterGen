package de.flozo.latex;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    private static final String OPENING_KEYWORD = "begin";
    private static final String CLOSING_KEYWORD = "end";

    private final String name;
    private final String argument;
    private final List<String> optionalParameters;
    private final List<String> body;

    public Environment(String name, List<String> body) {
        this(name, body, null, null);
    }

    public Environment(String name, List<String> body, String argument, List<String> optionalParameters) {
        this.name = name;
        this.body = body;
        this.argument = argument;
        this.optionalParameters = optionalParameters;
    }


    public List<String> assembleEnvironment() {
        List<String> codeLines = new ArrayList<>();
        String openingTag = "\\" + OPENING_KEYWORD + "{" + name + "}";
        if (argument != null) {
            openingTag = openingTag + "{" + argument + "}";
        }
        if (optionalParameters != null) {
            openingTag = openingTag + " [";
        }
        codeLines.add(openingTag);
        if (optionalParameters != null) {
            for (String parameter : optionalParameters) {
                codeLines.add("\t" + parameter + ",");
            }
            codeLines.add("\t]");
        }
        for (String line : body) {
            codeLines.add("\t" + line);
        }
        codeLines.add("\\" + CLOSING_KEYWORD + "{" + name + "}");
        return codeLines;
    }


    public String getName() {
        return name;
    }

    public String getArgument() {
        return argument;
    }

    public List<String> getOptionalParameters() {
        return optionalParameters;
    }

    public List<String> getBody() {
        return body;
    }
}
