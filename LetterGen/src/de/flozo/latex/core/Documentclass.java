package de.flozo.latex.core;

public class Documentclass extends Command {

    public static final CommandName KEYWORD = CommandName.DOCUMENTCLASS;
    private final DocumentClassName documentClassName;


    public Documentclass(DocumentClassName documentClassName, String... optionList) {
        super(KEYWORD, new ExpressionList(documentClassName.getString()), new ExpressionList(optionList));
        this.documentClassName = documentClassName;
    }

    public Documentclass(DocumentClassName packageName, ExpressionList optionList) {
        super(KEYWORD, new ExpressionList(packageName.getString()), optionList);
        this.documentClassName = packageName;
    }
}
