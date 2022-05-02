package de.flozo.latex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionListTest {

    public static final String INLINE_SEPARATOR = ExpressionList.INLINE_SEPARATOR;
    public static final StatementTerminator DEFAULT_INLINE_TERMINATOR = ExpressionList.DEFAULT_INLINE_TERMINATOR;
    public static final Bracket DEFAULT_INLINE_BRACKET = ExpressionList.DEFAULT_INLINE_BRACKET;
    public static final StatementTerminator TEST_TERMINATOR = StatementTerminator.COMMA;
    public static final Bracket TEST_INLINE_BRACKET = Bracket.SQUARE_BRACKETS;


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
                "anchor=north west" + TEST_TERMINATOR.getString(),
                "minimum width=9.0cm" + TEST_TERMINATOR.getString(),
                "minimum height=2.73cm" + TEST_TERMINATOR.getString(),
                "text width=9.0cm" + TEST_TERMINATOR.getString(),
                "align=left",
                bracket.getRightBracket()
        ));
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getLines(TEST_TERMINATOR, true, bracket));
    }


    @Test
    void getLines_brackets_none() {
        List<String> expected = new ArrayList<>(List.of(
                "anchor=north west" + TEST_TERMINATOR.getString(),
                "minimum width=9.0cm" + TEST_TERMINATOR.getString(),
                "minimum height=2.73cm" + TEST_TERMINATOR.getString(),
                "text width=9.0cm" + TEST_TERMINATOR.getString(),
                "align=left"
        ));
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getLines(TEST_TERMINATOR, true, Bracket.NONE));
    }

    @Test
    void getInline_noParameters() {
        String expected = String.format(DEFAULT_INLINE_BRACKET.getLeftBracket() +
                "anchor=north west%1$s%2$s" +
                "minimum width=9.0cm%1$s%2$s" +
                "minimum height=2.73cm%1$s%2$s" +
                "text width=9.0cm%1$s%2$s" +
                "align=left" +
                DEFAULT_INLINE_BRACKET.getRightBracket(), DEFAULT_INLINE_TERMINATOR.getString(), INLINE_SEPARATOR);
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getInline());
    }

    @ParameterizedTest
    @EnumSource(StatementTerminator.class)
    void getInline_terminators(StatementTerminator terminator) {
        String expected = String.format(TEST_INLINE_BRACKET.getLeftBracket() +
                        "anchor=north west%1$s%2$s" +
                        "minimum width=9.0cm%1$s%2$s" +
                        "minimum height=2.73cm%1$s%2$s" +
                        "text width=9.0cm%1$s%2$s" +
                        "align=left" +
                        TEST_INLINE_BRACKET.getRightBracket(),
                terminator.getString(), INLINE_SEPARATOR);
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getInline(terminator, TEST_INLINE_BRACKET));
    }

    @ParameterizedTest
    @EnumSource(Bracket.class)
    void getInline_brackets(Bracket bracket) {
        String expected = String.format(bracket.getLeftBracket() +
                        "anchor=north west%1$s%2$s" +
                        "minimum width=9.0cm%1$s%2$s" +
                        "minimum height=2.73cm%1$s%2$s" +
                        "text width=9.0cm%1$s%2$s" +
                        "align=left" +
                        bracket.getRightBracket(),
                TEST_TERMINATOR.getString(), INLINE_SEPARATOR);
        ExpressionList expressionList = new ExpressionList(arguments);
        assertEquals(expected, expressionList.getInline(TEST_TERMINATOR, bracket));
    }

}
