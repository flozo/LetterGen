package de.flozo.latex.core;

import java.util.*;

public class PackageList {

    private final Command2 documentclass;
    private final Map<PackageName, FormattedExpressionList> usepackageList;


    public PackageList(Command2 documentclass) {
        this.documentclass = documentclass;
        this.usepackageList = new LinkedHashMap<>();
    }


    // Add new usepackage statement; allow chaining of add method
    public PackageList add(PackageName packageName, String... options) {
        usepackageList.put(packageName, new FormattedExpressionList(options));
        return this;
    }

    public List<String> getBlock() {
        List<String> codeLines = new ArrayList<>();
        codeLines.add(documentclass.getInline());
        for (Map.Entry<PackageName, FormattedExpressionList> entry : usepackageList.entrySet()) {
            Usepackage usepackage = new Usepackage(entry.getKey(), entry.getValue());
            codeLines.add(usepackage.getInline());
        }
        return codeLines;
    }
}
