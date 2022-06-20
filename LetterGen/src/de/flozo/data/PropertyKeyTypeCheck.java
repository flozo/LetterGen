package de.flozo.data;

import java.util.function.Predicate;

public interface PropertyKeyTypeCheck {

    static Predicate<String> numericEntryCondition() {
        Predicate<String> isWidth = key -> key.endsWith(".width");
        Predicate<String> isHeight = key -> key.endsWith(".height");
        Predicate<String> isLength = key -> key.endsWith(".length");
        Predicate<String> isLineWidth = key -> key.endsWith(".line_width");
        Predicate<String> isSpacing = key -> key.endsWith(".spacing");
        Predicate<String> isX = key -> key.endsWith(".x");
        Predicate<String> isY = key -> key.endsWith(".y");
        Predicate<String> isXShift = key -> key.endsWith(".x_shift");
        Predicate<String> isYShift = key -> key.endsWith(".y_shift");
        Predicate<String> isBorderMargin = key -> key.startsWith("border_margin");
        return isWidth.or(isHeight).or(isLength).or(isLineWidth).or(isSpacing).or(isX).or(isY).or(isXShift).or(isYShift).or(isBorderMargin);
    }

    static Predicate<String> booleanEntryCondition() {
        return key -> key.endsWith(".on");
    }

    static Predicate<String> colorEntryCondition() {
        return key -> key.endsWith(".color");
    }

}
