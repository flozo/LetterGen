package de.flozo.latex;

//public class Code2 extends StatementList {

//    public static final String INLINE_SEPARATOR = " ";
//
//    private final StatementTerminator statementTerminator;
//    private final boolean dropLastTerminator;
//
//    private final
//    private final Brackets surroundWith;
//    private final String openingBracket;
//    private final String closingBracket;
//
//    public Code2(StatementTerminator statementTerminator, boolean dropLastTerminator, Brackets surroundWith, String openingBracket, String closingBracket, String... lines) {
//        super(lines);
//        this.statementTerminator = statementTerminator;
//        this.dropLastTerminator = dropLastTerminator;
//        this.surroundWith = surroundWith;
//        if (surroundWith == Brackets.PARENTHESES) {
//            this.openingBracket = Bracket.LEFT_PARENTHESIS.getString();
//            this.closingBracket = Bracket.RIGHT_PARENTHESIS.getString();
//        } else if (surroundWith == Brackets.SQUARE_BRACKETS) {
//            this.openingBracket = Bracket.LEFT_SQUARE_BRACKET.getString();
//            this.closingBracket = Bracket.RIGHT_SQUARE_BRACKET.getString();
//        } else if (surroundWith == Brackets.CURLY_BRACES) {
//            this.openingBracket = Bracket.LEFT_CURLY_BRACE.getString();
//            this.closingBracket = Bracket.RIGHT_CURLY_BRACE.getString();
//        } else {
//            this.openingBracket = Bracket.NONE.getString();
//            this.closingBracket = Bracket.NONE.getString();
//        }
//    }
//
//    public List<String> appendStatementTerminator() {
//        // Return lines unchanged if StatementTerminator.NONE
//        if (statementTerminator == StatementTerminator.NONE) {
//            return lines.getLines();
//        }
//        lines.getLines().replaceAll(s -> s + statementTerminator.getString());
//        if (dropLastTerminator) {
//            String last = lines.getLines().get(lines.getLines().size() - 1);
//            lines.getLines().set(lines.getLines().size() - 1, last.replace(statementTerminator.getString(), ""));
//        }
//        return lines.getLines();
//    }


//}
