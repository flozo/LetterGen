package de.flozo.latex.tikz;

public enum PathOperation {
    LINE("--"),
    RECTANGLE("rectangle"),
    CIRCLE("circle"),
    ELLIPSE("ellipse"),
    ARC("arc"),
    GRID("grid"),
    PARABOLA("parabola"),
    SIN("sin"),
    COS("cos");

    private final String pathOperation;

    PathOperation(String pathOperation) {
        this.pathOperation = pathOperation;
    }

    public String getString() {
        return pathOperation;
    }


}
