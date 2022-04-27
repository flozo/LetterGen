package main;

public class Page {

    private double width;
    private double height;
    private double marginTop;
    private double marginBottom;
    private double marginLeft;
    private double marginRight;
    private String backgroundColor;
    private boolean inDraftMode;
    private String draftHighlightColor;

    public Page(double width, double height, double marginTop, double marginBottom, double marginLeft, double marginRight, String backgroundColor, boolean inDraftMode, String draftHighlightColor) {
        this.width = width;
        this.height = height;
        this.marginTop = marginTop;
        this.marginBottom = marginBottom;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.backgroundColor = backgroundColor;
        this.inDraftMode = inDraftMode;
        this.draftHighlightColor = draftHighlightColor;
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

    public boolean isInDraftMode() {
        return inDraftMode;
    }

    public String getDraftHighlightColor() {
        return draftHighlightColor;
    }
}
