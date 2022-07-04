package de.flozo.letter.data;

import de.flozo.latex.color.Color;

import java.util.Map;

public class LetterColor {

    private final Color backgroundColor;
    private final Color draftModeHighlightingBackgroundColor;
    private final Color draftModeHighlightingBorderColor;
    private final Color urlHyperlinkColor;

    private final Color headlineTextColor;
    private final Color headlineSeplineColor;

    private final Color addressTextColor;
    private final Color backaddressTextColor;
    private final Color backaddressSeplineColor;
    private final Color senderTextColor;
    private final Color senderIconColor;

    private final Color bodyTextColor;
    private final Color subjectTextColor;
    private final Color dateTextColor;
    private final Color closingTextColor;
    private final Color signatureTextColor;
    private final Color enclosuresTextColor;

    private final Color perforationMarkColor;
    private final Color foldingMark1Color;
    private final Color foldingMark2Color;


    public LetterColor(PropertyMap propertyMap) {
        Map<String, Color> colorMap = propertyMap.colorSubMap();
        this.backgroundColor = colorMap.get(LetterColorProperty.BACKGROUND_COLOR.getPropertyKey());
        this.draftModeHighlightingBackgroundColor = colorMap.get(LetterColorProperty.DRAFT_MODE_HIGHLIGHTING_BACKGROUND_COLOR.getPropertyKey());
        this.draftModeHighlightingBorderColor = colorMap.get(LetterColorProperty.DRAFT_MODE_HIGHLIGHTING_BORDER_COLOR.getPropertyKey());
        this.urlHyperlinkColor = colorMap.get(LetterColorProperty.URL_COLOR.getPropertyKey());
        this.headlineTextColor = colorMap.get(LetterColorProperty.HEADLINE_TEXT_COLOR.getPropertyKey());
        this.headlineSeplineColor = colorMap.get(LetterColorProperty.HEADLINE_SEPLINE_COLOR.getPropertyKey());
        this.addressTextColor = colorMap.get(LetterColorProperty.ADDRESS_TEXT_COLOR.getPropertyKey());
        this.backaddressTextColor = colorMap.get(LetterColorProperty.BACKADDRESS_TEXT_COLOR.getPropertyKey());
        this.backaddressSeplineColor = colorMap.get(LetterColorProperty.BACKADDRESS_SEPLINE_COLOR.getPropertyKey());
        this.senderTextColor = colorMap.get(LetterColorProperty.SENDER_TEXT_COLOR.getPropertyKey());
        this.senderIconColor = colorMap.get(LetterColorProperty.SENDER_ICON_COLOR.getPropertyKey());
        this.bodyTextColor = colorMap.get(LetterColorProperty.BODY_TEXT_COLOR.getPropertyKey());
        this.subjectTextColor = colorMap.get(LetterColorProperty.SUBJECT_TEXT_COLOR.getPropertyKey());
        this.dateTextColor = colorMap.get(LetterColorProperty.DATE_TEXT_COLOR.getPropertyKey());
        this.closingTextColor = colorMap.get(LetterColorProperty.CLOSING_TEXT_COLOR.getPropertyKey());
        this.signatureTextColor = colorMap.get(LetterColorProperty.SIGNATURE_TEXT_COLOR.getPropertyKey());
        this.enclosuresTextColor = colorMap.get(LetterColorProperty.ENCLOSURES_TEXT_COLOR.getPropertyKey());
        this.perforationMarkColor = colorMap.get(LetterColorProperty.PERFORATION_MARK_COLOR.getPropertyKey());
        this.foldingMark1Color = colorMap.get(LetterColorProperty.FOLDING_MARK_1_COLOR.getPropertyKey());
        this.foldingMark2Color = colorMap.get(LetterColorProperty.FOLDING_MARK_2_COLOR.getPropertyKey());
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getDraftModeHighlightingBackgroundColor() {
        return draftModeHighlightingBackgroundColor;
    }

    public Color getDraftModeHighlightingBorderColor() {
        return draftModeHighlightingBorderColor;
    }

    public Color getUrlHyperlinkColor() {
        return urlHyperlinkColor;
    }

    public Color getHeadlineTextColor() {
        return headlineTextColor;
    }

    public Color getHeadlineSeplineColor() {
        return headlineSeplineColor;
    }

    public Color getAddressTextColor() {
        return addressTextColor;
    }

    public Color getBackaddressTextColor() {
        return backaddressTextColor;
    }

    public Color getBackaddressSeplineColor() {
        return backaddressSeplineColor;
    }

    public Color getSenderTextColor() {
        return senderTextColor;
    }

    public Color getSenderIconColor() {
        return senderIconColor;
    }

    public Color getBodyTextColor() {
        return bodyTextColor;
    }

    public Color getSubjectTextColor() {
        return subjectTextColor;
    }

    public Color getDateTextColor() {
        return dateTextColor;
    }

    public Color getClosingTextColor() {
        return closingTextColor;
    }

    public Color getSignatureTextColor() {
        return signatureTextColor;
    }

    public Color getEnclosuresTextColor() {
        return enclosuresTextColor;
    }

    public Color getPerforationMarkColor() {
        return perforationMarkColor;
    }

    public Color getFoldingMark1Color() {
        return foldingMark1Color;
    }

    public Color getFoldingMark2Color() {
        return foldingMark2Color;
    }

    @Override
    public String toString() {
        return "LetterColor{" +
                "backgroundColor=" + backgroundColor +
                ", draftModeHighlightingBackgroundColor=" + draftModeHighlightingBackgroundColor +
                ", draftModeHighlightingBorderColor=" + draftModeHighlightingBorderColor +
                ", urlHyperlinkColor=" + urlHyperlinkColor +
                ", headlineTextColor=" + headlineTextColor +
                ", headlineSeplineColor=" + headlineSeplineColor +
                ", addressTextColor=" + addressTextColor +
                ", backaddressTextColor=" + backaddressTextColor +
                ", backaddressSeplineColor=" + backaddressSeplineColor +
                ", senderTextColor=" + senderTextColor +
                ", senderIconColor=" + senderIconColor +
                ", bodyTextColor=" + bodyTextColor +
                ", subjectTextColor=" + subjectTextColor +
                ", dateTextColor=" + dateTextColor +
                ", closingTextColor=" + closingTextColor +
                ", signatureTextColor=" + signatureTextColor +
                ", enclosuresTextColor=" + enclosuresTextColor +
                ", perforationMarkColor=" + perforationMarkColor +
                ", foldingMark1Color=" + foldingMark1Color +
                ", foldingMark2Color=" + foldingMark2Color +
                '}';
    }
}
