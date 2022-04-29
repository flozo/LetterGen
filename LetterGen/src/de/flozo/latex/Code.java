package de.flozo.latex;

import java.util.ArrayList;
import java.util.List;

public class Code {

    private final String[] lines;
    private final StatementTerminator statementTerminator;
    private final boolean dropLastTerminator;
    public static final String INLINE_SEPARATOR = " ";

    private final Brackets surroundWith;
    private final Bracket openingBracket;
    private final Bracket closingBracket;



    public Code(String... lines) {
        this(lines, StatementTerminator.NONE, Brackets.NONE, false);
    }

    public Code(String[] lines, StatementTerminator statementTerminator) {
        this(lines, statementTerminator, Brackets.NONE, false);
    }

    public Code(String[] lines, StatementTerminator statementTerminator, Brackets surroundWith) {
        this(lines, statementTerminator, surroundWith, false);
    }


    public Code(String[] lines, StatementTerminator statementTerminator, Brackets surroundWith, boolean dropLastTerminator) {
        this.lines = lines;
        this.statementTerminator = statementTerminator;
        this.dropLastTerminator = dropLastTerminator;
        this.surroundWith = surroundWith;
//        if (spaceAfterComma) {
//            this.separatorString = ARGUMENT_SEPARATOR + SPACE;
//        } else {
//            this.separatorString = ARGUMENT_SEPARATOR;
//        }
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


    public String[] getCodeLines() {
        // Return lines unchanged if StatementTerminator.NONE
        if (statementTerminator == StatementTerminator.NONE) {
            return lines;
        }
        // Create new String array with statementTerminator appended to each element
        int last = lines.length - 1;
        String[] terminatedLines = new String[last + 1];
        for (int i = 0; i < last; i++) {
            terminatedLines[i] = lines[i] + statementTerminator;
        }
        // Append or drop last terminator
        terminatedLines[last] = dropLastTerminator ? lines[last] : lines[last] + statementTerminator;
        return terminatedLines;
    }

    public String inline() {
        return openingBracket + String.join(INLINE_SEPARATOR, getCodeLines()) + closingBracket;
    }

    public String[] asBlock() {
        List<String> newBlock = new ArrayList<>(List.of(getCodeLines()));
        newBlock.add(0, openingBracket.toString());
        newBlock.add(closingBracket.toString());
        Code newCode = new Code(newBlock.toArray(new String[0]));
        return newCode.getCodeLines();
    }


    // Getters

    public String[] getLines() {
        return lines;
    }

    public StatementTerminator getStatementTerminator() {
        return statementTerminator;
    }

    public boolean isDropLastTerminator() {
        return dropLastTerminator;
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
