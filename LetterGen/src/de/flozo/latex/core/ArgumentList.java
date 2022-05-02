package de.flozo.latex.core;

import java.util.List;

public class ArgumentList extends ExpressionList {

    public static final StatementTerminator DEFAULT_TERMINATOR = StatementTerminator.COMMA;
    public static final Bracket DEFAULT_BRACKETS = Bracket.NONE;
    public static final boolean DEFAULT_SKIP_LAST = true;
    public static final boolean DEFAULT_INPLACE = false;


    public ArgumentList(String... lines) {
        super(lines);
    }

    @Override
    public List<String> getLines() {
        return super.getLines(DEFAULT_TERMINATOR, DEFAULT_SKIP_LAST, DEFAULT_BRACKETS, DEFAULT_INPLACE);
    }

    @Override
    public String getInline() {
        return super.getInline();
    }

}
