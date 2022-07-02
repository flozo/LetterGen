package de.flozo.data;

import java.util.function.Predicate;

public interface PropertyKeyTypeCheck {

    static Predicate<String> numericEntryCondition() {
        Predicate<String> isWidth = key -> key.endsWith(".width");
        Predicate<String> isHeight = key -> key.endsWith(".height");
        Predicate<String> isLength = key -> key.endsWith(".length");
        Predicate<String> isLineWidth = key -> key.endsWith(".line_width");
        Predicate<String> isSpacing = key -> key.endsWith("spacing");
        Predicate<String> isX = key -> key.endsWith(".x");
        Predicate<String> isY = key -> key.endsWith(".y");
        Predicate<String> isXShift = key -> key.endsWith(".x_shift");
        Predicate<String> isYShift = key -> key.endsWith(".y_shift");
        Predicate<String> isBorderMargin = key -> key.startsWith("border_margin");
        Predicate<String> isFactor = key -> key.endsWith(".factor");
        return isWidth.or(isHeight).or(isLength).or(isLineWidth).or(isSpacing).or(isX).or(isY).or(isXShift).or(isYShift).or(isBorderMargin).or(isFactor);
    }

    static Predicate<String> booleanEntryCondition() {
        Predicate<String> on = key -> key.endsWith(".on");
        Predicate<String> hide = key -> key.endsWith(".hide");
        Predicate<String> show = key -> key.contains(".show");
        return on.or(hide).or(show);
    }

    static Predicate<String> colorEntryCondition() {
        return key -> key.endsWith(".color");
    }

    static Predicate<String> fontSizeEntryCondition() {
        return key -> key.endsWith(".fontsize");
    }

}
