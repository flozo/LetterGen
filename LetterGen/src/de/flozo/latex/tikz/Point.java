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

    private Point(PointBuilder builder) {
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


    public static class PointBuilder {

        // required
        private final double xValue;
        private final double yValue;

        // optional
        private LengthUnit xUnit = LengthUnit.DEFAULT;
        private LengthUnit yUnit = LengthUnit.DEFAULT;
        private CoordinateMode coordinateMode = CoordinateMode.ABSOLUTE;


        public PointBuilder(double xValue, double yValue) {
            this.xValue = xValue;
            this.yValue = yValue;
        }

        public PointBuilder xUnit(LengthUnit xUnit) {
            this.xUnit = xUnit;
            return this;
        }

        public PointBuilder yUnit(LengthUnit yUnit) {
            this.yUnit = yUnit;
            return this;
        }

        public PointBuilder coordinateMode(CoordinateMode coordinateMode) {
            this.coordinateMode = coordinateMode;
            return this;
        }


        public Point build() {
            return new Point(this);
        }
    }
}
