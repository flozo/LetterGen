package de.flozo.latex.core;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Length {

    private final double numericalValue;
    private final LengthUnit unit;


    public Length(double numericalValue) {
        this(numericalValue, LengthUnit.DEFAULT);
    }

    public Length(double numericalValue, LengthUnit unit) {
        this.numericalValue = numericalValue;
        this.unit = unit;
    }


    public String getString() {
        // Avoid trailing zeros; ensure point is used as decimal separator
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        return (df.format(numericalValue) + unit.getString());
    }
}
