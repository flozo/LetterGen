package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class ExpressionList {

    // required
    private final List<String> lines;


    // Accept any number of Strings or List<String>

    public ExpressionList(String... lines) {
        this.lines = new ArrayList<>(List.of(lines));
    }

    public ExpressionList(List<String> lines) {
        this.lines = lines;
    }


    // Allow chaining of append method
    public ExpressionList append(ExpressionList toAppend) {
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
