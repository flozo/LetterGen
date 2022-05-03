package de.flozo.latex;

import java.util.ArrayList;
import java.util.List;

public class SimpleExpressionList {

    private final List<String> lines;   // required

    public SimpleExpressionList(String... lines) {
        this.lines = new ArrayList<>(List.of(lines));
    }

    public int length() {
        return lines.size();
    }

    public SimpleExpressionList append(SimpleExpressionList toAppend) {
        lines.addAll(toAppend.lines);
        return this;    // allow chaining
    }

    public List<String> getLines() {
        return lines;
    }


}
