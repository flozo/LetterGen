package de.flozo.latex.letter;

import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.tikz.Anchor;
import de.flozo.latex.tikz.Node;
import de.flozo.latex.tikz.Point;

import java.util.Map;

public class Enclosure {

    public static final String FIELD_NAME = "enclosures_field";

    public final Point position;

    private final Map<String, String> enclosureDocuments;
    private final int numberOfDocuments;
    private final String enclosureTagSingular;
    private final String enclosureTagPlural;

    private final Color backgroundColor;
    private final Color borderColor;
    private final Color textColor;

    public Enclosure(LetterGeometry geometry, LetterColor color, Map<String, String> enclosureDocuments) {
        this.position = Point.fromNumbers(geometry.getBorderMarginLeft(), geometry.getEnclosuresY());
        this.enclosureDocuments = enclosureDocuments;
        this.numberOfDocuments = enclosureDocuments.size();
        this.enclosureTagSingular = "Enclosure";
        this.enclosureTagPlural = "Enclosures";
        this.backgroundColor = color.getDraftModeHighlightingBackgroundColor();
        this.borderColor = color.getDraftModeHighlightingBorderColor();
        this.textColor = color.getEnclosuresTextColor();
    }

    public String generate() {
        return new Node.Builder(assembleEnclosureTag())
                .name(FIELD_NAME)
                .position(position)
                .anchor(Anchor.NORTH_WEST)
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .build().getInline();
    }

    private String assembleEnclosureTag() {
        return numberOfDocuments == 1 ? enclosureTagSingular : enclosureTagPlural + " (" + numberOfDocuments + "): " + String.join(", ", enclosureDocuments.keySet());
    }


    @Override
    public String toString() {
        return "Enclosure{" +
                "position=" + position +
                ", enclosureDocuments=" + enclosureDocuments +
                ", numberOfDocuments=" + numberOfDocuments +
                ", enclosureTagSingular='" + enclosureTagSingular + '\'' +
                ", enclosureTagPlural='" + enclosureTagPlural + '\'' +
                ", backgroundColor=" + backgroundColor +
                ", borderColor=" + borderColor +
                ", textColor=" + textColor +
                '}';
    }
}
