package main;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    private static final String OPENING_KEYWORD = "begin";
    private static final String CLOSING_KEYWORD = "end";

    private String name;
    private String argument;
    private List<String> optionalParameters;
    private List<String> codeBlock;

    public Environment(String name, List<String> codeBlock) {
        this(name, codeBlock, null, null);
    }

    public Environment(String name, List<String> codeBlock, String argument, List<String> optionalParameters) {
        this.name = name;
        this.codeBlock = codeBlock;
        this.argument = argument;
        this.optionalParameters = optionalParameters;
    }


    public List<String> assembleEnvironment(){//String environmentName, String environmentArgument, List<String> optionalParameters, List<String> codeBlock) {
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
        for (String line : codeBlock) {
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

    public List<String> getCodeBlock() {
        return codeBlock;
    }
}
