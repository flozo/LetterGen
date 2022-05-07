package de.flozo.latex.core;

import de.flozo.latex.Command;

public class Usepackage extends Command {

    public static final CommandName KEYWORD = CommandName.USEPACKAGE;
    private final PackageName packageName;

    public Usepackage(PackageName packageName) {
        super(KEYWORD, new ExpressionList(packageName.getString()), null);
        this.packageName = packageName;
    }

    public Usepackage(PackageName packageName, String... optionList) {
        this(packageName, new ExpressionList(optionList));
    }


    public Usepackage(PackageName packageName, ExpressionList optionList) {
        super(KEYWORD, new ExpressionList(packageName.getString()), optionList);
        this.packageName = packageName;
    }


}
