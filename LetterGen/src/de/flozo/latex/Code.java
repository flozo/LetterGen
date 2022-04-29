package de.flozo.latex;

public class Code {

    private final String[] lines;
    private final StatementTerminator statementTerminator;
    private final boolean dropLastTerminator;


    public Code(String... lines) {
        this(lines, StatementTerminator.NONE, false);
    }

    public Code(String[] lines, StatementTerminator statementTerminator) {
        this(lines, statementTerminator, false);
    }

    public Code(String[] lines, StatementTerminator statementTerminator, boolean dropLastTerminator) {
        this.lines = lines;
        this.statementTerminator = statementTerminator;
        this.dropLastTerminator = dropLastTerminator;
    }


    public String[] getCodeLines() {
        // Return lines unchanged if StatementTerminator.NONE
        if (statementTerminator == StatementTerminator.NONE) {
            return lines;
        }
        // Create new String array with statementTerminator appended to each element
        String[] terminatedLines = new String[lines.length];
        for (int i = 0; i < lines.length - 1; i++) {
            terminatedLines[i] = lines[i] + statementTerminator;
        }
        // Append or drop last terminator
        terminatedLines[lines.length - 1] = dropLastTerminator ? lines[lines.length - 1] : lines[lines.length - 1] + statementTerminator;
        return terminatedLines;
    }

    public StatementTerminator getStatementTerminator() {
        return statementTerminator;
    }


//    public String inline() {
//        return String.join(OPTIONAL_ARGUMENT_SEPARATOR, getCodeLines());
//    }
}
