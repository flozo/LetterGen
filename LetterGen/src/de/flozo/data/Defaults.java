package de.flozo.data;

import de.flozo.latex.core.Color;
import de.flozo.latex.core.ColorLetter;
import de.flozo.latex.core.ColorScheme;
import de.flozo.latex.core.FontSize;

public class Defaults {

    public static final double A4_WIDTH = 21.0;
    public static final double A4_HEIGHT = 29.7;
    public static final double MARGIN_TOP = 2.0;
    public static final double MARGIN_BOTTOM = 2.5;
    public static final double MARGIN_LEFT = 2.5;
    public static final double MARGIN_RIGHT = 2.0;

    public static final double ADDRESS_FIELD_X = 2.0;
    public static final double ADDRESS_FIELD_Y = 20.7;

    public static final double ADDRESS_FIELD_WIDTH = 9.0;
    public static final double ADDRESS_FIELD_HEIGHT = 4.5;


    public static final double BACK_ADDRESS_Y = 23.43;
    public static final double BACK_ADDRESS_SEPARATION_LINE_THICKNESS = 0.5;
    public static final String BACK_ADDRESS_SEPARATION_CHAR = "bullet";
    public static final String BACK_ADDRESS_FONT_SIZE = String.valueOf(FontSize.SCRIPT_SIZE);


    public static final double PERFORATION_MARK_X = 0.1;
    public static final double PERFORATION_MARK_Y = 14.85;
    public static final double PERFORATION_MARK_WIDTH = 0.5;
    public static final double PERFORATION_MARK_THICKNESS = 0.3;

    public static final double FOLDING_MARK_1_X = 0.1;
    public static final double FOLDING_MARK_1_Y = 19.2;
    public static final double FOLDING_MARK_1_WIDTH = 0.25;
    public static final double FOLDING_MARK_1_THICKNESS = 0.3;

    public static final double FOLDING_MARK_2_X = 0.1;
    public static final double FOLDING_MARK_2_Y = 8.7;
    public static final double FOLDING_MARK_2_WIDTH = 0.25;
    public static final double FOLDING_MARK_2_THICKNESS = 0.3;

    public static final String LETTER_BACKGROUND_COLOR = "none";
    public static final String LETTER_DRAFT_HIGHLIGHT_COLOR = new Color(ColorScheme.GREYS, ColorLetter.D).toString();





}
