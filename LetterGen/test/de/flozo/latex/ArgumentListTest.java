package de.flozo.latex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArgumentListTest {

    private final String[] arguments = {
            "anchor=north west",
            "minimum width=9.0cm",
            "minimum height=2.73cm",
            "text width=9.0cm",
            "align=left"
    };


    @Test
    void testInline() {
        String expected = "[anchor=north west, minimum width=9.0cm, minimum height=2.73cm, text width=9.0cm, align=left]";
        ArgumentList argumentList = new ArgumentList(arguments);
        System.out.println(argumentList.inline());
        assertEquals(expected, argumentList.inline());
    }

    @Test
    void testAsBlock() {
        String[] expected = {
                "[",
                "anchor=north west,",
                "minimum width=9.0cm,",
                "minimum height=2.73cm,",
                "text width=9.0cm,",
                "align=left",
                "]"
        };
        ArgumentList argumentList = new ArgumentList(arguments);
        for (String line : argumentList.asBlock()) {
            System.out.println(line);
        }
        assertArrayEquals(expected, argumentList.asBlock());
    }


}
