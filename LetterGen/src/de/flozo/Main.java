package de.flozo;

import de.flozo.latex.core.*;
import de.flozo.latex.tikz.Layer;

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


        Command2 pgfdeclarelayer = new Command2.Command2Builder(CommandName.PGFDECLARELAYER.getString())
                .body("background")
                .build();

        Layer pfglayers = new Layer.LayerBuilder("background", "forebackground", "main", "foreground")
                .build();

        for (String line : pfglayers.getBlock()) {
            System.out.println(line);
        }



        Environment3 document = new Environment3.Environment3Builder(EnvironmentName.DOCUMENT)
                .build();




        Environment3 newEnvironment = new Environment3.Environment3Builder(EnvironmentName.TIKZPICTURE)
                .body("positioning",
                        "math",
                        "colorbrewer",
                        "backgrounds"
                )
                .optionalArguments("test1=1", "test2=99")
                .build();



        for (String line : newEnvironment.getBlock()) {
            System.out.println(line);
        }


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


    }

}
