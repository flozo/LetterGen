package de.flozo.latex.letter;

import de.flozo.data.LetterColor;
import de.flozo.data.LetterGeometry;
import de.flozo.latex.core.Length;
import de.flozo.latex.tikz.Point;

public class PerforationMark extends Mark {

    public PerforationMark(LetterGeometry geometry, LetterColor color) {
        super(Point.fromNumbers(geometry.getPerforationMarkX(), geometry.getPerforationMarkY()),
                Length.inDefaultUnit(geometry.getPerforationMarkLength()),
                Length.inDefaultUnit(geometry.getPerforationMarkLineWidth()),
                color.getPerforationMarkColor());
    }


}
