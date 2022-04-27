package main;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        LaTeXCode code = new LaTeXCode();
        List<String> codeBlock = List.of(
                "% Test line",
                "% Another test line",
                "% And another one"
        );

        List<String> tikzpictureOptional = List.of(
                "inner xsep=0pt",
                "inner ysep=0pt",
                "trim left=0pt",
                "trim right={20 cm}"
        );

        List<String> tikzPicture = code.assembleEnvironment("tikzpicture", null, tikzpictureOptional, codeBlock);
        List<String> document = code.assembleEnvironment("document", null, null, tikzPicture);
        code.writeToFile(document, "test_output.tex");
    }
}
