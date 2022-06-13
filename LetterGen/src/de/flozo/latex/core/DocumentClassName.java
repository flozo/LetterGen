package de.flozo.latex.core;

public enum DocumentClassName {
    ARTICLE("article"),
    PROC("proc"),
    MINIMAL("minimal"),
    REPORT("report"),
    BOOK("book"),
    SLIDES("slides"),
    MEMOIR("memoir"),
    LETTER("letter"),
    BEAMER("beamer"),
    SCRBOOK("scrbook"),
    SCRREPRT("scrreprt"),
    SCRARTCL("scrartcl"),
    SCRLTTR2("scrlttr2"),
    STANDALONE("standalone");

    private final String documentClassName;

    DocumentClassName(String documentClassName) {
        this.documentClassName = documentClassName;
    }

    public String getString() {
        return documentClassName;
    }
}
