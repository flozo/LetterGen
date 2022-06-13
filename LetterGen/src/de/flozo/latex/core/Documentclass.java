package de.flozo.latex.core;

public class Documentclass{//} extends Command {

    public static final CommandName KEYWORD = CommandName.DOCUMENTCLASS;
    private final DocumentClassName documentClassName;


    private Documentclass(DocumentClassName documentClassName, String... optionList) {
//        super(KEYWORD, new ExpressionList(documentClassName.getString()), new ExpressionList(optionList));
        this.documentClassName = documentClassName;
    }

    public static Command2 createWithOptions(DocumentClassName documentClassName, String... options) {
        return new Command2.Command2Builder(KEYWORD.getString())
                .optionList(options)
                .body(documentClassName.getString())
                .optionBrackets(Bracket.SQUARE_BRACKETS)
                .bodyBrackets(Bracket.CURLY_BRACES)
                .optionTerminator(StatementTerminator.COMMA)
                .build();
    }

//    private Command2 assembleCommand() {
////        return
//    }

    public DocumentClassName getDocumentClassName() {
        return documentClassName;
    }
}
