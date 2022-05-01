package de.flozo.latex;

class CodeTest {

    private final String[] lines = {
            "anchor=north west",
            "minimum width=9.0cm",
            "minimum height=2.73cm",
            "text width=9.0cm",
            "align=left"
    };


//    @Test
//    void getCodeLines_noTerminator() {
//        String[] expected = {
//                "anchor=north west",
//                "minimum width=9.0cm",
//                "minimum height=2.73cm",
//                "text width=9.0cm",
//                "align=left"
//        };
//        Code code = new Code(lines);
//        for (String line : code.getLines()) {
//            System.out.println(line);
//        }
//        assertArrayEquals(expected, code.getLines());
//    }
//
//    @Test
//    void getCodeLines_commaTerminator() {
//        String[] expected = {
//                "anchor=north west,",
//                "minimum width=9.0cm,",
//                "minimum height=2.73cm,",
//                "text width=9.0cm,",
//                "align=left,"
//        };
//        Code code = new Code(lines, StatementTerminator.COMMA);
//        for (String line : code.getLines()) {
//            System.out.println(line);
//        }
//        assertArrayEquals(expected, code.getLines());
//    }
//
//    @Test
//    void getCodeLines_semicolonTerminator() {
//        String[] expected = {
//                "anchor=north west;",
//                "minimum width=9.0cm;",
//                "minimum height=2.73cm;",
//                "text width=9.0cm;",
//                "align=left;"
//        };
//        Code code = new Code(lines, StatementTerminator.SEMICOLON);
//        for (String line : code.getLines()) {
//            System.out.println(line);
//        }
//        assertArrayEquals(expected, code.getLines());
//    }
//
//    @Test
//    void getCodeLines_semicolonTerminatorDropLast() {
//        String[] expected = {
//                "anchor=north west;",
//                "minimum width=9.0cm;",
//                "minimum height=2.73cm;",
//                "text width=9.0cm;",
//                "align=left"
//        };
//        Code code = new Code(lines, StatementTerminator.SEMICOLON, Brackets.NONE, true);
//        for (String line : code.getLines()) {
//            System.out.println(line);
//        }
//        assertArrayEquals(expected, code.getLines());
//    }
//
//    @Test
//    void getCodeLines_doubleBackslashTerminatorDropLast() {
//        String[] expected = {
//                "anchor=north west\\\\",
//                "minimum width=9.0cm\\\\",
//                "minimum height=2.73cm\\\\",
//                "text width=9.0cm\\\\",
//                "align=left"
//        };
//        Code code = new Code(lines, StatementTerminator.DOUBLE_BACKSLASH, Brackets.NONE, true);
//        for (String line : code.getLines()) {
//            System.out.println(line);
//        }
//        assertArrayEquals(expected, code.getLines());
//    }
//
//    @Test
//    void inline() {
//        String expected = "[anchor=north west, minimum width=9.0cm, " +
//                "minimum height=2.73cm, text width=9.0cm, align=left]";
//        Code code = new Code(lines, StatementTerminator.COMMA, Brackets.SQUARE_BRACKETS, true);
//        System.out.println(code.inline());
//        assertEquals(expected, code.inline());
//
//    }
//
//    @Test
//    void asBlock() {
//        String[] expected = {
//                "[",
//                "anchor=north west,",
//                "minimum width=9.0cm,",
//                "minimum height=2.73cm,",
//                "text width=9.0cm,",
//                "align=left,",
//                "]"
//        };
//        Code code = new Code(lines, StatementTerminator.COMMA, Brackets.SQUARE_BRACKETS, false);
//        for (String line : code.asBlock()) {
//            System.out.println(line);
//        }
//        assertArrayEquals(expected, code.asBlock());
//    }
}