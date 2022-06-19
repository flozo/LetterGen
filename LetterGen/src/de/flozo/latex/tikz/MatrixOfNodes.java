package de.flozo.latex.tikz;

import de.flozo.latex.core.FontSize;
import de.flozo.latex.core.StatementTerminator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixOfNodes {

    private final String name;
    private final List<List<Node>> matrix;

    private final Point position;

    private final Anchor anchor;
    private final FontSize fontSize;


    public MatrixOfNodes(Builder builder) {
        this.name = builder.name;
        this.matrix = builder.matrix;
        this.position = builder.position;
        this.anchor = builder.anchor;
        this.fontSize = builder.fontSize;
    }


    public List<String> getBlock() {
        return new Node.Builder(assembleTable())
                .name(name)
                .position(position)
                .isMatrix(true)
                .anchor(anchor)
                .fontSize(fontSize)
                // preliminary
                .addCustomOption("column 1/.style={nodes={rectangle, draw=none, inner xsep=8pt, inner ysep=6pt, align=right, minimum width=0.6cm, minimum height=0.5cm, text width=7.8cm, text height=0.25cm}}")
                .addCustomOption("column 2/.style={nodes={rectangle, draw=none, inner xsep=0pt, inner ysep=6pt, align=center, minimum width=0.4cm, minimum height=0.5cm, text width=0.4cm, text height=0.25cm}}")
                .bodyTerminator(StatementTerminator.DOUBLE_BACKSLASH)
                .skipLastTerminator(false)
                .build().getBlock();
    }


    private List<String> assembleTable() {
        List<String> matrixLines = new ArrayList<>();
        for (int row = 0; row < getNumRows(); row++) {
            List<Node> rowNodes = matrix.get(row);
            List<String> rowStrings = rowNodes.stream().map(Node::getInline).collect(Collectors.toList());
            matrixLines.add(String.join(" & ", rowStrings));
        }
        return matrixLines;
    }

    public int getNumCols() {
        return matrix.get(0).size();
    }

    public int getNumRows() {
        return matrix.size();
    }

    @Override
    public String toString() {
        return "MatrixOfNodes{" +
                "name='" + name + '\'' +
                ", matrix=" + matrix +
                ", position=" + position +
                ", anchor=" + anchor +
                ", fontSize=" + fontSize +
                '}';
    }

    public static class Builder {

        // required
        private final String name;
        private final List<List<Node>> matrix = new ArrayList<>();
        private final Point position;
        private final Anchor anchor;

        // optional
        private FontSize fontSize = FontSize.NORMAL_SIZE;


        public Builder(String name, Point position, Anchor anchor) {
            this.name = name;
            this.position = position;
            this.anchor = anchor;
        }

        public Builder addRow(String... row) {
            return addRow(new ArrayList<>(List.of(row)));
        }

        public Builder addRow(List<String> row) {
            return addRowOfNodes(row.stream().map(e -> new Node.Builder(e).build()).collect(Collectors.toList()));
        }

        public Builder addRowOfNodes(Node... row) {
            return addRowOfNodes(new ArrayList<>(List.of(row)));
        }

        public Builder addRowOfNodes(List<Node> row) {
//            System.out.println(row);
            this.matrix.add(row);
            return this;
        }

        public Builder fontSize(FontSize fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public MatrixOfNodes build() {
            return new MatrixOfNodes(this);
        }

    }

}
