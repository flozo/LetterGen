package de.flozo.data;

import java.util.Map;

public class LetterGeometry {

    private final double paperWidth;
    private final double paperHeight;

    private final double borderMarginTop;
    private final double borderMarginBottom;
    private final double borderMarginLeft;
    private final double borderMarginRight;

    private final double addressX;
    private final double addressY;
    private final double addressWidth;
    private final double addressHeight;

    private final double backaddressX;
    private final double backaddressY;
    private final double backaddressWidth;
    private final double backaddressHeight;
    private final double backaddressSeplineX;
    private final double backaddressSeplineLineWidth;
    private final String backaddressSepChar;
    private final double backaddressSepCharSpacing;
    private final String backaddressFontSize;

    private final double senderX;
    private final double senderY;
    private final double senderWidth;
    private final double senderHeight;

    private final double subjectY;
    private final double textY;
    private final double closingYShift;
    private final double enclosuresYShift;
    private final double headlineXShift;
    private final double headlineYShift;

    private final double perforationMarkX;
    private final double perforationMarkY;
    private final double perforationMarkLength;
    private final double perforationMarkLineWidth;


    private final double foldingMark1X;
    private final double foldingMark1Y;
    private final double foldingMark1Length;
    private final double foldingMark1LineWidth;

    private final double foldingMark2X;
    private final double foldingMark2Y;
    private final double foldingMark2Length;
    private final double foldingMark2LineWidth;


