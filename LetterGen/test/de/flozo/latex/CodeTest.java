package de.flozo.latex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CodeTest {

    private final String[] lines = {
            "anchor=north west",
            "minimum width=9.0cm",
            "minimum height=2.73cm",
            "text width=9.0cm",
            "align=left"
    };


    @Test
    void getCodeLines_noTerminator() {
        String[] expected = {
                "anchor=north west",
                "minimum width=9.0cm",
                "minimum height=2.73cm",
                "text width=9.0cm",
                "align=left"
        };
        Code code = new Code(lines);
        for (String line: code.getCodeLines()) {
            System.out.println(line);
        }
        assertArrayEquals(expected, code.getCodeLines());
    }

    @Test
    void getCodeLines_commaTerminator() {
        String[] expected = {
                "anchor=north west,",
                "minimum width=9.0cm,",
                "minimum height=2.73cm,",
                "text width=9.0cm,",
                "align=left,"
        };
        Code code = new Code(lines,StatementTerminator.COMMA);
        for (String line: code.getCodeLines()) {
            System.out.println(line);
        }
        assertArrayEquals(expected, code.getCodeLines());
    }

    @Test
    void getCodeLines_semicolonTerminator() {
        String[] expected = {
                "anchor=north west;",
                "minimum width=9.0cm;",
                "minimum height=2.73cm;",
                "text width=9.0cm;",
                "align=left;"
        };
        Code code = new Code(lines,StatementTerminator.SEMICOLON);
        for (String line: code.getCodeLines()) {
            System.out.println(line);
        }
        assertArrayEquals(expected, code.getCodeLines());
    }

    @Test
    void getCodeLines_semicolonTerminatorDropLast() {
        String[] expected = {
                "anchor=north west;",
                "minimum width=9.0cm;",
                "minimum height=2.73cm;",
                "text width=9.0cm;",
                "align=left"
        };
        Code code = new Code(lines,StatementTerminator.SEMICOLON, true);
        for (String line: code.getCodeLines()) {
            System.out.println(line);
        }
        assertArrayEquals(expected, code.getCodeLines());
    }

    @Test
    void getCodeLines_doubleBackslashTerminatorDropLast() {
        String[] expected = {
                "anchor=north west\\\\",
                "minimum width=9.0cm\\\\",
                "minimum height=2.73cm\\\\",
                "text width=9.0cm\\\\",
                "align=left"
        };
        Code code = new Code(lines,StatementTerminator.DOUBLE_BACKSLASH, true);
        for (String line: code.getCodeLines()) {
            System.out.println(line);
        }
        assertArrayEquals(expected, code.getCodeLines());
    }

}
