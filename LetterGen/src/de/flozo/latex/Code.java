package de.flozo.latex;

public class Code {



    private final String[] lines;

    public Code(String... lines) {
        this.lines = lines;
    }

    public String[] getCodeLines() {
        return lines;
    }

//    public String inline() {
//        return String.join(OPTIONAL_ARGUMENT_SEPARATOR, getCodeLines());
//    }
}
