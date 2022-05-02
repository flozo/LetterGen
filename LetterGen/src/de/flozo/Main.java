package de.flozo;

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

        ArgumentList argumentList = new ArgumentList(String.valueOf(expressionList.getLines()));
        InlineCommand inlineCommand = new InlineCommand("usepackage", argumentList, codeBlock);
        for (String line : inlineCommand.getLines()) {
            System.out.println(line);
        }


    }
}
