package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        LaTeXCode code = new LaTeXCode();

        List<String> pgfonlayerBlock = List.of(
                "\\fill [fill=none] (0, 0) rectangle (21.0, 29.7);"
        );

        Environment pgfonlayer = new Environment("pgfonlayer", pgfonlayerBlock, "background", null);


        List<String> codeBlock = new ArrayList<>(List.of(
                "% Test line",
                "% Another test line",
                "% And another one"
        ));


        List<String> tikzpictureOptional = List.of(
                "inner xsep=0pt",
                "inner ysep=0pt",
                "trim left=0pt",
                "trim right={20 cm}"
        );

        codeBlock.addAll(pgfonlayer.assembleEnvironment());


        Environment tikzPicture = new Environment("tikzpicture", codeBlock, null, tikzpictureOptional);
        Environment document = new Environment("document", tikzPicture.assembleEnvironment());
        try {
            code.writeToFile(document.assembleEnvironment(), "test_output.tex");
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

    }
}
