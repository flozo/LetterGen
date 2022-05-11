package de.flozo;

import de.flozo.data.Defaults;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LaTeXCode {


    public static final String USEPACKAGE = "\\usepackage";
    public static final String DOCUMENT_CLASS_DEFINITION = "\\documentclass[12pt, tikz, multi, crop]{standalone}";
    public static final String INPUT_ENCODING = USEPACKAGE + "[utf8]{inputenc}";
    public static final String FONT_ENCODING = USEPACKAGE + "[T1]{fontenc}";
    public static final String BABEL = USEPACKAGE + "[german]{babel}";
    public static final String HYPERXMP = USEPACKAGE + "{hyperxmp}";
    public static final String FIRASANS = USEPACKAGE + "[sfdefault, scaled=1.0098]{FiraSans}";
    public static final String NEWTXSF = USEPACKAGE + "{newtxsf}";
    public static final String FONTAWESOME5 = USEPACKAGE + "{fontawesome5}";
    public static final String CSQUOTES = USEPACKAGE + "[autostyle=true]{csquotes}";
    public static final String ENUMITEM = USEPACKAGE + "{enumitem}";
    public static final String MICROTYPE = USEPACKAGE + "[activate={true, nocompatibility}, final, tracking=true, kerning=true, spacing=true, factor=1100, stretch=8, shrink=8]{microtype}";
    public static final String TIKZ = USEPACKAGE + "{tikz}";
    public static final String HYPERREF = USEPACKAGE + "[unicode]{hyperref}";
    public static final String USE_TIKZ_LIBRARY = "\\usetikzlibrary{positioning, math, colorbrewer, backgrounds, matrix}";
    public static final String STANDALONE_ENV = "\\standaloneenv{tikzpicture}";
    public static final String HYPERSETUP = "colorlinks=true,\n" +
            "\turlcolor=Blues-K,\n" +
            "\tpdftitle={Title},\n" +
            "\tpdfsubject={Subject},\n" +
            "\tpdfauthor={Name},\n" +
            "\tpdfauthortitle={Title},\n" +
            "\tpdfcaptionwriter={Name},\n" +
            "\tpdfdate={2022-04-27},\n" +
            "\tpdfproducer={Name},\n" +
            "\tpdfcontactcity={City},\n" +
            "\tpdfcontactcountry={Country},\n" +
            "\tpdfcontactemail={test@email.com}," +
            "}";
    public static final String BEGIN_DOCUMENT = "\\begin{document}";
    public static final String END_DOCUMENT = "\\end{document}";
    public static final String BEGIN_TIKZ_PICTURE = "\\begin{tikzpicture}";
    public static final String END_TIKZ_PICTURE = "\\end{tikzpicture}";
    public static final String PGF_DECLARE_LAYER = "\\pgfdeclarelayer";
    public static final String LAYER_BACKGROUND = "background";
    public static final String LAYER_FORE_BACKGROUND = "forebackground";
    public static final String LAYER_FOREGROUND = "foreground";
    public static final String LAYER_MAIN = "main";
    public static final String PGF_SET_LAYERS = "\\pgfsetlayers";
    public static final String COMMA_SPACE = ", ";
    public static final String SET_LAYERS = PGF_SET_LAYERS + '{' + LAYER_BACKGROUND + COMMA_SPACE + LAYER_FORE_BACKGROUND + COMMA_SPACE + LAYER_MAIN + COMMA_SPACE + LAYER_FOREGROUND + "}";
    public static final String BEGIN_PGF_ON_LAYER = "\\begin{pgfonlayer}";
    public static final String END_PGF_ON_LAYER = "\\begin{pgfonlayer}";


    public ArrayList<String> latexCode() {
        ArrayList<String> codeLines = new ArrayList<>();
        codeLines.add(DOCUMENT_CLASS_DEFINITION);
        codeLines.add(INPUT_ENCODING);
        codeLines.add(FONT_ENCODING);
        codeLines.add(BABEL);
        codeLines.add(HYPERXMP);
        codeLines.add(FIRASANS);
        codeLines.add(NEWTXSF);
        codeLines.add(FONTAWESOME5);
        codeLines.add(CSQUOTES);
        codeLines.add(ENUMITEM);
        codeLines.add(MICROTYPE);
        codeLines.add(TIKZ);
        codeLines.add(HYPERREF);
        codeLines.add(USE_TIKZ_LIBRARY);
        codeLines.add(STANDALONE_ENV);
        codeLines.add(HYPERSETUP);

        codeLines.add(BEGIN_DOCUMENT);

        // Layer declarations
        codeLines.add(PGF_DECLARE_LAYER + "{" + LAYER_BACKGROUND + "}");
        codeLines.add(PGF_DECLARE_LAYER + "{" + LAYER_FORE_BACKGROUND + "}");
        codeLines.add(PGF_DECLARE_LAYER + "{" + LAYER_FOREGROUND + "}");
        codeLines.add(SET_LAYERS);

        codeLines.add(BEGIN_TIKZ_PICTURE + "[");
        codeLines.add("\t" + "trim left=0pt,");
        codeLines.add("\t" + "trim right={" + Defaults.A4_WIDTH + "cm},");
        codeLines.add("\t" + "]");

        // Define background rectangle
        codeLines.add("\t" + BEGIN_PGF_ON_LAYER + "{" + LAYER_BACKGROUND + "}");
        codeLines.add("\t\t" + "\\fill [fill=none] (0, 0) rectangle (" + Defaults.A4_WIDTH + ", " + Defaults.A4_HEIGHT + ");");
        codeLines.add("\t" + END_PGF_ON_LAYER);


        codeLines.add(END_TIKZ_PICTURE);

        codeLines.add(END_DOCUMENT);


        return codeLines;
    }


//    public List<String> assembleEnvironment(String environmentName, String environmentArgument, List<String> optionalParameters, List<String> codeBlock) {
//        List<String> codeLines = new ArrayList<>();
//        String openingTag = "\\begin{" + environmentName + "}";
//        if (environmentArgument != null) {
//            openingTag = openingTag + "{" + environmentArgument + "}";
//        }
//        if (optionalParameters != null) {
//            openingTag = openingTag + " [";
//        }
//        codeLines.add(openingTag);
//        if (optionalParameters != null) {
//            for (String parameter : optionalParameters) {
//                codeLines.add("\t" + parameter + ",");
//            }
//            codeLines.add("\t]");
//        }
//        for (String line : codeBlock) {
//            codeLines.add("\t" + line);
//        }
//        codeLines.add("\\end{" + environmentName + "}");
//        return codeLines;
//    }


    public void writeToFile(List<String> codeLines, String outputFile) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(outputFile)) {
            for (String codeLine : codeLines) {
                printWriter.println(codeLine);
            }
        }
    }

}
