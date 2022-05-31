package de.flozo.data;

import java.util.Map;

public class LetterGeometry {

    private final String backgroundColor;
    private final String draftModeHighlightColor;
    private final String urlHyperlinkColor;

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
    private final double backaddressSeplineLineWidth;
    private final String backaddressSepChar;
    private final String backaddressFontSize;

    private final double senderX;
    private final double senderY;
    private final double senderWidth;
    private final double senderHeight;

    private final double subjectY;
    private final double textY;
    private final double closingYShift;
    private final double enclosuresYShift;

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


//    public LetterGeometry() {
//        this.backgroundColor = LetterGeometryProperty.BACKGROUND_COLOR.getStringValue();
//        this.draftModeHighlightColor = LetterGeometryProperty.DRAFT_MODE_HIGHLIGHT_COLOR.getStringValue();
//        this.urlHyperlinkColor = LetterGeometryProperty.URL_COLOR.getStringValue();
//        this.paperWidth = LetterGeometryProperty.PAPER_WIDTH.getNumericalValue();
//        this.paperHeight = LetterGeometryProperty.PAPER_HEIGHT.getNumericalValue();
//        this.borderMarginTop = LetterGeometryProperty.BORDER_MARGIN_TOP.getNumericalValue();
//        this.borderMarginBottom = LetterGeometryProperty.BORDER_MARGIN_BOTTOM.getNumericalValue();
//        this.borderMarginLeft = LetterGeometryProperty.BORDER_MARGIN_LEFT.getNumericalValue();
//        this.borderMarginRight = LetterGeometryProperty.BORDER_MARGIN_RIGHT.getNumericalValue();
//        this.addressX = LetterGeometryProperty.ADDRESS_X.getNumericalValue();
//        this.addressY = LetterGeometryProperty.ADDRESS_Y.getNumericalValue();
//        this.addressWidth = LetterGeometryProperty.ADDRESS_WIDTH.getNumericalValue();
//        this.addressHeight = LetterGeometryProperty.ADDRESS_HEIGHT.getNumericalValue();
//        this.backaddressX = LetterGeometryProperty.BACKADDRESS_X.getNumericalValue();
//        this.backaddressY = LetterGeometryProperty.BACKADDRESS_Y.getNumericalValue();
//        this.backaddressWidth = LetterGeometryProperty.BACKADDRESS_WIDTH.getNumericalValue();
//        this.backaddressHeight = LetterGeometryProperty.BACKADDRESS_HEIGHT.getNumericalValue();
//        this.backaddressSeplineLineWidth = LetterGeometryProperty.BACKADDRESS_SEPLINE_LINEWIDTH.getNumericalValue();
//        this.backaddressSepChar = LetterGeometryProperty.BACKADDRESS_SEPCHAR.getStringValue();
//        this.backaddressFontSize = LetterGeometryProperty.BACKADDRESS_FONTSIZE.getStringValue();
//        this.senderX = LetterGeometryProperty.SENDER_X.getNumericalValue();
//        this.senderY = LetterGeometryProperty.SENDER_Y.getNumericalValue();
//        this.senderWidth = LetterGeometryProperty.SENDER_WIDTH.getNumericalValue();
//        this.senderHeight = LetterGeometryProperty.SENDER_HEIGHT.getNumericalValue();
//        this.subjectY = LetterGeometryProperty.SUBJECT_Y.getNumericalValue();
//        this.textY = LetterGeometryProperty.TEXT_Y.getNumericalValue();
//        this.closingYShift = LetterGeometryProperty.CLOSING_Y_SHIFT.getNumericalValue();
//        this.enclosuresYShift = LetterGeometryProperty.ENCLOSURES_Y_SHIFT.getNumericalValue();
//        this.perforationMarkX = LetterGeometryProperty.PERFORATION_MARK_X.getNumericalValue();
//        this.perforationMarkY = LetterGeometryProperty.PERFORATION_MARK_Y.getNumericalValue();
//        this.perforationMarkLength = LetterGeometryProperty.PERFORATION_MARK_LENGTH.getNumericalValue();
//        this.perforationMarkLineWidth = LetterGeometryProperty.PERFORATION_MARK_LINE_WIDTH.getNumericalValue();
//        this.foldingMark1X = LetterGeometryProperty.FOLDING_MARK_1_X.getNumericalValue();
//        this.foldingMark1Y = LetterGeometryProperty.FOLDING_MARK_1_Y.getNumericalValue();
//        this.foldingMark1Length = LetterGeometryProperty.FOLDING_MARK_1_LENGTH.getNumericalValue();
//        this.foldingMark1LineWidth = LetterGeometryProperty.FOLDING_MARK_1_LINE_WIDTH.getNumericalValue();
//        this.foldingMark2X = LetterGeometryProperty.FOLDING_MARK_2_X.getNumericalValue();
//        this.foldingMark2Y = LetterGeometryProperty.FOLDING_MARK_2_Y.getNumericalValue();
//        this.foldingMark2Length = LetterGeometryProperty.FOLDING_MARK_2_LENGTH.getNumericalValue();
//        this.foldingMark2LineWidth = LetterGeometryProperty.FOLDING_MARK_2_LINE_WIDTH.getNumericalValue();
//    }

