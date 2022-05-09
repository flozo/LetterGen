package de.flozo.latex.core;

import java.util.*;

public class Preamble {

    // required
    private final Documentclass documentclass;

    // optional
    private final Map<PackageName, ExpressionList> usepackageList;

    // Constructor using settings from subclass CodeBuilder
    public Preamble(Documentclass documentclass) {
        this.documentclass = documentclass;
        this.usepackageList = new LinkedHashMap<>();
    }


    public Preamble add(PackageName packageName, String... options) {
        usepackageList.put(packageName, new ExpressionList(options));
        return this;
    }

    public List<String> getBlock() {
        List<String> codeLines = new ArrayList<>();
        for (Map.Entry<PackageName, ExpressionList> entry : usepackageList.entrySet()) {
            Usepackage usepackage = new Usepackage(entry.getKey(), entry.getValue());
            codeLines.add(usepackage.getInline());
        }
        return codeLines;
    }

}
