package de.flozo.latex.core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArgumentListTest {

    public static final StatementTerminator DEFAULT_TERMINATOR = ArgumentList.DEFAULT_TERMINATOR;
    public static final Bracket DEFAULT_BRACKETS = ArgumentList.DEFAULT_BRACKETS;
    public static final String INLINE_SEPARATOR = ExpressionList.INLINE_SEPARATOR;

    private final String[] arguments = {
            "anchor=north west",
            "minimum width=9.0cm",
            "minimum height=2.73cm",
            "text width=9.0cm",
            "align=left"
    };

    @Test
    void getLines() {
        List<String> expected = new ArrayList<>(List.of(
                DEFAULT_BRACKETS.getLeftBracket(),
                "anchor=north west" + DEFAULT_TERMINATOR.getString(),
                "minimum width=9.0cm" + DEFAULT_TERMINATOR.getString(),
                "minimum height=2.73cm" + DEFAULT_TERMINATOR.getString(),
                "text width=9.0cm" + DEFAULT_TERMINATOR.getString(),
                "align=left",
                DEFAULT_BRACKETS.getRightBracket()
        ));
        ArgumentList argumentList = new ArgumentList(arguments);
        assertEquals(expected, argumentList.getLines());
    }

    @Test
    void getInline() {
        String expected = String.format(DEFAULT_BRACKETS.getLeftBracket() +
                        "anchor=north west,%1$sminimum width=9.0cm,%1$s" +
                        "minimum height=2.73cm,%1$stext width=9.0cm,%1$salign=left" +
                        DEFAULT_BRACKETS.getRightBracket(),
                INLINE_SEPARATOR);
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getInline(DEFAULT_TERMINATOR, DEFAULT_BRACKETS));

    }
}
