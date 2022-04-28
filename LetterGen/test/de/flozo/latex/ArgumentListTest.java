package de.flozo.latex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArgumentListTest {

    @Test
    void testInline() {
        String[] arguments = {
                "anchor=north west",
                "minimum width=9.0cm",
                "minimum height=2.73cm",
                "text width=9.0cm",
                "align=left"
        };
        String expected = "[anchor=north west, minimum width=9.0cm, minimum height=2.73cm, text width=9.0cm, align=left]";

        Code lines = new Code(arguments);
        ArgumentList argumentList = new ArgumentList(lines);
        System.out.println(argumentList.inline());
        assertEquals(expected, argumentList.inline());

    }
}
