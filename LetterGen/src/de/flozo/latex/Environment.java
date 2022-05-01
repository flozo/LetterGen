package de.flozo.latex;

public class Environment {

    private static final String OPENING_KEYWORD = "begin";
    private static final String CLOSING_KEYWORD = "end";

    private final String name;
    private final String argument;
    private final ArgumentList optionalParameters;
    private final StatementList body;
    private final boolean inlineOptions;

    public Environment(String name, StatementList body) {
        this(name, body, null, null, false);
    }

    public Environment(String name, StatementList body, String argument, ArgumentList optionalParameters, Boolean inlineOptions) {
        this.name = name;
        this.body = body;
        this.argument = argument;
        this.optionalParameters = optionalParameters;
        this.inlineOptions = inlineOptions;
    }


//    public ExpressionList assembleEnvironment() {
//        List<String> codeLines = new ArrayList<>();
//        String openingTag;
//        if (optionalParameters != null) {
//            if (inlineOptions) {
//                openingTag = "\\" + OPENING_KEYWORD + "{" + name + "} " +
//                        optionalParameters.inline();
//            } else {
//                openingTag = "\\" + OPENING_KEYWORD + "{" + name + "} " +
//                        optionalParameters.getOpeningBracket();
//            }
//            codeLines.add(openingTag);
//            if (!inlineOptions) {
//                for (int i = 1; i < optionalParameters.asBlock().length(); i++) {
//                    codeLines.add("\t" + optionalParameters.asBlock().getLine(i));
//                }
////                for (String parameter : optionalParameters.asBlock()) {
////                    codeLines.add("\t" + parameter);
////                }
//            }
//        } else {
//            openingTag = "\\" + OPENING_KEYWORD + "{" + name + "}";
//            codeLines.add(openingTag);
//        }
//        for (String line : body) {
//            codeLines.add("\t" + line);
//        }
//        codeLines.add("\\" + CLOSING_KEYWORD + "{" + name + "}");
//        return new ExpressionList(String.valueOf(codeLines));
//    }

}
