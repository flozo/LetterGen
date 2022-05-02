package de.flozo.latex.core;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnvironmentTest {

    public static final String INLINE_SEPARATOR = ExpressionList.INLINE_SEPARATOR;

    public static final String OPENING_KEYWORD = Environment.OPENING_KEYWORD;
    public static final String CLOSING_KEYWORD = Environment.CLOSING_KEYWORD;

    public static final String INDENT_CHARACTER = Environment.INDENT_CHARACTER;
    public static final StatementTerminator TERMINATOR = StatementTerminator.COMMA;

    private final String[] arguments = {
            "anchor=north west",
            "minimum width=9.0cm",
            "minimum height=2.73cm",
            "text width=9.0cm",
            "align=left"
    };

    private final String[] body = {
            "% Test line",
            "% Another test line",
            "% And another one"
    };


    @ParameterizedTest
    @EnumSource(EnvironmentName.class)
    void getLines_blockOptions(EnvironmentName environmentName) {
        List<String> expected = new ArrayList<>(List.of(
                "\\" + OPENING_KEYWORD + "{" + environmentName.getString() + "} [",
                INDENT_CHARACTER + "anchor=north west" + TERMINATOR.getString(),
                INDENT_CHARACTER + "minimum width=9.0cm" + TERMINATOR.getString(),
                INDENT_CHARACTER + "minimum height=2.73cm" + TERMINATOR.getString(),
                INDENT_CHARACTER + "text width=9.0cm" + TERMINATOR.getString(),
                INDENT_CHARACTER + "align=left",
                INDENT_CHARACTER + "]",
                INDENT_CHARACTER + "% Test line",
                INDENT_CHARACTER + "% Another test line",
                INDENT_CHARACTER + "% And another one",
                "\\" + CLOSING_KEYWORD + "{" + environmentName.getString() + "}"
        ));
        ArgumentList argumentList = new ArgumentList(arguments);
        ExpressionList expressionList = new ExpressionList(body);
        Environment environment = new Environment(environmentName, argumentList, false, expressionList);
        assertEquals(expected, environment.getLines());
    }

    @ParameterizedTest
    @EnumSource(EnvironmentName.class)
    void getLines_inlineOptions(EnvironmentName environmentName) {
        String expectedOptions = String.format(
                "\\" + OPENING_KEYWORD + "{" + environmentName.getString() + "} [" +
                "anchor=north west" + "%1$s%2$s" +
                "minimum width=9.0cm" + "%1$s%2$s" +
                "minimum height=2.73cm" + "%1$s%2$s" +
                "text width=9.0cm" + "%1$s%2$s" +
                "align=left]", TERMINATOR.getString(), INLINE_SEPARATOR);
        List<String> expected = new ArrayList<>(List.of(
                expectedOptions,
                INDENT_CHARACTER + "% Test line",
                INDENT_CHARACTER + "% Another test line",
                INDENT_CHARACTER + "% And another one",
                "\\" + CLOSING_KEYWORD + "{" + environmentName.getString() + "}"
        ));
        ArgumentList argumentList = new ArgumentList(arguments);
        ExpressionList expressionList = new ExpressionList(body);
        Environment environment = new Environment(environmentName, argumentList, true, expressionList);
        assertEquals(expected, environment.getLines());
    }

}
