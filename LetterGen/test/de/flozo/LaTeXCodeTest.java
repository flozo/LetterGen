package de.flozo;

class LaTeXCodeTest {
    private LaTeXCode laTeXCode = new LaTeXCode();


//    @org.junit.jupiter.api.Test
//    void assembleEnvironment_pgfonlayer() {
//        String environmentName = "pgfonlayer";
//        String environmentArgument = "background";
//        List<String> codeBlock = List.of("\\fill [fill=none] (0, 0) rectangle (21.0, 29.7);");
//
//        List<String> expectedResult = List.of(
//                "\\begin{pgfonlayer}{background}",
//                "\t\\fill [fill=none] (0, 0) rectangle (21.0, 29.7);",
//                "\\end{pgfonlayer}"
//        );
//        assertEquals(expectedResult, laTeXCode.assembleEnvironment(environmentName, environmentArgument, null, codeBlock));
//    }
//
//    @org.junit.jupiter.api.Test
//    void assembleEnvironment_tikzpicture() {
//        String environmentName = "tikzpicture";
//        String environmentArgument = null;
//        List<String> optionalParameters = List.of("inner xsep=0pt", "inner ysep=0pt", "trim left=0pt", "trim right={20 cm}");
//        List<String> codeBlock = List.of("% Test line", "% Another test line", "% And another one");
//
//        List<String> expectedResult = List.of(
//                "\\begin{tikzpicture} [",
//                "\tinner xsep=0pt,",
//                "\tinner ysep=0pt,",
//                "\ttrim left=0pt,",
//                "\ttrim right={20 cm},",
//                "\t]",
//                "\t% Test line",
//                "\t% Another test line",
//                "\t% And another one",
//                "\\end{tikzpicture}"
//        );
//        assertEquals(expectedResult, laTeXCode.assembleEnvironment(environmentName, null, optionalParameters, codeBlock));
//    }


}
