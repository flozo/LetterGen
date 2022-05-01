package de.flozo.latex;

public class ArgumentList extends Code {

    public static final StatementTerminator ARGUMENT_SEPARATOR = StatementTerminator.COMMA;
    public static final Brackets BRACKETS = Brackets.SQUARE_BRACKETS;

    public ArgumentList(StatementList arguments) {
        super(arguments, ARGUMENT_SEPARATOR, BRACKETS, true);
    }

}
