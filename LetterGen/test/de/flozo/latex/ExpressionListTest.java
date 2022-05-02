package de.flozo.latex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionListTest {

    private final String[] arguments = {
            "anchor=north west",
            "minimum width=9.0cm",
            "minimum height=2.73cm",
            "text width=9.0cm",
            "align=left"
    };

    private final String[] comments = {
            "% Test line",
            "% Another test line",
            "% And another one"
    };


    @Test
    void append() {
        List<String> expected = new ArrayList<>(List.of(
                "anchor=north west",
                "minimum width=9.0cm",
                "minimum height=2.73cm",
                "text width=9.0cm",
                "align=left",
                "% Test line",
                "% Another test line",
                "% And another one"
        ));
        ExpressionList expressionList = new ExpressionList(arguments);
        ExpressionList codeBlock = new ExpressionList(comments);
        expressionList.append(codeBlock);
        assertEquals(expected, expressionList.getLines());
    }

    @Test
    void len() {
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(5, expressionList.len());
    }

    @Test
    void getLines_noParameters() {
        List<String> expected = new ArrayList<>(List.of(
                "anchor=north west",
                "minimum width=9.0cm",
                "minimum height=2.73cm",
                "text width=9.0cm",
                "align=left"
        ));
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getLines());
    }

    @ParameterizedTest
    @EnumSource(StatementTerminator.class)
    void getLines_terminators(StatementTerminator terminator) {
        List<String> expected = new ArrayList<>(List.of(
                "anchor=north west" + terminator.getString(),
                "minimum width=9.0cm" + terminator.getString(),
                "minimum height=2.73cm" + terminator.getString(),
                "text width=9.0cm" + terminator.getString(),
                "align=left" + terminator.getString()
        ));
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getLines(terminator));
    }

    @ParameterizedTest
    @EnumSource(StatementTerminator.class)
    void getLines_skipLast(StatementTerminator terminator) {
        List<String> expected = new ArrayList<>(List.of(
                "anchor=north west" + terminator.getString(),
                "minimum width=9.0cm" + terminator.getString(),
                "minimum height=2.73cm" + terminator.getString(),
                "text width=9.0cm" + terminator.getString(),
                "align=left"
        ));
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getLines(terminator, true));
    }

    @ParameterizedTest
    @EnumSource(value = Bracket.class,
            names = {"NONE"},
            mode = EnumSource.Mode.EXCLUDE)
    void getLines_brackets_notNone(Bracket bracket) {
        List<String> expected = new ArrayList<>(List.of(
                bracket.getLeftBracket(),
                "anchor=north west,",
                "minimum width=9.0cm,",
                "minimum height=2.73cm,",
                "text width=9.0cm,",
                "align=left",
                bracket.getRightBracket()
        ));
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getLines(StatementTerminator.COMMA, true, bracket));
    }


    @Test
    void getLines_brackets_none() {
        List<String> expected = new ArrayList<>(List.of(
                "anchor=north west,",
                "minimum width=9.0cm,",
                "minimum height=2.73cm,",
                "text width=9.0cm,",
                "align=left"
        ));
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getLines(StatementTerminator.COMMA, true, Bracket.NONE));
    }

    @Test
    void getInline_noParameters() {
        String expected = String.format(Bracket.SQUARE_BRACKETS.getLeftBracket() +
                "anchor=north west,%1$sminimum width=9.0cm,%1$s" +
                "minimum height=2.73cm,%1$stext width=9.0cm,%1$salign=left" +
                Bracket.SQUARE_BRACKETS.getRightBracket(), ExpressionList.INLINE_SEPARATOR);
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getInline());
    }

    @ParameterizedTest
    @EnumSource(StatementTerminator.class)
    void getInline_terminators(StatementTerminator terminator) {
        String expected = String.format("anchor=north west%1$s%2$s" +
                        "minimum width=9.0cm%1$s%2$s" +
                        "minimum height=2.73cm%1$s%2$s" +
                        "text width=9.0cm%1$s%2$s" +
                        "align=left",
                terminator.getString(), ExpressionList.INLINE_SEPARATOR);
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getInline(terminator, Bracket.NONE));
    }

    @ParameterizedTest
    @EnumSource(Bracket.class)
    void getInline_brackets(Bracket bracket) {
        String expected = String.format(bracket.getLeftBracket() +
                        "anchor=north west,%1$sminimum width=9.0cm,%1$s" +
                        "minimum height=2.73cm,%1$stext width=9.0cm,%1$salign=left" +
                        bracket.getRightBracket(),
                ExpressionList.INLINE_SEPARATOR);
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getInline(StatementTerminator.COMMA, bracket));
    }

}
