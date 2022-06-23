package de.flozo.latex.letter;

import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.Length;
import de.flozo.latex.tikz.Point;

public class FoldingMark1 extends Mark {

    public FoldingMark1(LetterGeometry geometry, LetterColor color) {
        super(Point.fromNumbers(geometry.getFoldingMark1X(), geometry.getFoldingMark1Y()),
                Length.inDefaultUnit(geometry.getFoldingMark1Length()),
                Length.inDefaultUnit(geometry.getFoldingMark1LineWidth()),
                color.getFoldingMark1Color());
    }
}
