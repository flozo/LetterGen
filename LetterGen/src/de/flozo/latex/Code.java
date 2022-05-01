package de.flozo.latex;

public class Code {

//    public static final String INLINE_SEPARATOR = " ";
//
//    private final StatementList lines;
//    private final StatementTerminator statementTerminator;
//    private final boolean dropLastTerminator;
//
//    private final Brackets surroundWith;
//    private final String openingBracket;
//    private final String closingBracket;
//
//
//    public Code(StatementList lines) {
//        this(lines, StatementTerminator.NONE, Brackets.NONE, false);
//    }
//
//    public Code(StatementList lines, StatementTerminator statementTerminator) {
//        this(lines, statementTerminator, Brackets.NONE, false);
//    }
//
//    public Code(StatementList lines, StatementTerminator statementTerminator, Brackets surroundWith) {
//        this(lines, statementTerminator, surroundWith, false);
//    }
//
//
//    public Code(StatementList lines, StatementTerminator statementTerminator, Brackets surroundWith, boolean dropLastTerminator) {
//        this.lines = lines;
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
//
////    public List<String> appendStatementTerminator() {
//        // Return lines unchanged if StatementTerminator.NONE
////        if (statementTerminator == StatementTerminator.NONE) {
////            return lines.getLines();
////        }
////        lines.getLines().replaceAll(s -> s + statementTerminator.getString());
////        if (dropLastTerminator) {
////            String last = lines.getLines().get(lines.getLines().size() - 1);
////            lines.getLines().set(lines.getLines().size() - 1, last.replace(statementTerminator.getString(), ""));
////        }
////        return lines.getLines();
//
//
////        // Create new String array with statementTerminator appended to each element
////        int last = lines.getLines().size() - 1;
////        String[] terminatedStatement = new String[last + 1];
////        for (int i = 0; i < last; i++) {
////            terminatedStatement[i] = lines.getLines().get(i) + statementTerminator.getString();
////        }
//        // Append or drop last terminator
////        terminatedStatement[last] = dropLastTerminator ? lines.getLines().get(last) : lines.getLines().get(last) + statementTerminator.getString();
////        return terminatedStatement;
//
////    }
//
////    public String inline() {
////        return openingBracket + String.join(INLINE_SEPARATOR, appendStatementTerminator()) + closingBracket;
////    }
//
//    //    public List<String> asBlock() {
////    public StatementList asBlock() {
////        List<String> newBlock = new ArrayList<>();
////        newBlock.add(openingBracket);
////        newBlock.addAll(appendStatementTerminator());
////        newBlock.add(closingBracket);
////        return new StatementList(String.valueOf(newBlock));
//
////        List<String> newBlock = new ArrayList<>(List.of(getLines()));
////        newBlock.add(0, openingBracket);
////        newBlock.add(closingBracket);
////        Code newCode = new Code(newBlock.toArray(new String[0]));
////        return newCode.getLines();
////    }
//
////    public List<String> append(Code newCode) {
////        List<String> currentCode = appendStatementTerminator();
////        currentCode.addAll(newCode);
////        return currentCode.toArray(new String[0]);
////    }
//
//    // Getters
//
//
//    public StatementTerminator getStatementTerminator() {
//        return statementTerminator;
//    }
//
//    public boolean isDropLastTerminator() {
//        return dropLastTerminator;
//    }
//
//    public Brackets getSurroundWith() {
//        return surroundWith;
//    }
//
//    public String getOpeningBracket() {
//        return openingBracket;
//    }
//
//    public String getClosingBracket() {
//        return closingBracket;
//    }
}
