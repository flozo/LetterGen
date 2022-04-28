package de.flozo.latex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArgumentListTest {

    private String[] arguments = {
            "anchor=north west",
            "minimum width=9.0cm",
            "minimum height=2.73cm",
            "text width=9.0cm",
            "align=left"
    };


    @Test
    void testInline_noBrackets() {
        String expected = "anchor=north west, minimum width=9.0cm, minimum height=2.73cm, text width=9.0cm, align=left";
        Code lines = new Code(arguments);
        ArgumentList argumentList = new ArgumentList(lines, Brackets.NONE);
        System.out.println(expected);
        System.out.println(argumentList.inline());
        assertEquals(expected, argumentList.inline());
    }

    @Test
    void testInline_parenthesesBrackets() {
        String expected = "(anchor=north west, minimum width=9.0cm, minimum height=2.73cm, text width=9.0cm, align=left)";
        Code lines = new Code(arguments);
        ArgumentList argumentList = new ArgumentList(lines, Brackets.PARENTHESES);
        System.out.println(expected);
        System.out.println(argumentList.inline());
        assertEquals(expected, argumentList.inline());
    }


    @Test
    void testInline_squareBrackets() {
        String expected = "[anchor=north west, minimum width=9.0cm, minimum height=2.73cm, text width=9.0cm, align=left]";

        Code lines = new Code(arguments);
        ArgumentList argumentList = new ArgumentList(lines, Brackets.SQUARE_BRACKETS);
        System.out.println(expected);
        System.out.println(argumentList.inline());
        assertEquals(expected, argumentList.inline());
    }

    @Test
    void testInline_curlyBraces() {
        String expected = "{anchor=north west, minimum width=9.0cm, minimum height=2.73cm, text width=9.0cm, align=left}";
        Code lines = new Code(arguments);
        ArgumentList argumentList = new ArgumentList(lines, Brackets.CURLY_BRACES);
        System.out.println(expected);
        System.out.println(argumentList.inline());
        assertEquals(expected, argumentList.inline());
    }


}
