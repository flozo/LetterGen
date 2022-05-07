package de.flozo;

import de.flozo.latex.Command;
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


//        Code code = new Code.CodeBuilder(expressionList)
//                .terminator(StatementTerminator.COMMA)
//                .dropLast(true)
//                .brackets(Bracket.SQUARE_BRACKETS)
//                .build();


//        Code optionList = new Code.CodeBuilder(expressionList)
//                .terminator(StatementTerminator.COMMA)
//                .brackets(Bracket.SQUARE_BRACKETS)
//                .interBracketSpacing(true)
//                .skipLast(true)
//                .build();

//        System.out.println("------- optionList");
//        for (String line : optionList.getBlock()) {
//            System.out.println(line);
//        }
//        System.out.println("------- optionList");





        Documentclass documentclass = new Documentclass(PackageName.STANDALONE, "12pt", "tikz", "multi", "crop");
        Usepackage inputenc = new Usepackage(PackageName.INPUTENC, "utf8");
        Usepackage fontenc = new Usepackage(PackageName.FONTENC, "T1");
        Usepackage babel = new Usepackage(PackageName.BABEL, "german");
        Usepackage hyperxmp = new Usepackage(PackageName.HYPERXMP);
        Usepackage firaSans = new Usepackage(PackageName.FIRASANS, "sfdefault", "scaled=1.0098");
        Usepackage newtxsf = new Usepackage(PackageName.NEWTXSF);
        Usepackage fontawesome = new Usepackage(PackageName.FONTAWESOME5);
        Usepackage csquotes = new Usepackage(PackageName.CSQUOTES, "autostyle=true");
        Usepackage enumitem = new Usepackage(PackageName.ENUMITEM);
        Usepackage microtype = new Usepackage(PackageName.MICROTYPE, "activate={true, nocompatibility}", "final", "tracking=true", "kerning=true", "spacing=true", "factor=1100", "stretch=8", "shrink=8");
        Usepackage tikz = new Usepackage(PackageName.TIKZ);
        Usepackage hyperref = new Usepackage(PackageName.HYPERREF, "unicode");
        Command usetikzlibrary = new Command(CommandName.USETIKZLIBRARY, "positioning", "math", "colorbrewer", "backgrounds", "matrix");
        Command standanloneenv = new Command(CommandName.STANDALONEENV, "tikzpicture");

        Command hypersetup = new Command(CommandName.HYPERSETUP);

        ExpressionList preamble = new ExpressionList(
                documentclass.getInline(),
                inputenc.getInline(),
                fontenc.getInline(),
                babel.getInline(),
                hyperxmp.getInline(),
                firaSans.getInline(),
                newtxsf.getInline(),
                fontawesome.getInline(),
                csquotes.getInline(),
                enumitem.getInline(),
                microtype.getInline(),
                tikz.getInline(),
                hyperref.getInline(),
                usetikzlibrary.getInline(),
                standanloneenv.getInline()
        );

        for (String line : preamble.getLines()) {
            System.out.println(line);
        }



//
//        ExpressionList newList = new ExpressionList(code1.getBlock());
//        for (String line : newList.getLines()) {
//            System.out.println(line);
//        }
//
//
//        System.out.println(code1.getInline());
//
//        Environment document = new Environment.EnvironmentBuilder(EnvironmentName.DOCUMENT, codeBlock)
//                .inlineOptions(false)
//                .optionalArguments(expressionList)
//                .trailingOpeningBracketBody(true)
//                .trailingOpeningBracketOption(false)
//                .build();
//
//        for (String line : document.getBlock()) {
//            System.out.println(line);
//        }

    }
}
