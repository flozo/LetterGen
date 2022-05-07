package de.flozo.latex.core;

public enum CommandName {
    DOCUMENTCLASS("documentclass"),
    USEPACKAGE("usepackage"),
    HYPERSETUP("hypersetup"),
    USETIKZLIBRARY("usetikzlibrary"),
    STANDALONEENV("standaloneenv"),
    TIKZMATH("tikzmath"),
    TIKZSET("tikzset"),
    FOREACH("foreach"),
    FILLDRAW("filldraw"),
    FILL("fill"),
    DRAW("draw"),
    PGFDECLARELAYER("pgfdeclarelayer"),
    PGFSETLAYERS("pgfsetlayers"),
    NODE("node"),
    HSPACE("hspace"),
    VSPACE("vspace"),
    PGFLINEWIDTH("pgflinewidth"),
    HREF("href"),
    BF("bf"),
    INCLUDEGRAPHICS("includegraphics"),
    FAICON("faIcon");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getString() {
        return commandName;
    }

}
