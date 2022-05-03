package de.flozo.latex;

import java.util.ArrayList;
import java.util.List;

public class SimpleExpressionList {

    // required
    private final List<String> lines;


    // Accept any number of Strings or List<String>

    public SimpleExpressionList(String... lines) {
        this.lines = new ArrayList<>(List.of(lines));
    }

    public SimpleExpressionList(List<String> lines) {
        this.lines = lines;
    }


    // Allow chaining of append method
    public SimpleExpressionList append(SimpleExpressionList toAppend) {
        lines.addAll(toAppend.lines);
        return this;
    }

    public List<String> getLines() {
        return lines;
    }

    public int length() {
        return lines.size();
    }

}
