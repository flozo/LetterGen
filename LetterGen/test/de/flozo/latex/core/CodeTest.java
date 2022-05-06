package de.flozo.latex.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CodeTest {

    ExpressionList expressionList = new ExpressionList(
            "inner xsep=0pt",
            "inner ysep=0pt",
            "trim left=0pt",
            "trim right={20 cm}"
    );
    Code.CodeBuilder code = new Code.CodeBuilder(expressionList);

    @Test
    void getExpressionList() {
        Code formattedCode = code
                .terminator(StatementTerminator.COMMA)
                .brackets(Bracket.SQUARE_BRACKETS)
                .inlineSpacing(true)
                .skipLast(true)
                .build();
        assertEquals(expressionList, formattedCode.getExpressionList());
    }

    @ParameterizedTest
    @EnumSource(Bracket.class)
    void getBrackets(Bracket bracket) {
        Code formattedCode = code
                .brackets(bracket)
                .build();
        assertEquals(bracket, formattedCode.getBrackets());
    }

    @ParameterizedTest
    @EnumSource(StatementTerminator.class)
    void getBlock_terminator_skipLastTrue(StatementTerminator terminator) {
        ExpressionList expected = new ExpressionList(
                "inner xsep=0pt" + terminator.getString(),
                "inner ysep=0pt" + terminator.getString(),
                "trim left=0pt" + terminator.getString(),
                "trim right={20 cm}"
        );
        Code formattedCode = code
                .terminator(terminator)
                .brackets(Bracket.NONE)
                .build();
        assertEquals(expected.getLines(), formattedCode.getBlock());
    }

    @ParameterizedTest
    @EnumSource(StatementTerminator.class)
    void getBlock_terminator_skipLastFalse(StatementTerminator terminator) {
        ExpressionList expected = new ExpressionList(
                "inner xsep=0pt" + terminator.getString(),
                "inner ysep=0pt" + terminator.getString(),
                "trim left=0pt" + terminator.getString(),
                "trim right={20 cm}" + terminator.getString()
        );
        Code formattedCode = code
                .terminator(terminator)
                .brackets(Bracket.NONE)
                .skipLast(false)
                .build();
        assertEquals(expected.getLines(), formattedCode.getBlock());
    }

    @ParameterizedTest
    @EnumSource(
            value = Bracket.class,
            names = "NONE",
            mode = EnumSource.Mode.EXCLUDE)
    void getBlock_bracket_notNone(Bracket bracket) {
        ExpressionList expected = new ExpressionList(
                bracket.getLeftBracket(),
                "inner xsep=0pt",
                "inner ysep=0pt",
                "trim left=0pt",
                "trim right={20 cm}",
                bracket.getRightBracket()
        );
        Code formattedCode = code
                .terminator(StatementTerminator.NONE)
                .brackets(bracket)
                .build();
        assertEquals(expected.getLines(), formattedCode.getBlock());
    }

    @Test
    void getBlock_bracket_none() {
        ExpressionList expected = new ExpressionList(
                "inner xsep=0pt",
                "inner ysep=0pt",
                "trim left=0pt",
                "trim right={20 cm}"
        );
        Code formattedCode = code
                .terminator(StatementTerminator.NONE)
                .brackets(Bracket.NONE)
                .build();
        assertEquals(expected.getLines(), formattedCode.getBlock());
    }


    @Test
    void getBlock_prepend_mergeBracketLinesFalse() {
        ExpressionList expected = new ExpressionList(
                "[",
                "inner xsep=0pt,",
                "inner ysep=0pt,",
                "trim left=0pt,",
                "trim right={20 cm}",
                "]",
                "{",
                "inner xsep=0pt,",
                "inner ysep=0pt,",
                "trim left=0pt,",
                "trim right={20 cm}",
                "}"
        );
        Code formattedCodeToPrepend = code
                .terminator(StatementTerminator.COMMA)
                .brackets(Bracket.SQUARE_BRACKETS)
                .build();
        Code formattedCode = code
                .terminator(StatementTerminator.COMMA)
                .brackets(Bracket.CURLY_BRACES)
                .prepend(formattedCodeToPrepend)
                .mergeBracketLines(false)
                .build();
        assertEquals(expected.getLines(), formattedCode.getBlock());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void getBlock_prepend_mergeBracketLinesTrue(boolean spacing) {
        ExpressionList expected = new ExpressionList(
                "[",
                "inner xsep=0pt,",
                "inner ysep=0pt,",
                "trim left=0pt,",
                "trim right={20 cm}",
                spacing ? "] {" : "]{",
                "inner xsep=0pt,",
                "inner ysep=0pt,",
                "trim left=0pt,",
                "trim right={20 cm}",
                "}"
        );
        Code formattedCodeToPrepend = code
                .terminator(StatementTerminator.COMMA)
                .brackets(Bracket.SQUARE_BRACKETS)
                .build();
        Code formattedCode = code
                .terminator(StatementTerminator.COMMA)
                .brackets(Bracket.CURLY_BRACES)
                .prepend(formattedCodeToPrepend)
                .mergeBracketLines(true)
                .interBracketSpacing(spacing)
                .build();
        assertEquals(expected.getLines(), formattedCode.getBlock());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void getBlock_append_mergeBracketLinesFalse(boolean spacing) {
        ExpressionList expected = new ExpressionList(
                "{",
                "inner xsep=0pt,",
                "inner ysep=0pt,",
                "trim left=0pt,",
                "trim right={20 cm}",
                spacing ? "} [" : "}[",
                "inner xsep=0pt,",
                "inner ysep=0pt,",
                "trim left=0pt,",
                "trim right={20 cm}",
                "]"
        );
        Code formattedCodeToAppend = code
                .terminator(StatementTerminator.COMMA)
                .brackets(Bracket.SQUARE_BRACKETS)
                .build();
        Code formattedCode = code
                .terminator(StatementTerminator.COMMA)
                .brackets(Bracket.CURLY_BRACES)
                .append(formattedCodeToAppend)
                .mergeBracketLines(true)
                .interBracketSpacing(spacing)
                .build();
        assertEquals(expected.getLines(), formattedCode.getBlock());
    }

    @Test
    void getBlock_append_mergeBracketLinesTrue() {
        ExpressionList expected = new ExpressionList(
                "{",
                "inner xsep=0pt,",
                "inner ysep=0pt,",
                "trim left=0pt,",
                "trim right={20 cm}",
                "}",
                "[",
                "inner xsep=0pt,",
                "inner ysep=0pt,",
                "trim left=0pt,",
                "trim right={20 cm}",
                "]"
        );
        Code formattedCodeToAppend = code
                .terminator(StatementTerminator.COMMA)
                .brackets(Bracket.SQUARE_BRACKETS)
                .build();
        Code formattedCode = code
                .terminator(StatementTerminator.COMMA)
                .brackets(Bracket.CURLY_BRACES)
                .append(formattedCodeToAppend)
                .mergeBracketLines(false)
                .build();
        assertEquals(expected.getLines(), formattedCode.getBlock());
    }



    @ParameterizedTest
    @EnumSource(StatementTerminator.class)
    void getInline_terminator_noInlineSpacing_skipLast(StatementTerminator terminator) {
        String expected = String.format("inner xsep=0pt%1$s" +
                "inner ysep=0pt%1$s" +
                "trim left=0pt%1$s" +
                "trim right={20 cm}", terminator.getString());
        Code formattedCode = code
                .terminator(terminator)
                .brackets(Bracket.NONE)
                .inlineSpacing(false)
                .build();
        assertEquals(expected, formattedCode.getInline());
    }

    @ParameterizedTest
    @EnumSource(StatementTerminator.class)
    void getInline_terminator_inlineSpacing_skipLast(StatementTerminator terminator) {
        String expected = String.format("inner xsep=0pt%1$s " +
                "inner ysep=0pt%1$s " +
                "trim left=0pt%1$s " +
                "trim right={20 cm}", terminator.getString());
        Code formattedCode = code
                .terminator(terminator)
                .brackets(Bracket.NONE)
                .inlineSpacing(true)
                .build();
        assertEquals(expected, formattedCode.getInline());
    }

    @ParameterizedTest
    @EnumSource(StatementTerminator.class)
    void getInline_terminator_inlineSpacing_skipLastFalse(StatementTerminator terminator) {
        String expected = String.format("inner xsep=0pt%1$s " +
                "inner ysep=0pt%1$s " +
                "trim left=0pt%1$s " +
                "trim right={20 cm}%1$s", terminator.getString());
        Code formattedCode = code
                .terminator(terminator)
                .brackets(Bracket.NONE)
                .skipLast(false)
                .inlineSpacing(true)
                .build();
        assertEquals(expected, formattedCode.getInline());
    }

    @ParameterizedTest
    @EnumSource(Bracket.class)
    void getInline_bracket_inlineSpacing_skipLast(Bracket bracket) {
        String expected = bracket.getLeftBracket() +
                "inner xsep=0pt, " +
                "inner ysep=0pt, " +
                "trim left=0pt, " +
                "trim right={20 cm}" +
                bracket.getRightBracket();
        Code formattedCode = code
                .terminator(StatementTerminator.COMMA)
                .brackets(bracket)
                .inlineSpacing(true)
                .skipLast(true)
                .build();
        assertEquals(expected, formattedCode.getInline());
    }

}
