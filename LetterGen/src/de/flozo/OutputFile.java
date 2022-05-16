package de.flozo;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OutputFile {

    private String outputDirectory;
    private String fileNameLatex;
    private List<String> fileContent;
    private String fileExtension = "pdf";
    private String latexCommand = "pdflatex";
    private String option1 = "-synctex=1";
    private String option2 = "-interaction=nonstopmode";
    private String option3 = "-output-directory";
    private String fileNameOutputLog = "pdfLaTeX_last_output.log";
    private String fileNameErrorLog = "pdfLaTeX_last_error.log";


    public OutputFile(String outputDirectory, String fileNameLatex, List<String> fileContent) {
        this.outputDirectory = outputDirectory;
        this.fileNameLatex = fileNameLatex;
        this.fileContent = fileContent;
    }


    public void create(boolean runPDFLatex, boolean openPDF) {
        Path pathOfTexFile = Paths.get(outputDirectory, fileNameLatex);
        Path pathOfOutputLogFile = Paths.get(outputDirectory, fileNameOutputLog);
        Path pathOfErrorLogFile = Paths.get(outputDirectory, fileNameErrorLog);
        if (writeToFile(fileContent, pathOfTexFile.toString())) {
            System.out.println("[output] ... success!");
        }
        if (runPDFLatex || openPDF) {
            if (runPDFLatex(outputDirectory, fileNameLatex)) {
                System.out.println("[output] ... success!");
            }
        }
        if (openPDF) {
            if (openPDF(removeFileExtension(pathOfTexFile.toString()) + "." + fileExtension)) {
                System.out.println("[output] ... success!");
            }
        }
    }


    public boolean writeToFile(List<String> codeLines, String outputFile) {
        System.out.println("[output] Writing LaTeX code to file \"" + outputFile + "\" ...");
        try (PrintWriter printWriter = new PrintWriter(outputDirectory, outputFile)) {
            for (String codeLine : codeLines) {
                printWriter.println(codeLine);
            }
            return true;
        } catch (IOException e) {
            System.out.println("[output] ... failed to write to file!");
            return false;
        }
    }

    public boolean runPDFLatex(String outputDirectory, String outputFile) {
        ProcessBuilder processBuilder = new ProcessBuilder(latexCommand, option1, option2, option3, outputDirectory, outputFile);
        processBuilder.redirectErrorStream(true);
        File outputLog = new File(outputDirectory, fileNameOutputLog);
        File errorLog = new File(outputDirectory, fileNameErrorLog);
        processBuilder.redirectOutput(outputLog);
        processBuilder.redirectError(errorLog);
        String command = "\"" + String.join(" ", processBuilder.command()) + "\"";
        System.out.println("[output] Run command " + command + " ...");
        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
//            throw new IOException();
            return true;
        } catch (IOException | InterruptedException e) {
            System.out.println("[output] ... failed to run pdflatex!");
            return false;
        }
    }

    public boolean openPDF(String pdfFile) {
        System.out.println("[output] Open PDF file with default viewer ...");
        File pdf = new File(pdfFile);
        try {
            Desktop.getDesktop().open(pdf);
            return true;
        } catch (IOException e) {
            System.out.println();
            System.out.println("[output] ... failed to open PDF file!");
            return false;
        }
    }


    private String removeFileExtension(String fileName) {
        if (fileName.contains(".")) {
            return fileName.substring(0, fileName.lastIndexOf('.'));
        } else {
            return fileName;
        }
    }


}
