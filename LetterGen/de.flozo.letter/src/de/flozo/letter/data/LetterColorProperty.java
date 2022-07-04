package de.flozo.letter.data;


import de.flozo.latex.color.*;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum LetterColorProperty implements Property {

    BACKGROUND_COLOR("background.color", StandardColor.NONE),
    DRAFT_MODE_HIGHLIGHTING_BACKGROUND_COLOR("draft_mode_highlighting.background.color", BrewerColor.compose(SequentialScheme.ORANGES, SequentialLetter.D)),
    DRAFT_MODE_HIGHLIGHTING_BORDER_COLOR("draft_mode_highlighting.border.color", BrewerColor.compose(SequentialScheme.GREYS, SequentialLetter.G)),
    URL_COLOR("url_hyperlink.color", BrewerColor.compose(SequentialScheme.BLUES, SequentialLetter.M)),

    HEADLINE_TEXT_COLOR("headline.text.color", StandardColor.DEFAULT),
    HEADLINE_SEPLINE_COLOR("headline.sepline.color", StandardColor.DEFAULT),

    ADDRESS_TEXT_COLOR("address.text.color", StandardColor.DEFAULT),
    BACKADDRESS_TEXT_COLOR("backaddress.text.color", StandardColor.DEFAULT),
    BACKADDRESS_SEPLINE_COLOR("backaddress.sepline.color", StandardColor.DEFAULT),
    SENDER_TEXT_COLOR("sender.text.color", StandardColor.DEFAULT),
    SENDER_ICON_COLOR("sender.icon.color", StandardColor.DEFAULT),

    BODY_TEXT_COLOR("body.text.color", StandardColor.DEFAULT),
    SUBJECT_TEXT_COLOR("subject.text.color", StandardColor.DEFAULT),
    DATE_TEXT_COLOR("date.text.color", StandardColor.DEFAULT),
    CLOSING_TEXT_COLOR("closing.text.color", StandardColor.DEFAULT),
    SIGNATURE_TEXT_COLOR("signature.text.color", StandardColor.DEFAULT),
    ENCLOSURES_TEXT_COLOR("enclosures.text.color", StandardColor.DEFAULT),

    PERFORATION_MARK_COLOR("perforation_mark.color", StandardColor.DEFAULT),
    FOLDING_MARK_1_COLOR("folding_mark_1.color", StandardColor.DEFAULT),
    FOLDING_MARK_2_COLOR("folding_mark_2.color", StandardColor.DEFAULT);


    private final String property;
    private final Color colorValue;

    LetterColorProperty(String property, Color colorValue) {
        this.property = property;
        this.colorValue = colorValue;
    }

    public Color getColorValue() {
        return colorValue;
    }

    @Override
    public String getGenericStringValue() {
        return colorValue.getString();

    }

    @Override
    public String getPropertyKey() {
        return property;
    }

    @Override
    public String getEntry() {
        return property + " = " + colorValue.getString();
    }

    private static final Map<String, LetterColorProperty> stringToEnum = Stream.of(values()).collect(toMap(LetterColorProperty::getPropertyKey, Function.identity()));

    public static Optional<LetterColorProperty> fromString(String stringValue) {
        return Optional.ofNullable(stringToEnum.get(stringValue));
    }

    @Override
    public String toString() {
        return "LetterColorProperty{" +
                "property='" + property + '\'' +
                ", colorValue=" + colorValue +
                '}';
    }
}
