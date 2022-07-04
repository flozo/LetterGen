package de.flozo.letter.latex;

import de.flozo.letter.data.LetterColor;
import de.flozo.letter.data.LetterGeneral;
import de.flozo.letter.data.LetterGeometry;
import de.flozo.latex.core.Length;
import de.flozo.latex.color.Color;
import de.flozo.latex.color.StandardColor;
import de.flozo.latex.tikz.commands.LayerEnvironment;
import de.flozo.latex.tikz.commands.Line;
import de.flozo.latex.tikz.commands.Point;
import de.flozo.latex.tikz.commands.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private final Length totalWidth;
    private final Length totalHeight;
    private final Length marginTop;
    private final Length marginBottom;
    private final Length marginLeft;
    private final Length marginRight;
    private final Length borderRight;
    private final Length borderTop;
    private final Length innerWidth;
    private final Length innerHeight;
//    private final Point innerCornerTopLeft;
//    private final Point innerCornerTopRight;
//    private final Point innerCornerBottomLeft;
//    private final Point innerCornerBottomRight;

    private final Color backgroundColor;
    private final Color draftHighlightColor;
    private final Color draftBorderColor;
    private final Color urlHyperlinkColor;
    private final boolean inDraftMode;


    // Constructor with dependency injection
    public Page(LetterGeneral general, LetterGeometry geometry, LetterColor color) {
        this.totalWidth = Length.inCentimeter(geometry.getPaperWidth());
        this.totalHeight = Length.inCentimeter(geometry.getPaperHeight());
        this.marginTop = Length.inCentimeter(geometry.getBorderMarginTop());
        this.marginBottom = Length.inCentimeter(geometry.getBorderMarginBottom());
        this.marginLeft = Length.inCentimeter(geometry.getBorderMarginLeft());
        this.marginRight = Length.inCentimeter(geometry.getBorderMarginRight());
        this.borderRight = Length.inCentimeter(geometry.getPaperWidth() - geometry.getBorderMarginRight());
        this.borderTop = Length.inCentimeter(geometry.getPaperHeight() - geometry.getBorderMarginTop());
        this.innerWidth = Length.inCentimeter(borderRight.getNumericalValue() - geometry.getBorderMarginLeft());
        this.innerHeight = Length.inCentimeter(borderTop.getNumericalValue() - geometry.getBorderMarginBottom());
//        this.innerCornerTopLeft = Point.fromLengths(marginLeft, innerHeight);
//        this.innerCornerTopRight = Point.fromLengths(borderRight, innerHeight);
//        this.innerCornerBottomLeft = Point.fromLengths(marginLeft, marginBottom);
//        this.innerCornerBottomRight = Point.fromLengths(borderRight, marginBottom);
        this.backgroundColor = color.getBackgroundColor();
        this.draftBorderColor = color.getDraftModeHighlightingBorderColor();
        this.draftHighlightColor = color.getDraftModeHighlightingBackgroundColor();
        this.urlHyperlinkColor = color.getUrlHyperlinkColor();
        this.inDraftMode = general.isDraftModeOn();
    }

    public List<String> getPage() {
        List<String> page = new ArrayList<>();
        page.add(getBackgroundRectangle().getInline());
        if (inDraftMode) {
            page.addAll(getBorderMargins());
        }
        LayerEnvironment onBackgroundLayer = new LayerEnvironment("background", page);
        return onBackgroundLayer.getBlock();
    }


    private List<String> getBorderMargins() {
        Line top = new Line.Builder(Point.fromLengths(Length.inCentimeter(0.0), borderTop), Point.fromLengths(totalWidth, borderTop))
                .drawColor(draftBorderColor)
                .build();
        Line bottom = new Line.Builder(Point.fromLengths(Length.inCentimeter(0.0), marginBottom), Point.fromLengths(totalWidth, marginBottom))
                .drawColor(draftBorderColor)
                .build();
        Line left = new Line.Builder(Point.fromLengths(marginLeft, Length.inCentimeter(0.0)), Point.fromLengths(marginLeft, totalHeight))
                .drawColor(draftBorderColor)
                .build();
        Line right = new Line.Builder(Point.fromLengths(borderRight, Length.inCentimeter(0.0)), Point.fromLengths(borderRight, totalHeight))
                .drawColor(draftBorderColor)
                .build();
        return new ArrayList<>(List.of(top.getInline(), bottom.getInline(), left.getInline(), right.getInline()));
    }

    private Rectangle getBackgroundRectangle() {
        return new Rectangle.Builder(0, 0, totalWidth.getNumericalValue(), totalHeight.getNumericalValue())
                .fillColor(backgroundColor)
                .drawColor(StandardColor.NONE)
                .skipLastTerminator(true)
                .build();
    }


    public Length getTotalWidth() {
        return totalWidth;
    }

    public Length getTotalHeight() {
        return totalHeight;
    }

    public Length getMarginTop() {
        return marginTop;
    }

    public Length getMarginBottom() {
        return marginBottom;
    }

    public Length getMarginLeft() {
        return marginLeft;
    }

    public Length getMarginRight() {
        return marginRight;
    }

    public Length getBorderRight() {
        return borderRight;
    }

    public Length getBorderTop() {
        return borderTop;
    }

    public Length getInnerWidth() {
        return innerWidth;
    }

    public Length getInnerHeight() {
        return innerHeight;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getDraftHighlightColor() {
        return draftHighlightColor;
    }

    public Color getDraftBorderColor() {
        return draftBorderColor;
    }

    public Color getUrlHyperlinkColor() {
        return urlHyperlinkColor;
    }

    public boolean isInDraftMode() {
        return inDraftMode;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalWidth=" + totalWidth +
                ", totalHeight=" + totalHeight +
                ", marginTop=" + marginTop +
                ", marginBottom=" + marginBottom +
                ", marginLeft=" + marginLeft +
                ", marginRight=" + marginRight +
                ", borderRight=" + borderRight +
                ", borderTop=" + borderTop +
                ", innerWidth=" + innerWidth +
                ", innerHeight=" + innerHeight +
                ", backgroundColor=" + backgroundColor +
                ", draftHighlightColor=" + draftHighlightColor +
                ", draftBorderColor=" + draftBorderColor +
                ", urlHyperlinkColor=" + urlHyperlinkColor +
                ", inDraftMode=" + inDraftMode +
                '}';
    }
}
