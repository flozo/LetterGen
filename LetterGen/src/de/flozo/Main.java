package de.flozo;

import de.flozo.latex.core.*;
import de.flozo.latex.tikz.*;
import de.flozo.latex.letter.Closing;
import de.flozo.latex.letter.DateField;
import de.flozo.latex.letter.Enclosure;
import de.flozo.latex.letter.SubjectField;
import de.flozo.latex.tikz.Rectangle;

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


        for (String line : preamble.getBlock()) {
            System.out.println(line);
        }


        Node myNode = new Node.NodeBuilder(2.5, 127.10, "Test text")
                .anchor(Anchor.NORTH_WEST)
                .textWidth(16.5)
                .fontSize(FontSize.LARGE)
                .drawColor(new Color(ColorScheme.BLUES, ColorLetter.M))
                .xShift(0.1)
                .yShift(0.5)
                .minimumWidth(9.0)
                .textColor(new Color(ColorScheme.ORANGES, ColorLetter.D))
                .minimumHeight(2.73)
                .alignment(Alignment.CENTER)
                .textDepth(0.5)
                .name("name")
                .build();

        System.out.println(myNode.getStatement());

        System.out.println("**************");
        Node mySecondNode = new Node.NodeBuilder(0.123, 0.346, "This is a new test")
                .name("MyNode")
                .textWidth(10.5)
                .lineCap(LineCap.BUTT)
                .lineJoin(LineJoin.ROUND)
                .lineWidthStyle(LineWidthStyle.ULTRA_THICK)
                .drawColor(new Color(ColorScheme.BU_GN, ColorLetter.C))
                .dashPatternStyle(DashPatternStyle.DENSELY_DASH_DOT_DOT)
                .build();
        System.out.println(mySecondNode.getStatement());
        System.out.println("**************");


        Closing closing = new Closing("/home/user/images/signature.pdf",
                "Sincerely",
                "My Name",
                2.5,
                5.7);
        System.out.println(closing.generate());

        ExpressionList enclosureItems = new ExpressionList("CV", "Certificate1", "Certificate2");
        Enclosure enclosure = new Enclosure("As attachment", enclosureItems, 2.5, 5.7);
        System.out.println(enclosure.generate());

        SubjectField subjectField = new SubjectField(2.5, 18.5, "Application for Java developer position");
        System.out.println(subjectField.generate());
        DateField dateField = new DateField(19.0, 19.2, "City", "01.01.2022");
        System.out.println(dateField.generate());


        System.out.println("~~~~~~~~~~~~~");
        Rectangle myRectangle = new Rectangle.RectangleBuilder(0, 0, 10, 10)
                .drawColor(new Color(ColorScheme.BU_GN, ColorLetter.M))
                .fillColor(new Color(ColorScheme.GREYS, ColorLetter.D))
                .lineWidthStyle(LineWidthStyle.SEMITHICK)
                .lineCap(LineCap.ROUND)
                .lineWidth(20)
                .build();

        System.out.println(myRectangle.getStatement());

    }

}
