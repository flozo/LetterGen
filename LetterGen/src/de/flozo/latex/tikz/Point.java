package de.flozo.latex.tikz;

import de.flozo.latex.core.Bracket;
import de.flozo.latex.core.Length;
import de.flozo.latex.core.LengthUnit;
import de.flozo.latex.core.StatementTerminator;

public class Point {

    // constants
    public static final Bracket brackets = Bracket.PARENTHESIS;
    public static final StatementTerminator valueSeparator = StatementTerminator.COMMA;


    // required
    private final double xValue;
    private final double yValue;
    private final Length xLength;
    private final Length yLength;

    // optional
    private final LengthUnit xUnit;
    private final LengthUnit yUnit;
    private final CoordinateMode coordinateMode;

    private Point(Builder builder) {
        this.xValue = builder.xValue;
        this.yValue = builder.yValue;
        this.xUnit = builder.xUnit;
        this.yUnit = builder.yUnit;
        this.coordinateMode = builder.coordinateMode;
        this.xLength = new Length(xValue, xUnit);
        this.yLength = new Length(yValue, yUnit);
    }

    public String getStatement() {
        return coordinateMode.getString() + brackets.getLeftBracket() +
                xLength.getString() +
                valueSeparator.getString() + " " +
                yLength.getString() +
                brackets.getRightBracket();
    }


    @Override
    public String toString() {
        return "Point{" +
                "xValue=" + xValue +
                ", yValue=" + yValue +
                ", xLength=" + xLength +
                ", yLength=" + yLength +
                ", xUnit=" + xUnit +
                ", yUnit=" + yUnit +
                ", coordinateMode=" + coordinateMode +
                '}';
    }

    public static class Builder {

        // required
        private final double xValue;
        private final double yValue;

        // optional
        private LengthUnit xUnit = LengthUnit.DEFAULT;
        private LengthUnit yUnit = LengthUnit.DEFAULT;
        private CoordinateMode coordinateMode = CoordinateMode.ABSOLUTE;


        public Builder(double xValue, double yValue) {
            this.xValue = xValue;
            this.yValue = yValue;
        }

        public Builder xUnit(LengthUnit xUnit) {
            this.xUnit = xUnit;
            return this;
        }

        public Builder yUnit(LengthUnit yUnit) {
            this.yUnit = yUnit;
            return this;
        }

        public Builder coordinateMode(CoordinateMode coordinateMode) {
            this.coordinateMode = coordinateMode;
            return this;
        }


        public Point build() {
            return new Point(this);
        }
    }
}
