package de.flozo.latex.core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionListTest {

    ExpressionList expressionList = new ExpressionList(
            "inner xsep=0pt",
            "inner ysep=0pt",
            "trim left=0pt",
            "trim right={20 cm}"
    );

    ExpressionList expressionList2 = new ExpressionList(
            "% Test line",
            "% Another test line",
            "% And another one"
    );


    @Test
    void append() {
        ExpressionList expected = new ExpressionList(
                "inner xsep=0pt",
                "inner ysep=0pt",
                "trim left=0pt",
                "trim right={20 cm}",
                "% Test line",
                "% Another test line",
                "% And another one"
        );
        expressionList.append(expressionList2);
        assertEquals(expected.getLines(), expressionList.getLines());
    }

    @Test
    void getLines() {
        List<String> expected = new ArrayList<>(List.of(
                "inner xsep=0pt",
                "inner ysep=0pt",
                "trim left=0pt",
                "trim right={20 cm}"
        ));
        assertEquals(expected, expressionList.getLines());

    }

    @Test
    void length() {
        assertEquals(4,expressionList.length());
    }
}
