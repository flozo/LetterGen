package de.flozo.latex.core;

import java.util.List;

public interface ExpressionList {

    List<String> getBlock();
//    List<String> getBlock(boolean skipOpeningBracket, boolean skipClosingBracket);
    String getInline();

}
