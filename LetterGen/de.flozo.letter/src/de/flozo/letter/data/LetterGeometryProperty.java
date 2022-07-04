package de.flozo.letter.data;

import java.util.Objects;

public enum LetterGeometryProperty implements Property {

    PAPER_WIDTH("paper.width", 21.0),
    PAPER_HEIGHT("paper.height", 29.7),

    BORDER_MARGIN_TOP("border_margin.top", 2.0),
    BORDER_MARGIN_BOTTOM("border_margin.bottom", 2.0),
    BORDER_MARGIN_LEFT("border_margin.left", 2.5),
    BORDER_MARGIN_RIGHT("border_margin.right", 2.0),

    ADDRESS_X("address.x", 2.5),
    ADDRESS_Y("address.y", 23.43),
    ADDRESS_WIDTH("address.width", 9.0),
    ADDRESS_HEIGHT("address.height", 4.5),

    BACKADDRESS_X("backaddress.x", 2.0),
    BACKADDRESS_Y("backaddress.y", 23.43),
    BACKADDRESS_WIDTH("backaddress.width", 9.0),
    BACKADDRESS_HEIGHT("backaddress.height", 1.0),
    BACKADDRESS_SEPLINE_X("backaddress.sepline.x", 2.0),
    BACKADDRESS_SEPLINE_LINEWIDTH("backaddress.sepline.line_width", 0.5),
    BACKADDRESS_SEPCHAR("backaddress.sepchar", "$\\bullet$"),
    BACKADDRESS_SEPCHAR_SPACING("backaddress.sepchar.spacing", 8.0),

    SENDER_X("sender.x", 19.0),
    SENDER_Y("sender.y", 27.55),
    SENDER_WIDTH("sender.width", 20.7),
    SENDER_HEIGHT("sender.height", 20.7),

    SUBJECT_Y("subject.y", 18.5),
    DATE_Y("date.y", 19.2),
    BODY_Y("body.y", 17.8),
    BODY_TEXT_PARAGRAPH_SPACING("body.text.paragraph_spacing", -0.2),
    CLOSING_Y_SHIFT("closing.y_shift", 3.7),
    SIGNATURE_Y("signature.y", 5.0),
    SIGNATURE_IMAGE_SCALE_FACTOR("signature.image.scale.factor", 1.0),
    ENCLOSURES_Y("enclosures.y", 5.0),

    HEADLINE_X_SHIFT("headline.x_shift", 0.0),
    HEADLINE_Y_SHIFT("headline.y_shift", 0.2),
    HEADLINE_SEPLINE_LINE_WIDTH("headline.sepline.line_width", 3.0),

    PERFORATION_MARK_X("perforation_mark.x", 0.1),
    PERFORATION_MARK_Y("perforation_mark.y", 14.85),
    PERFORATION_MARK_LENGTH("perforation_mark.length", 0.5),
    PERFORATION_MARK_LINE_WIDTH("perforation_mark.line_width", 0.3),
    FOLDING_MARK_1_X("folding_mark_1.x", 0.1),
    FOLDING_MARK_1_Y("folding_mark_1.y", 19.2),
    FOLDING_MARK_1_LENGTH("folding_mark_1.length", 0.25),
    FOLDING_MARK_1_LINE_WIDTH("folding_mark_1.line_width", 0.3),
    FOLDING_MARK_2_X("folding_mark_2.x", 0.1),
    FOLDING_MARK_2_Y("folding_mark_2.y", 8.7),
    FOLDING_MARK_2_LENGTH("folding_mark_2.length", 0.25),
    FOLDING_MARK_2_LINE_WIDTH("folding_mark_2.line_width", 0.3);

    private final String property;
    private final double numericalValue;
    private final String stringValue;

    LetterGeometryProperty(String property, double numericalValue) {
        this.property = property;
        this.numericalValue = numericalValue;
        this.stringValue = "";
    }

    LetterGeometryProperty(String property, String stringValue) {
        this.property = property;
        this.numericalValue = 0.0;
        this.stringValue = stringValue;
    }

    @Override
    public String getPropertyKey() {
        return property;
    }

    public double getNumericalValue() {
        return numericalValue;
    }

    public String getStringValue() {
        return stringValue;
    }


    @Override
    public String getGenericStringValue() {
        return Objects.equals(stringValue, "") ? String.valueOf(numericalValue) : stringValue;
    }

    @Override
    public String getEntry() {
        return property + " = " + getGenericStringValue();
    }


    @Override
    public String toString() {
        return "LetterGeometryProperty{" +
                "property='" + property + '\'' +
                ", numericalValue=" + numericalValue +
                ", stringValue='" + stringValue + '\'' +
                '}';
    }
}
