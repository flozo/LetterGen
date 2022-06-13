package de.flozo.latex.core;

public class Documentclass extends Command {

    public static final CommandName KEYWORD = CommandName.DOCUMENTCLASS;
    private final DocumentClassName documentClassName;


    private Documentclass(DocumentClassName documentClassName, String... optionList) {
        super(KEYWORD, new ExpressionList(documentClassName.getString()), new ExpressionList(optionList));
        this.documentClassName = documentClassName;
    }

    public static Documentclass createWithOptions(DocumentClassName documentClassName, String... options) {
        return new Documentclass(documentClassName, options);
    }

    public DocumentClassName getDocumentClassName() {
        return documentClassName;
    }
}
