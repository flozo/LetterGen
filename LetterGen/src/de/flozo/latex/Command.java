package de.flozo.latex;

public class Command {

//    private final String nameTag;
//    private final Code body;
//    private final Code optionalArguments;
//    private final boolean argsOnOneLine;
//
//    public Command(String nameTag, Code body) {
//        this(nameTag, body, null, false);
//    }
//
//    public Command(String nameTag, Code body, Code optionalArguments, boolean argsOnOneLine) {
//        this.nameTag = nameTag;
//        this.body = body;
//        this.optionalArguments = optionalArguments;
//        this.argsOnOneLine = argsOnOneLine;
//    }

//    public List<String> assembleCommand() {
//        List<String> codeLines = new ArrayList<>();
//        String openingTag = "\\" + nameTag;
//        if (optionalArguments != null) {
//            openingTag = openingTag + " [";
//            if (optionalArguments.getCodeLines().size() == 1) {
//                openingTag = openingTag + optionalArguments.getCodeLines() + "]";
//            }
//            codeLines.add(openingTag);
//            if (optionalArguments.getCodeLines().size() > 1) {
//                // If argsOnOneLine is true, concatenate arguments
//                if (argsOnOneLine) {
//                    System.out.println(optionalArguments.getCodeLines());
//                    System.out.println(optionalArguments.getCodeLines().indexOf("anchor=south west"));
//                    String line = String.join(", ", optionalArguments.getCodeLines());
//                    line = line + "]";
//                    System.out.println(line);
//                    codeLines.add(line);
//                } else {
//                    System.out.println("Ich will das nicht!");
//                    for (String argument : optionalArguments.getCodeLines()) {
//                        codeLines.add("\t" + argument + ",");
//                    }
//                    codeLines.add("\t]");
//                }
//            }
//        }
//        codeLines.add("{" + body.getCodeLines() + "}");
//        return codeLines;
//    }

//    public String getNameTag() {
//        return nameTag;
//    }
//
//    public Code getBody() {
//        return body;
//    }
//
//    public Code getOptionalArguments() {
//        return optionalArguments;
//    }
}
