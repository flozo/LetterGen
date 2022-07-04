package de.flozo.letter.latex;

import de.flozo.letter.data.LetterColor;
import de.flozo.letter.data.LetterGeometry;
import de.flozo.latex.core.Length;
import de.flozo.latex.tikz.commands.Point;

public class FoldingMark2 extends Mark {

    public FoldingMark2(LetterGeometry geometry, LetterColor color) {
        super(Point.fromNumbers(geometry.getFoldingMark2X(), geometry.getFoldingMark2Y()),
                Length.inDefaultUnit(geometry.getFoldingMark2Length()),
                Length.inDefaultUnit(geometry.getFoldingMark2LineWidth()),
                color.getFoldingMark2Color());
    }
}
