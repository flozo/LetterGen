package de.flozo.data;

public class StringTypeValue implements GenericTypeValue {

    private final String value;

    public StringTypeValue(String value) {
        this.value = value;
    }

    @Override
    public String getString() {
        return value;
    }

    @Override
    public double getNumber() {
        return 0;
    }

    @Override
    public String valueDescription() {
        if (value == null) {
            return "String value is null.";
        }
        return "Value is a String.";
    }
}