    public LetterGeometry(PropertyMap propertyMap) {
        Map<String, String> rawMap = propertyMap.getProperties();
        Map<String, String> stringSubMap = propertyMap.stringSubMap(rawMap);
        Map<String, Double> numericSubMap = propertyMap.numericSubMap(rawMap);
        this.paperWidth = numericSubMap.get(LetterGeometryProperty.PAPER_WIDTH.getPropertyKey());
        this.paperHeight = numericSubMap.get(LetterGeometryProperty.PAPER_HEIGHT.getPropertyKey());
        this.borderMarginTop = numericSubMap.get(LetterGeometryProperty.BORDER_MARGIN_TOP.getPropertyKey());
        this.borderMarginBottom = numericSubMap.get(LetterGeometryProperty.BORDER_MARGIN_BOTTOM.getPropertyKey());
        this.borderMarginLeft = numericSubMap.get(LetterGeometryProperty.BORDER_MARGIN_LEFT.getPropertyKey());
        this.borderMarginRight = numericSubMap.get(LetterGeometryProperty.BORDER_MARGIN_RIGHT.getPropertyKey());
        this.addressX = numericSubMap.get(LetterGeometryProperty.ADDRESS_X.getPropertyKey());
        this.addressY = numericSubMap.get(LetterGeometryProperty.ADDRESS_Y.getPropertyKey());
        this.addressWidth = numericSubMap.get(LetterGeometryProperty.ADDRESS_WIDTH.getPropertyKey());
        this.addressHeight = numericSubMap.get(LetterGeometryProperty.ADDRESS_HEIGHT.getPropertyKey());
        this.backaddressX = numericSubMap.get(LetterGeometryProperty.BACKADDRESS_X.getPropertyKey());
        this.backaddressY = numericSubMap.get(LetterGeometryProperty.BACKADDRESS_Y.getPropertyKey());
        this.backaddressWidth = numericSubMap.get(LetterGeometryProperty.BACKADDRESS_WIDTH.getPropertyKey());
        this.backaddressHeight = numericSubMap.get(LetterGeometryProperty.BACKADDRESS_HEIGHT.getPropertyKey());
        this.backaddressSeplineX = numericSubMap.get(LetterGeometryProperty.BACKADDRESS_SEPLINE_X.getPropertyKey());
        this.backaddressSeplineLineWidth = numericSubMap.get(LetterGeometryProperty.BACKADDRESS_SEPLINE_LINEWIDTH.getPropertyKey());
        this.backaddressSepChar = stringSubMap.get(LetterGeometryProperty.BACKADDRESS_SEPCHAR.getPropertyKey());
        this.backaddressSepCharSpacing = numericSubMap.get(LetterGeometryProperty.BACKADDRESS_SEPCHAR_SPACING.getPropertyKey());
        this.backaddressFontSize = stringSubMap.get(LetterGeometryProperty.BACKADDRESS_FONTSIZE.getPropertyKey());
        this.senderX = numericSubMap.get(LetterGeometryProperty.SENDER_X.getPropertyKey());
        this.senderY = numericSubMap.get(LetterGeometryProperty.SENDER_Y.getPropertyKey());
        this.senderWidth = numericSubMap.get(LetterGeometryProperty.SENDER_WIDTH.getPropertyKey());
        this.senderHeight = numericSubMap.get(LetterGeometryProperty.SENDER_HEIGHT.getPropertyKey());
        this.subjectY = numericSubMap.get(LetterGeometryProperty.SUBJECT_Y.getPropertyKey());
        this.textY = numericSubMap.get(LetterGeometryProperty.TEXT_Y.getPropertyKey());
        this.closingYShift = numericSubMap.get(LetterGeometryProperty.CLOSING_Y_SHIFT.getPropertyKey());
        this.enclosuresYShift = numericSubMap.get(LetterGeometryProperty.ENCLOSURES_Y_SHIFT.getPropertyKey());
        this.headlineXShift = numericSubMap.get(LetterGeometryProperty.HEADLINE_X_SHIFT.getPropertyKey());
        this.headlineYShift = numericSubMap.get(LetterGeometryProperty.HEADLINE_Y_SHIFT.getPropertyKey());
        this.perforationMarkX = numericSubMap.get(LetterGeometryProperty.PERFORATION_MARK_X.getPropertyKey());
        this.perforationMarkY = numericSubMap.get(LetterGeometryProperty.PERFORATION_MARK_Y.getPropertyKey());
        this.perforationMarkLength = numericSubMap.get(LetterGeometryProperty.PERFORATION_MARK_LENGTH.getPropertyKey());
        this.perforationMarkLineWidth = numericSubMap.get(LetterGeometryProperty.PERFORATION_MARK_LINE_WIDTH.getPropertyKey());
        this.foldingMark1X = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_1_X.getPropertyKey());
        this.foldingMark1Y = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_1_Y.getPropertyKey());
        this.foldingMark1Length = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_1_LENGTH.getPropertyKey());
        this.foldingMark1LineWidth = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_1_LINE_WIDTH.getPropertyKey());
        this.foldingMark2X = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_2_X.getPropertyKey());
        this.foldingMark2Y = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_2_Y.getPropertyKey());
        this.foldingMark2Length = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_2_LENGTH.getPropertyKey());
        this.foldingMark2LineWidth = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_2_LINE_WIDTH.getPropertyKey());
    }

    public double getPaperWidth() {
        return paperWidth;
    }

    public double getPaperHeight() {
        return paperHeight;
    }

    public double getBorderMarginTop() {
        return borderMarginTop;
    }

    public double getBorderMarginBottom() {
        return borderMarginBottom;
    }

    public double getBorderMarginLeft() {
        return borderMarginLeft;
    }

    public double getBorderMarginRight() {
        return borderMarginRight;
    }

    public double getAddressX() {
        return addressX;
    }

    public double getAddressY() {
        return addressY;
    }

    public double getAddressWidth() {
        return addressWidth;
    }

    public double getAddressHeight() {
        return addressHeight;
    }

    public double getBackaddressX() {
        return backaddressX;
    }

    public double getBackaddressY() {
        return backaddressY;
    }

    public double getBackaddressWidth() {
        return backaddressWidth;
    }

    public double getBackaddressHeight() {
        return backaddressHeight;
    }

    public double getBackaddressSeplineX() {
        return backaddressSeplineX;
    }

    public double getBackaddressSeplineLineWidth() {
        return backaddressSeplineLineWidth;
    }

    public String getBackaddressSepChar() {
        return backaddressSepChar;
    }

    public double getBackaddressSepCharSpacing() {
        return backaddressSepCharSpacing;
    }

    public String getBackaddressFontSize() {
        return backaddressFontSize;
    }

    public double getSenderX() {
        return senderX;
    }

    public double getSenderY() {
        return senderY;
    }

    public double getSenderWidth() {
        return senderWidth;
    }

    public double getSenderHeight() {
        return senderHeight;
    }

    public double getSubjectY() {
        return subjectY;
    }

    public double getTextY() {
        return textY;
    }

    public double getClosingYShift() {
        return closingYShift;
    }

    public double getEnclosuresYShift() {
        return enclosuresYShift;
    }

    public double getHeadlineXShift() {
        return headlineXShift;
    }

    public double getHeadlineYShift() {
        return headlineYShift;
    }

    public double getPerforationMarkX() {
        return perforationMarkX;
    }

    public double getPerforationMarkY() {
        return perforationMarkY;
    }

    public double getPerforationMarkLength() {
        return perforationMarkLength;
    }

    public double getPerforationMarkLineWidth() {
        return perforationMarkLineWidth;
    }

    public double getFoldingMark1X() {
        return foldingMark1X;
    }

    public double getFoldingMark1Y() {
        return foldingMark1Y;
    }

    public double getFoldingMark1Length() {
        return foldingMark1Length;
    }

    public double getFoldingMark1LineWidth() {
        return foldingMark1LineWidth;
    }

    public double getFoldingMark2X() {
        return foldingMark2X;
    }

    public double getFoldingMark2Y() {
        return foldingMark2Y;
    }

    public double getFoldingMark2Length() {
        return foldingMark2Length;
    }

    public double getFoldingMark2LineWidth() {
        return foldingMark2LineWidth;
    }

    @Override
    public String toString() {
        return "LetterGeometry{" +
                "paperWidth=" + paperWidth +
                ", paperHeight=" + paperHeight +
                ", borderMarginTop=" + borderMarginTop +
                ", borderMarginBottom=" + borderMarginBottom +
                ", borderMarginLeft=" + borderMarginLeft +
                ", borderMarginRight=" + borderMarginRight +
                ", addressX=" + addressX +
                ", addressY=" + addressY +
                ", addressWidth=" + addressWidth +
                ", addressHeight=" + addressHeight +
                ", backaddressX=" + backaddressX +
                ", backaddressY=" + backaddressY +
                ", backaddressWidth=" + backaddressWidth +
                ", backaddressHeight=" + backaddressHeight +
                ", backaddressSeplineX=" + backaddressSeplineX +
                ", backaddressSeplineLineWidth=" + backaddressSeplineLineWidth +
                ", backaddressSepChar='" + backaddressSepChar + '\'' +
                ", backaddressSepCharSpacing=" + backaddressSepCharSpacing +
                ", backaddressFontSize='" + backaddressFontSize + '\'' +
                ", senderX=" + senderX +
                ", senderY=" + senderY +
                ", senderWidth=" + senderWidth +
                ", senderHeight=" + senderHeight +
                ", subjectY=" + subjectY +
                ", textY=" + textY +
                ", closingYShift=" + closingYShift +
                ", enclosuresYShift=" + enclosuresYShift +
                ", headlineXShift=" + headlineXShift +
                ", headlineYShift=" + headlineYShift +
                ", perforationMarkX=" + perforationMarkX +
                ", perforationMarkY=" + perforationMarkY +
                ", perforationMarkLength=" + perforationMarkLength +
                ", perforationMarkLineWidth=" + perforationMarkLineWidth +
                ", foldingMark1X=" + foldingMark1X +
                ", foldingMark1Y=" + foldingMark1Y +
                ", foldingMark1Length=" + foldingMark1Length +
                ", foldingMark1LineWidth=" + foldingMark1LineWidth +
                ", foldingMark2X=" + foldingMark2X +
                ", foldingMark2Y=" + foldingMark2Y +
                ", foldingMark2Length=" + foldingMark2Length +
                ", foldingMark2LineWidth=" + foldingMark2LineWidth +
                '}';
    }
}
