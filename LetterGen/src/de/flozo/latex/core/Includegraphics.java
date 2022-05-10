package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class Includegraphics {

    // constants
    public static final String COMMAND_MARKER_CHAR = "\\";
    public static final CommandName KEYWORD = CommandName.INCLUDEGRAPHICS;
    public static final Bracket BODY_BRACKETS = Bracket.CURLY_BRACES;
    public static final Bracket OPTIONS_BRACKETS = Bracket.SQUARE_BRACKETS;


    // required
    private String fileName;

    // optional
    private final List<String> optionalArguments;
    private final double height;
    private final double width;

    public Includegraphics(IncludegraphicsBuilder builder) {
        this.fileName = builder.fileName;
        this.optionalArguments = builder.optionalArguments;
        this.height = builder.height;
        this.width = builder.width;
    }

    public String getStatement() {
        StringBuilder sb = new StringBuilder(COMMAND_MARKER_CHAR + KEYWORD.getString());
        // Append options if at least one option is present
        if (!optionalArguments.isEmpty()) {
            sb.append(" ").append(inlineOptions());
        }
        // Append required parts
        sb.append(BODY_BRACKETS.getLeftBracket());
        sb.append(fileName);
        sb.append(BODY_BRACKETS.getRightBracket());
        return sb.toString();
    }


    private String inlineOptions() {
        Code options = new Code.CodeBuilder(new ExpressionList(optionalArguments))
                .brackets(OPTIONS_BRACKETS)
                .terminator(StatementTerminator.COMMA)
                .skipLast(true)
                .inlineSpacing(true)
                .build();
        return options.getInline();
    }



    public static class IncludegraphicsBuilder {

        // required
        private final String fileName;

        // optional
        private List<String> optionalArguments = new ArrayList<>();
        private double height;
        private double width;


        public IncludegraphicsBuilder(String fileName) {
            this.fileName = fileName;
        }

        public IncludegraphicsBuilder height(double height) {
            this.height = height;
            this.optionalArguments.add("height=" + height);
            return this;
        }

        public IncludegraphicsBuilder width(double width) {
            this.width = width;
            this.optionalArguments.add("width=" + width);
            return this;
        }


        public Includegraphics build() {
            return new Includegraphics(this);
        }
    }
}
