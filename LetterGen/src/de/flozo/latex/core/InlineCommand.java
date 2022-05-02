package de.flozo.latex.core;

import java.util.ArrayList;
import java.util.List;

public class InlineCommand {

    public static final Bracket BODY_BRACKETS = Bracket.CURLY_BRACES;

    private final String name;
    private final ArgumentList options;
    private final ExpressionList body;
    private final List<String> lines;

    public InlineCommand(String name, ArgumentList options, ExpressionList body) {
        this.name = name;
        this.options = options;
        this.body = body;
        this.lines = new ArrayList<>();
    }


    public List<String> getLines() {
        System.out.println(options.getInline());
        System.out.println(body.getInline());
        lines.add("\\" + name + options.getInline() +
                BODY_BRACKETS.getLeftBracket() + body.getInline() +
                BODY_BRACKETS.getRightBracket());
        return lines;
    }


}
