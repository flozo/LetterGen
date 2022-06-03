package de.flozo.latex.letter;

import de.flozo.data.LetterGeometry;

public class Page {

    private final double width;
    private final double height;
    private final double marginTop;
    private final double marginBottom;
    private final double marginLeft;
    private final double marginRight;
    private final String backgroundColor;
    private final String draftHighlightColor;
    private final String urlHyperlinkColor;

    public Page(LetterGeometry geometry) {
        this.width = geometry.getPaperWidth();
        this.height = geometry.getPaperHeight();
        this.marginTop = geometry.getBorderMarginTop();
        this.marginBottom = geometry.getBorderMarginBottom();
        this.marginLeft = geometry.getBorderMarginLeft();
        this.marginRight = geometry.getBorderMarginRight();
        this.backgroundColor = geometry.getBackgroundColor();
        this.draftHighlightColor = geometry.getDraftModeHighlightColor();
        this.urlHyperlinkColor = geometry.getUrlHyperlinkColor();
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

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getDraftHighlightColor() {
        return draftHighlightColor;
    }

    public String getUrlHyperlinkColor() {
        return urlHyperlinkColor;
    }
}
