package de.flozo.latex.letter;

import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.color.Color;
import de.flozo.latex.core.color.StandardColor;
import de.flozo.latex.tikz.LayerEnvironment;
import de.flozo.latex.tikz.Line;
import de.flozo.latex.tikz.Point;
import de.flozo.latex.tikz.Rectangle;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getPage() {
        List<String> page = new ArrayList<>();
        page.add(getBackgroundRectangle().getInline());
        page.addAll(getBorderMargins());
        LayerEnvironment onBackgroundLayer = new LayerEnvironment("background", page);
        return onBackgroundLayer.getBlock();
    }


    private List<String> getBorderMargins() {
        Line top = new Line.Builder(Point.fromNumbers(0.0, height - marginTop), Point.fromNumbers(width, height - marginTop)).build();
        Line bottom = new Line.Builder(Point.fromNumbers(0.0, marginBottom), Point.fromNumbers(width, marginBottom)).build();
        Line left = new Line.Builder(Point.fromNumbers(marginLeft, 0), Point.fromNumbers(marginLeft, height)).build();
        Line right = new Line.Builder(Point.fromNumbers(width - marginRight, 0), Point.fromNumbers(width - marginRight, height)).build();
        return new ArrayList<>(List.of(top.getInline(), bottom.getInline(), left.getInline(), right.getInline()));
    }

    private Rectangle getBackgroundRectangle() {
        return new Rectangle.Builder(0, 0, width, height)
                .fillColor(backgroundColor)
                .drawColor(StandardColor.NONE)
                .skipLastTerminator(true)
                .build();
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
