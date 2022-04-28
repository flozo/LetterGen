package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code {

    private final List<String> codeLines = new ArrayList<>();

    public Code(String... lines) {
        this.codeLines.addAll(Arrays.asList(lines));
    }

    public List<String> getCodeLines() {
        return codeLines;
    }
}
