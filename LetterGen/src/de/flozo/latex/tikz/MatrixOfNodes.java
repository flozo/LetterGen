package de.flozo.latex.tikz;

import de.flozo.latex.core.*;

import java.util.ArrayList;
import java.util.List;

public class MatrixOfNodes {

    private final String name;
    private final List<List<String>> matrix;

    private final double x;
    private final double y;

    private final Anchor anchor;
    private final FontSize fontSize;


    public MatrixOfNodes(MatrixBuilder builder) {
        this.name = builder.name;
        this.matrix = builder.matrix;
        this.x = builder.x;
        this.y = builder.y;
        this.anchor = builder.anchor;
        this.fontSize = builder.fontSize;
    }


    public List<String> getBlock() {
        return new Node.NodeBuilder(x, y, assembleTable())
                .name(name)
                .anchor(anchor)
                .fontSize(fontSize)
                .bodyTerminator(StatementTerminator.DOUBLE_BACKSLASH)
                .build().getBlock();
    }


    private List<String> assembleTable() {
        List<String> matrixLines = new ArrayList<>();
        for (int row = 0; row < getNumRows(); row++) {
            matrixLines.add(String.join(" & ", matrix.get(row)));
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
                ", x=" + x +
                ", y=" + y +
                ", anchor=" + anchor +
                ", fontSize=" + fontSize +
                '}';
    }

    public static class MatrixBuilder {

        // required
        private final String name;
        private final List<List<String>> matrix = new ArrayList<>();
        private final double x;
        private final double y;
        private final Anchor anchor;

        // optional
        private FontSize fontSize = FontSize.NORMAL_SIZE;


        public MatrixBuilder(String name, double x, double y, Anchor anchor) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.anchor = anchor;
        }

        public MatrixBuilder addRow(String... row) {
            return addRow(new ArrayList<>(List.of(row)));
        }

        public MatrixBuilder addRow(List<String> row) {
            this.matrix.add(row);
            return this;
        }

        public MatrixBuilder fontSize(FontSize fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public MatrixOfNodes build() {
            return new MatrixOfNodes(this);
        }

    }

}
