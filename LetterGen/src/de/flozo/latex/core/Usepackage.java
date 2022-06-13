package de.flozo.latex.core;

public class Usepackage {//} extends Command {

    public static final CommandName KEYWORD = CommandName.USEPACKAGE;
    private final PackageName packageName;

    public Usepackage(PackageName packageName) {
        this(packageName, new ExpressionList(""));
    }

    public Usepackage(PackageName packageName, String[] optionList) {
        this(packageName, new ExpressionList(optionList));
    }


    public Usepackage(PackageName packageName, FormattedExpressionList optionList) {
//        super(KEYWORD, new ExpressionList(packageName.getString()), optionList);
        this.packageName = packageName;
    }


}
