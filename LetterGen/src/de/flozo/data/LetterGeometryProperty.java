package de.flozo.data;

public enum LetterGeometryProperty {

    PAPER_WIDTH("paper.width"),
    PAPER_HEIGHT("paper.height"),
    BACKGROUND_COLOR("background.color"),
    DRAFT_MODE_HIGHLIGHT_COLOR("draft_mode.highlight_color"),
    URL_COLOR("url.color"),

    BORDER_MARGIN_TOP("border_margin.top"),
    BORDER_MARGIN_BOTTOM("border_margin.bottom"),
    BORDER_MARGIN_LEFT("border_margin.left"),
    BORDER_MARGIN_RIGHT("border_margin.right"),

    ADDRESS_X("address.x"),
    ADDRESS_Y("address.y"),
    ADDRESS_WIDTH("address.width"),
    ADDRESS_HEIGHT("address.height"),

    BACKADDRESS_Y("backaddress.y"),
    BACKADDRESS_SEPLINE_THICKNESS("backaddress.sepline.thickness"),
    BACKADDRESS_SEPCHAR("backaddress.sepchar"),
    BACKADDRESS_FONTSIZE("backaddress.fontsize"),

    SENDER_X("sender.x"),
    SENDER_Y("sender.y"),
    SENDER_WIDTH("sender.width"),
    SENDER_HEIGHT("sender.height"),

    SUBJECT_Y("subject.y"),
    TEXT_Y("text.y"),
    CLOSING_Y_SHIFT("closing.y_shift"),
    ENCLOSURES_Y_SHIFT("enclosures.y_shift"),

    PERFORATION_MARK_X("perforation_mark.x"),
    PERFORATION_MARK_Y("perforation_mark.y"),
    PERFORATION_MARK_WIDTH("perforation_mark.width"),
    PERFORATION_MARK_THICKNESS("perforation_mark.thickness"),
    FOLDING_MARK_1_X("folding_mark_1.x"),
    FOLDING_MARK_1_Y("folding_mark_1.y"),
    FOLDING_MARK_1_WIDTH("folding_mark_1.width"),
    FOLDING_MARK_1_THICKNESS("folding_mark_1.thickness"),
    FOLDING_MARK_2_X("folding_mark_2.x"),
    FOLDING_MARK_2_Y("folding_mark_2.y"),
    FOLDING_MARK_2_WIDTH("folding_mark_2.width"),
    FOLDING_MARK_2_THICKNESS("folding_mark_2.thickness");

    private final String property;

    LetterGeometryProperty(String property) {
        this.property = property;
    }

    public String getString() {
        return property;
    }
}
