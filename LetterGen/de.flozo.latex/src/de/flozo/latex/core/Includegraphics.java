package de.flozo.latex.core;

import java.util.List;

public class Includegraphics implements Command {


    public static final CommandName KEYWORD = CommandName.INCLUDEGRAPHICS;
    private final String imageFileName;
    private final ExpressionList options;

    public Includegraphics(String imageFileName, ExpressionList options) {
        this.imageFileName = imageFileName;
        this.options = options;
    }

    public Includegraphics(String imageFileName) {
        this(imageFileName, new FormattedExpressionList.Builder("").build());
    }


    @Override
    public List<String> getInlineOptions() {
        return new GenericCommand.Builder(KEYWORD.getString())
                .optionList(options.getInline())
                .body(imageFileName)
                .build().getInlineOptions();
    }

    @Override
    public List<String> getBlock() {
            return new GenericCommand.Builder(KEYWORD.getString())
                    .optionList(options.getBlock())
                    .body(imageFileName)
                    .build().getBlock();
    }

    @Override
    public String getInline() {
        return new GenericCommand.Builder(KEYWORD.getString())
                .optionList(options.getInline())
                .body(imageFileName)
                .build().getInline();
    }

    @Override
    public String toString() {
        return "Includegraphics{" +
                "imageFileName='" + imageFileName + '\'' +
                ", options=" + options +
                '}';
    }
}
