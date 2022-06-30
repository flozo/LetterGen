package de.flozo.latex.core;

public class Documentclass{//} extends Command {

    public static final CommandName KEYWORD = CommandName.DOCUMENTCLASS;
    private final DocumentClassName documentClassName;


    private Documentclass(DocumentClassName documentClassName, String... optionList) {
//        super(KEYWORD, new ExpressionList(documentClassName.getString()), new ExpressionList(optionList));
        this.documentClassName = documentClassName;
    }

    public static GenericCommand createWithOptions(DocumentClassName documentClassName, String... options) {
        return new GenericCommand.Builder(KEYWORD.getString())
                .optionList(options)
                .body(documentClassName.getString())
                .optionBrackets(Bracket.SQUARE_BRACKETS)
                .bodyBrackets(Bracket.CURLY_BRACES)
                .optionTerminator(StatementTerminator.COMMA)
                .build();
    }

    public DocumentClassName getDocumentClassName() {
        return documentClassName;
    }

    @Override
    public String toString() {
        return "Documentclass{" +
                "documentClassName=" + documentClassName +
                '}';
    }
}