    public LetterGeometry(PropertyMap propertyMap) {
        Map<String, String> rawMap = propertyMap.getRawMap();
        Map<String, String> stringSubMap = propertyMap.stringSubMap(rawMap);
        Map<String, Double> numericSubMap = propertyMap.numericSubMap(rawMap);
        this.backgroundColor = stringSubMap.get(LetterGeometryProperty.BACKGROUND_COLOR.getPropertyName());
        this.draftModeHighlightColor = stringSubMap.get(LetterGeometryProperty.DRAFT_MODE_HIGHLIGHT_COLOR.getPropertyName());
        this.urlHyperlinkColor = stringSubMap.get(LetterGeometryProperty.URL_COLOR.getPropertyName());
        this.paperWidth = numericSubMap.get(LetterGeometryProperty.PAPER_WIDTH.getPropertyName());
        this.paperHeight = numericSubMap.get(LetterGeometryProperty.PAPER_HEIGHT.getPropertyName());
        this.borderMarginTop = numericSubMap.get(LetterGeometryProperty.BORDER_MARGIN_TOP.getPropertyName());
        this.borderMarginBottom = numericSubMap.get(LetterGeometryProperty.BORDER_MARGIN_BOTTOM.getPropertyName());
        this.borderMarginLeft = numericSubMap.get(LetterGeometryProperty.BORDER_MARGIN_LEFT.getPropertyName());
        this.borderMarginRight = numericSubMap.get(LetterGeometryProperty.BORDER_MARGIN_RIGHT.getPropertyName());
        this.addressX = numericSubMap.get(LetterGeometryProperty.ADDRESS_X.getPropertyName());
        this.addressY = numericSubMap.get(LetterGeometryProperty.ADDRESS_Y.getPropertyName());
        this.addressWidth = numericSubMap.get(LetterGeometryProperty.ADDRESS_WIDTH.getPropertyName());
        this.addressHeight = numericSubMap.get(LetterGeometryProperty.ADDRESS_HEIGHT.getPropertyName());
        this.backaddressX = numericSubMap.get(LetterGeometryProperty.BACKADDRESS_X.getPropertyName());
        this.backaddressY = numericSubMap.get(LetterGeometryProperty.BACKADDRESS_Y.getPropertyName());
        this.backaddressWidth = numericSubMap.get(LetterGeometryProperty.BACKADDRESS_WIDTH.getPropertyName());
        this.backaddressHeight = numericSubMap.get(LetterGeometryProperty.BACKADDRESS_HEIGHT.getPropertyName());
        this.backaddressSeplineLineWidth = numericSubMap.get(LetterGeometryProperty.BACKADDRESS_SEPLINE_LINEWIDTH.getPropertyName());
        this.backaddressSepChar = stringSubMap.get(LetterGeometryProperty.BACKADDRESS_SEPCHAR.getPropertyName());
        this.backaddressFontSize = stringSubMap.get(LetterGeometryProperty.BACKADDRESS_FONTSIZE.getPropertyName());
        this.senderX = numericSubMap.get(LetterGeometryProperty.SENDER_X.getPropertyName());
        this.senderY = numericSubMap.get(LetterGeometryProperty.SENDER_Y.getPropertyName());
        this.senderWidth = numericSubMap.get(LetterGeometryProperty.SENDER_WIDTH.getPropertyName());
        this.senderHeight = numericSubMap.get(LetterGeometryProperty.SENDER_HEIGHT.getPropertyName());
        this.subjectY = numericSubMap.get(LetterGeometryProperty.SUBJECT_Y.getPropertyName());
        this.textY = numericSubMap.get(LetterGeometryProperty.TEXT_Y.getPropertyName());
        this.closingYShift = numericSubMap.get(LetterGeometryProperty.CLOSING_Y_SHIFT.getPropertyName());
        this.enclosuresYShift = numericSubMap.get(LetterGeometryProperty.ENCLOSURES_Y_SHIFT.getPropertyName());
        this.perforationMarkX = numericSubMap.get(LetterGeometryProperty.PERFORATION_MARK_X.getPropertyName());
        this.perforationMarkY = numericSubMap.get(LetterGeometryProperty.PERFORATION_MARK_Y.getPropertyName());
        this.perforationMarkLength = numericSubMap.get(LetterGeometryProperty.PERFORATION_MARK_LENGTH.getPropertyName());
        this.perforationMarkLineWidth = numericSubMap.get(LetterGeometryProperty.PERFORATION_MARK_LINE_WIDTH.getPropertyName());
        this.foldingMark1X = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_1_X.getPropertyName());
        this.foldingMark1Y = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_1_Y.getPropertyName());
        this.foldingMark1Length = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_1_LENGTH.getPropertyName());
        this.foldingMark1LineWidth = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_1_LINE_WIDTH.getPropertyName());
        this.foldingMark2X = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_2_X.getPropertyName());
        this.foldingMark2Y = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_2_Y.getPropertyName());
        this.foldingMark2Length = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_2_LENGTH.getPropertyName());
        this.foldingMark2LineWidth = numericSubMap.get(LetterGeometryProperty.FOLDING_MARK_2_LINE_WIDTH.getPropertyName());
    }


    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getDraftModeHighlightColor() {
        return draftModeHighlightColor;
    }

    public String getUrlHyperlinkColor() {
        return urlHyperlinkColor;
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

    public double getBackaddressSeplineLineWidth() {
        return backaddressSeplineLineWidth;
    }

    public String getBackaddressSepChar() {
        return backaddressSepChar;
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
}
