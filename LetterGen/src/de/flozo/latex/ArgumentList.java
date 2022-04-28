package de.flozo.latex;

import java.util.ArrayList;
import java.util.List;

public class ArgumentList {

    public static final String ARGUMENT_SEPARATOR = ",";
    public static final String SPACE = " ";


    private final Code arguments;

    private final boolean spaceAfterComma;
    private final String separatorString;

    private final Brackets surroundWith;
    private final Bracket openingBracket;
    private final Bracket closingBracket;


    public ArgumentList(Code arguments) {
        this(arguments, Brackets.NONE);
    }

    public ArgumentList(Code arguments, Brackets surroundWith) {
        this(arguments, surroundWith, true);
    }

    public ArgumentList(Code arguments, Brackets surroundWith, boolean spaceAfterComma) {
        this.arguments = arguments;
        this.spaceAfterComma = spaceAfterComma;
        this.surroundWith = surroundWith;
        if (spaceAfterComma) {
            this.separatorString = ARGUMENT_SEPARATOR + SPACE;
        } else {
            this.separatorString = ARGUMENT_SEPARATOR;
        }
        if (surroundWith == Brackets.PARENTHESES) {
            this.openingBracket = Bracket.LEFT_PARENTHESIS;
            this.closingBracket = Bracket.RIGHT_PARENTHESIS;
        } else if (surroundWith == Brackets.SQUARE_BRACKETS) {
            this.openingBracket = Bracket.LEFT_SQUARE_BRACKET;
            this.closingBracket = Bracket.RIGHT_SQUARE_BRACKET;
        } else if (surroundWith == Brackets.CURLY_BRACES) {
            this.openingBracket = Bracket.LEFT_CURLY_BRACE;
            this.closingBracket = Bracket.RIGHT_CURLY_BRACE;
        } else {
            this.openingBracket = Bracket.NONE;
            this.closingBracket = Bracket.NONE;
        }
    }

    public String inline() {
        return openingBracket + String.join(separatorString, arguments.getCodeLines()) + closingBracket;
    }


    public String[] asBlock() {
        List<String> newBlock = new ArrayList<>(List.of(arguments.getCodeLines()));
        newBlock.add(0, openingBracket.toString());
        newBlock.add(closingBracket.toString());
        Code newCode = new Code(newBlock.toArray(new String[0]));
        return newCode.getCodeLines();

    }

    public Code getArguments() {
        return arguments;
    }

    public boolean isSpaceAfterComma() {
        return spaceAfterComma;
    }

    public String getSeparatorString() {
        return separatorString;
    }

    public Brackets getSurroundWith() {
        return surroundWith;
    }

    public Bracket getOpeningBracket() {
        return openingBracket;
    }

    public Bracket getClosingBracket() {
        return closingBracket;
    }
}
