package de.flozo.letter;

import de.flozo.data.LetterColor;
import de.flozo.data.LetterFont;
import de.flozo.data.LetterGeneral;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.FontSize;
import de.flozo.latex.color.Color;
import de.flozo.latex.color.StandardColor;
import de.flozo.latex.tikz.options.Anchor;
import de.flozo.latex.tikz.commands.Node;
import de.flozo.latex.tikz.commands.Point;

import java.util.Map;

public class Enclosure {

    public static final String FIELD_NAME = "enclosures_field";

    // content
    private final Map<String, String> enclosureDocuments;
    private final int numberOfDocuments;
    private final String enclosureTagSingular;
    private final String enclosureTagPlural;

    // appearance
    private final Point position;
    private final Color backgroundColor;
    private final Color borderColor;
    private final Color textColor;
    private final FontSize fontSize;
    private final boolean enclosureStyleHide;
    private final boolean enclosureStyleShowTag;
    private final boolean enclosureStyleShowNumber;
    private final boolean enclosureStyleShowTitles;
    private final String enclosureStyleTagSeparator;
    private final String enclosureStyleTitleSeparator;


    public Enclosure(LetterGeneral general, LetterGeometry geometry, LetterColor color, LetterFont font, Map<String, String> enclosureDocuments) {
        this.position = Point.fromNumbers(geometry.getBorderMarginLeft(), geometry.getEnclosuresY());
        this.enclosureDocuments = enclosureDocuments;
        this.numberOfDocuments = enclosureDocuments.size();
        this.enclosureTagSingular = "Enclosure";
        this.enclosureTagPlural = "Enclosures";
        this.backgroundColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBackgroundColor(): StandardColor.NONE;
        this.borderColor = general.isDraftModeOn() ? color.getDraftModeHighlightingBorderColor(): StandardColor.DEFAULT;
        this.textColor = color.getEnclosuresTextColor();
        this.fontSize = font.getEnclosuresFontSize();
        this.enclosureStyleHide = general.isEnclosureStyleHide();
        this.enclosureStyleShowTag = general.isEnclosureStyleShowTag();
        this.enclosureStyleShowNumber = general.isEnclosureStyleShowNumber();
        this.enclosureStyleShowTitles = general.isEnclosureStyleShowTitles();
        this.enclosureStyleTagSeparator = general.getEnclosureStyleTagSeparator();
        this.enclosureStyleTitleSeparator = general.getEnclosureStyleTitleSeparator();
    }

    public String generate() {
        return new Node.Builder(assembleEnclosureTag())
                .name(FIELD_NAME)
                .position(position)
                .anchor(Anchor.NORTH_WEST)
                .fillColor(backgroundColor)
                .drawColor(borderColor)
                .textColor(textColor)
                .fontSize(fontSize)
                .build().getInline();
    }

    private String assembleEnclosureTag() {
        if (enclosureStyleHide) {
            return "";
        }
        StringBuilder tag = new StringBuilder();
        if (enclosureStyleShowTag) {
            tag.append(numberOfDocuments == 1 ? enclosureTagSingular : enclosureTagPlural);
        }
        if (enclosureStyleShowNumber) {
            tag.append(" (").append(numberOfDocuments).append(")");
        }
        if (enclosureStyleShowTitles) {
            tag.append(enclosureStyleShowTag || enclosureStyleShowNumber ? enclosureStyleTagSeparator + " " : "");
            tag.append(String.join(enclosureStyleTitleSeparator + " ", enclosureDocuments.keySet()));
        }
        return tag.toString();
    }


    @Override
    public String toString() {
        return "Enclosure{" +
                "enclosureDocuments=" + enclosureDocuments +
                ", numberOfDocuments=" + numberOfDocuments +
                ", enclosureTagSingular='" + enclosureTagSingular + '\'' +
                ", enclosureTagPlural='" + enclosureTagPlural + '\'' +
                ", position=" + position +
                ", backgroundColor=" + backgroundColor +
                ", borderColor=" + borderColor +
                ", textColor=" + textColor +
                ", fontSize=" + fontSize +
                ", enclosureStyleHide=" + enclosureStyleHide +
                ", enclosureStyleShowTag=" + enclosureStyleShowTag +
                ", enclosureStyleShowNumber=" + enclosureStyleShowNumber +
                ", enclosureStyleShowTitles=" + enclosureStyleShowTitles +
                ", enclosureStyleTagSeparator='" + enclosureStyleTagSeparator + '\'' +
                ", enclosureStyleTitleSeparator='" + enclosureStyleTitleSeparator + '\'' +
                '}';
    }
}
