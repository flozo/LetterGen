package de.flozo.latex;

public class ArgumentList {

    public static final String ARGUMENT_SEPARATOR = ",";
    public static final String SPACE = " ";

    private final Code arguments;
    private final boolean spaceAfterComma;
    private final String separatorString;


    public ArgumentList(Code arguments) {
        this(arguments, true);
    }

    public ArgumentList(Code arguments, boolean spaceAfterComma) {
        this.arguments = arguments;
        this.spaceAfterComma = spaceAfterComma;
        if (spaceAfterComma) {
            this.separatorString = ARGUMENT_SEPARATOR + SPACE;
        } else {
            this.separatorString = ARGUMENT_SEPARATOR;
        }
    }

    public String inline() {
        return String.join(separatorString, arguments.getCodeLines());
    }

//    public String inline() {
//        // Build string with pattern: [comma, separated, arguments, list]
//        return OPENING_MARKER + arguments.inline() + CLOSING_MARKER;
//    }

    public Code getArguments() {
        return arguments;
    }
}
