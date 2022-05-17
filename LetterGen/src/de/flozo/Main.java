package de.flozo;

import de.flozo.latex.core.*;
import de.flozo.latex.tikz.CoordinateMode;
import de.flozo.latex.tikz.DashPatternStyle;
import de.flozo.latex.tikz.Line;

public class Main {

    public static void main(String[] args) {


        Documentclass documentclass = new Documentclass(PackageName.STANDALONE, "12pt", "tikz", "multi", "crop");

        Preamble preamble = new Preamble(documentclass);
        preamble.add(PackageName.INPUTENC, "utf8")
                .add(PackageName.FONTENC, "T1")
                .add(PackageName.BABEL, "german")
                .add(PackageName.HYPERXMP)
                .add(PackageName.FIRASANS, "sfdefault", "scaled=1.0098")
                .add(PackageName.NEWTXSF)
                .add(PackageName.FONTAWESOME5)
                .add(PackageName.CSQUOTES, "autostyle=true")
                .add(PackageName.ENUMITEM)
                .add(PackageName.MICROTYPE, "activate={true, nocompatibility}", "final", "tracking=true", "kerning=true", "spacing=true", "factor=1100", "stretch=8", "shrink=8")
                .add(PackageName.TIKZ)
                .add(PackageName.HYPERREF, "unicode");


//        Node myNode = new Node.NodeBuilder(2.5, 127.10, "Test text")
//                .anchor(Anchor.NORTH_WEST)
//                .textWidth(16.5)
//                .fontSize(FontSize.LARGE)
//                .drawColor(new Color(ColorScheme.BLUES, ColorLetter.M))
//                .xShift(0.1)
//                .yShift(0.5)
//                .minimumWidth(9.0)
//                .textColor(new Color(ColorScheme.ORANGES, ColorLetter.D))
//                .minimumHeight(2.73)
//                .alignment(Alignment.CENTER)
//                .textDepth(0.5)
//                .name("name")
//                .build();
//
//        System.out.println(myNode.getStatement());
//


//        Circle myCircle = new Circle.CircleBuilder(5, 7)
//                .radius(5)
//                .xRadius(3)
//                .fillColor(new Color(ColorScheme.PURPLES, ColorLetter.H))
//                .yRadius(8)
//                .build();
//
//        System.out.println(myCircle.getStatement());
//
//
        Line myLine = new Line.LineBuilder(5, 5, 15, 15, CoordinateMode.RELATIVE)
                .nextPoint(20, 22, CoordinateMode.ABSOLUTE)
                .drawColor(new Color(ColorScheme.ORANGES, ColorLetter.H))
                .lineWidth(6)
                .dashPatternStyle(DashPatternStyle.DENSELY_DASH_DOT_DOT)
                .nextPoint(25, 27, CoordinateMode.RELATIVE_SET_ORIGIN, LengthUnit.POINT)
                .cycle(true)
                .nextPoint(2, 3, LengthUnit.CENTIMETER)
                .build();

        Command usetikzlibrary = new Command(CommandName.USETIKZLIBRARY,
                "positioning",
                "math",
                "colorbrewer",
                "backgrounds",
                "matrix");
        Command standaloneenv = new Command(CommandName.STANDALONEENV, "tikzpicture");
        Command hypersetup = new Command(CommandName.HYPERSETUP,
                "colorlinks=true",
                "urlcolor=Blues-K",
                "pdftitle={Application}",
                "pdfauthor={My Name}");


        ExpressionList tikzCode = new ExpressionList(myLine.getStatement());

        ExpressionList packageSettings = new ExpressionList(usetikzlibrary.getBlock());
        packageSettings.append(new ExpressionList(standaloneenv.getBlock())).append(new ExpressionList(hypersetup.getBlock()));

        Environment tikzpicture = new Environment.EnvironmentBuilder(EnvironmentName.TIKZPICTURE, tikzCode)
                .build();

        for (String line : tikzpicture.getExpressionList().getLines()) {
            System.out.println(line);
        }


        Environment document = new Environment.EnvironmentBuilder(EnvironmentName.DOCUMENT, tikzpicture.getExpressionList())
                .build();


        System.out.println("**************************");
        ExpressionList2 list2 = new ExpressionList2.ExpressionList2Builder("bla1", "bla2", "bla3").build();

        ExpressionList2 expressionList2 = new ExpressionList2.ExpressionList2Builder("test1", "test2", "test3")
                .brackets(Bracket.PARENTHESIS)
                .terminator(StatementTerminator.COMMA)
                .skipLastTerminator(true)
                .prepend(list2.getExpressions())
                .inlineSpacing(true)
                .append(list2.getExpressions())
                .append("line1", "line2")
                .prepend("another line1", "another lin2")
                .build();
        for (String line : expressionList2.getExpressions()) {
            System.out.println(line);
        }
        System.out.println("**************************");

        for (String line : expressionList2.getBlock()) {
            System.out.println(line);
        }
        System.out.println("**************************");

        System.out.println(expressionList2.getInline());
        System.out.println("**************************");

        Command2 command2 = new Command2.Command2Builder(CommandName.USEPACKAGE)
                .optionTerminator(StatementTerminator.COMMA)
                .bodyTerminator(StatementTerminator.DOUBLE_BACKSLASH)
                .inlineSpacingOptions(true)
                .optionList("sfdefault", "scaled=1.0098")
                .body("FiraSans")
                .bodyBrackets(Bracket.CURLY_BRACES)
                .optionBrackets(Bracket.SQUARE_BRACKETS)
                .skipLastTerminatorBody(true)
                .skipLastTerminatorOptions(true)
                .build();
        for (String line : command2.getInlineOptions()) {
            System.out.println(line);
        }
        System.out.println(command2.getInline());


//        // Assemble final code
//        ExpressionList finalCode = new ExpressionList(preamble.getBlock());
//        finalCode.append(packageSettings);
//        finalCode.append(document.getExpressionList());
//
//
//
//        String fileName = "test_output.tex";
//        String directory = "/tmp";
//
//
//        OutputFile outputFile = new OutputFile(directory, fileName, finalCode.getLines());
//        if (outputFile.create(true, true)) {
//            System.out.println("[output] Done!");
//        } else {
//            System.out.println("[output] Something went wrong!");
//        }
//

    }

}
