package de.flozo;

import de.flozo.latex.ExpressionList;
import de.flozo.latex.StatementTerminator;

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

//        InlineExpressionList inlineExpressionList = new InlineExpressionList(expressionList,StatementTerminator.DOUBLE_BACKSLASH,true);

//        expressionList.append(codeBlock.setTerminator(StatementTerminator.COMMA)).append(codeBlock2);


//        expressionList.append(expressionList).append(codeBlock).addStatementTerminator(StatementTerminator.COMMA);

//        System.out.println("ExpressionList:");
//        System.out.println("---------------");
//        for (String line : expressionList.getLines(StatementTerminator.DOUBLE_BACKSLASH)) {
//            System.out.println(line);
//        }
//


        System.out.println("---------------");
        for (String line : expressionList.getLines()) {
            System.out.println(line);
        }


        System.out.println("---------------");
        System.out.println(expressionList.getInline(StatementTerminator.DOUBLE_BACKSLASH, false));



    }
}
