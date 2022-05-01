package de.flozo.latex;

import java.util.List;

public class ArgumentList extends ExpressionList {

    public ArgumentList(String... lines) {
        super(lines);
    }

    @Override
    public List<String> getLines() {
        return super.getLines(StatementTerminator.COMMA, false, Bracket.SQUARE_BRACKETS, false);
    }

    @Override
    public String getInline() {
        return super.getInline();
    }

}
