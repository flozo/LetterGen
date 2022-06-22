package de.flozo.latex.core;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Length {


    public static final LengthUnit DEFAULT_LENGTH_UNIT = LengthUnit.DEFAULT;

    private final double numericalValue;
    private final LengthUnit unit;


    public Length(double numericalValue) {
        this(numericalValue, DEFAULT_LENGTH_UNIT);
    }

    public Length(double numericalValue, LengthUnit unit) {
        this.numericalValue = numericalValue;
        this.unit = unit;
    }

    public static Length inDefaultUnit(double numericalValue) {
        return new Length(numericalValue);
    }

    public static Length inCentimeter(double numericalValue) {
        return new Length(numericalValue, LengthUnit.CENTIMETER);
    }
    public static Length inPoint(double numericalValue) {
        return new Length(numericalValue, LengthUnit.POINT);
    }

    public static Length fromNumberAndUnit(double numericalValue, LengthUnit unit) {
        return new Length(numericalValue, unit);
    }

    public double getNumericalValue() {
        return numericalValue;
    }

    public String getFormatted() {
        // Avoid trailing zeros; ensure point is used as decimal separator
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        return (df.format(numericalValue) + unit.getString());
    }

    @Override
    public String toString() {
        return "Length{" +
                "numericalValue=" + numericalValue +
                ", unit=" + unit +
                '}';
    }
}
