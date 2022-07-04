package de.flozo.latex.core;

import java.util.List;

public class Usepackage implements Command {

    public static final CommandName KEYWORD = CommandName.USEPACKAGE;
    private final PackageName packageName;
    private final ExpressionList options;

    public Usepackage(PackageName packageName, ExpressionList options) {
        this.packageName = packageName;
        this.options = options;
    }

    @Override
    public List<String> getInlineOptions() {
        return new GenericCommand.Builder(KEYWORD.getString())
                .optionList(options.getInline())
                .body(packageName.getString())
                .build().getInlineOptions();
    }

    @Override
    public List<String> getBlock() {
        return new GenericCommand.Builder(KEYWORD.getString())
                .optionList(options.getBlock())
                .body(packageName.getString())
                .build().getBlock();
    }

    @Override
    public String getInline() {
        return new GenericCommand.Builder(KEYWORD.getString())
                .optionList(options.getInline())
                .body(packageName.getString())
                .build().getInline();
    }

    @Override
    public String toString() {
        return "Usepackage{" +
                "packageName=" + packageName +
                ", options=" + options +
                '}';
    }
}
