package de.flozo.letter.latex;

import de.flozo.letter.data.LetterColor;
import de.flozo.letter.data.LetterGeometry;
import de.flozo.latex.core.Length;
import de.flozo.latex.tikz.commands.Point;

public class PerforationMark extends Mark {

    public PerforationMark(LetterGeometry geometry, LetterColor color) {
        super(Point.fromNumbers(geometry.getPerforationMarkX(), geometry.getPerforationMarkY()),
                Length.inDefaultUnit(geometry.getPerforationMarkLength()),
                Length.inDefaultUnit(geometry.getPerforationMarkLineWidth()),
                color.getPerforationMarkColor());
    }


}
