package de.flozo.latex.letter;

import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.Length;
import de.flozo.latex.tikz.Point;

public class FoldingMark2 extends Mark {

    public FoldingMark2(LetterGeometry geometry, LetterColor color) {
        super(Point.fromNumbers(geometry.getFoldingMark2X(), geometry.getFoldingMark2Y()),
                Length.inDefaultUnit(geometry.getFoldingMark2Length()),
                Length.inDefaultUnit(geometry.getFoldingMark2LineWidth()),
                color.getFoldingMark2Color());
    }
}
