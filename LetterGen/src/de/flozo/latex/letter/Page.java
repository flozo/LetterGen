package de.flozo.latex.letter;

import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.color.Color;

public class Page {

    private final double width;
    private final double height;
    private final double marginTop;
    private final double marginBottom;
    private final double marginLeft;
    private final double marginRight;
    private final Color backgroundColor;
    private final Color draftHighlightColor;
    private final Color urlHyperlinkColor;


    // Constructor with dependency injection
    public Page(LetterGeometry geometry, LetterColor color) {
        this.width = geometry.getPaperWidth();
        this.height = geometry.getPaperHeight();
        this.marginTop = geometry.getBorderMarginTop();
        this.marginBottom = geometry.getBorderMarginBottom();
        this.marginLeft = geometry.getBorderMarginLeft();
        this.marginRight = geometry.getBorderMarginRight();
        this.backgroundColor = color.getBackgroundColor();
        this.draftHighlightColor = color.getDraftModeHighlightingBackgroundColor();
        this.urlHyperlinkColor = color.getUrlHyperlinkColor();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getMarginTop() {
        return marginTop;
    }

    public double getMarginBottom() {
        return marginBottom;
    }

    public double getMarginLeft() {
        return marginLeft;
    }

    public double getMarginRight() {
        return marginRight;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getDraftHighlightColor() {
        return draftHighlightColor;
    }

    public Color getUrlHyperlinkColor() {
        return urlHyperlinkColor;
    }

    @Override
    public String toString() {
        return "Page{" +
                "width=" + width +
                ", height=" + height +
                ", marginTop=" + marginTop +
                ", marginBottom=" + marginBottom +
                ", marginLeft=" + marginLeft +
                ", marginRight=" + marginRight +
                ", backgroundColor=" + backgroundColor +
                ", draftHighlightColor=" + draftHighlightColor +
                ", urlHyperlinkColor=" + urlHyperlinkColor +
                '}';
    }
}
