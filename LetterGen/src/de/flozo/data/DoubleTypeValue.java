package de.flozo.data;

public class DoubleTypeValue implements GenericTypeValue {

    private final Double value;

    public DoubleTypeValue(Double value) {
        this.value = value;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public double getNumber() {
        return value;
    }

    @Override
    public String valueDescription() {
        if (value == null) {
            return "Double value is null.";
        }
        return "Value is a Double.";
    }
}
