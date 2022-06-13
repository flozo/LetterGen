package de.flozo.latex.letter;

public class Closing  {

    private String signatureFileName;
    private String complimentaryClosing;
    private String name;
    private double x;
    private double y;


    public Closing(String signatureFileName, String complimentaryClosing, String name, double x, double y) {
        this.signatureFileName = signatureFileName;
        this.complimentaryClosing = complimentaryClosing;
        this.name = name;
        this.x = x;
        this.y = y;
    }

//    public String generate(){
//        // Generate letter closing
//        Includegraphics signature = new Includegraphics.IncludegraphicsBuilder(signatureFileName)
//                .height(1.3)
//                .build();
//        ExpressionList closingTextLines = new ExpressionList(
//                complimentaryClosing,
//                signature.getStatement(),
//                name
//        );
//        Code closingText = new Code.CodeBuilder(closingTextLines)
//                .terminator(StatementTerminator.DOUBLE_BACKSLASH)
//                .inlineSpacing(false)
//                .build();
//        Node closing = new Node.NodeBuilder(x, y, closingText.getInline())
//                .name("closing")
//                .anchor(Anchor.NORTH_WEST)
//                .textWidth(10)
//                .build();
//        return closing.getInline();
//    }


}
