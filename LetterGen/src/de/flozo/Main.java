package de.flozo;

import de.flozo.latex.core.ExpressionList;
import de.flozo.latex.core.*;

public class Main {

    public static void main(String[] args) {

//        LaTeXCode code = new LaTeXCode();

//        ExpressionList pgfonlayerCode = new ExpressionList(
//                "\\fill [fill=black] (0, 0) rectangle (21.0, 29.7);",
//                "\\fill [fill=white] (0, 0) rectangle (21.0, 29.7);"
//        );
        ExpressionList codeBlock = new ExpressionList(
                "% Test line",
                "% Another test line",
                "% And another one"
        );

        ExpressionList codeBlock2 = new ExpressionList(
                "% And another one",
                "% And another one",
                "% And another one"
        );


        ExpressionList expressionList = new ExpressionList(
                "inner xsep=0pt",
                "inner ysep=0pt",
                "trim left=0pt",
                "trim right={20 cm}"
        );

//        ArgumentList argumentListOptions = new ArgumentList(String.valueOf(expressionList.getLines()));
//        ArgumentList argumentListBody = new ArgumentList(String.valueOf(codeBlock));
//        String body = argumentListBody.getInline();
//        InlineCommand inlineCommand = new InlineCommand("usepackage", argumentListOptions, body);
//        for (String line : inlineCommand.getLines()) {
//            System.out.println(line);
//        }

        expressionList.append(codeBlock).append(codeBlock2);


//        Code code = new Code.CodeBuilder(expressionList)
//                .terminator(StatementTerminator.COMMA)
//                .dropLast(true)
//                .brackets(Bracket.SQUARE_BRACKETS)
//                .build();


        Code.CodeBuilder codeBuilder = new Code.CodeBuilder(expressionList)
                .skipLast(true);

        Code code1 = codeBuilder
                .terminator(StatementTerminator.SEMICOLON)
                .brackets(Bracket.CURLY_BRACES).build();
        Code code2 = codeBuilder
                .skipLast(false)
                .build();

        for (String line : code1.getBlock()) {
            System.out.println(line);
        }

        System.out.println("******************");
        for (String line : code1.getExpressionList().getLines()) {
            System.out.println(line);
        }
        System.out.println("******************");


        for (String line : code2.getBlock()) {
            System.out.println(line);
        }


        ExpressionList newList = new ExpressionList(code1.getBlock());
        for (String line : newList.getLines()) {
            System.out.println(line);
        }


        System.out.println(code1.getInline());

        Environment document = new Environment.EnvironmentBuilder(EnvironmentName.DOCUMENT, codeBlock)
                .inlineOptions(false)
                .optionalArguments(expressionList)
                .trailingOpeningBracketBody(true)
                .trailingOpeningBracketOption(false)
                .build();

        for (String line : document.getBlock()) {
            System.out.println(line);
        }

    }
}
