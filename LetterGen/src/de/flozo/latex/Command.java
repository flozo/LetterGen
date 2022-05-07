package de.flozo.latex;

import de.flozo.latex.core.*;

import java.util.ArrayList;
import java.util.List;

public class Command {

    public static final String COMMAND_MARKER_CHAR = "\\";
    public static final Bracket COMMAND_BODY_BRACKETS = Bracket.CURLY_BRACES;
    public static final StatementTerminator COMMAND_BODY_TERMINATOR = StatementTerminator.COMMA;
    public static final Bracket COMMAND_OPTIONS_BRACKETS = Bracket.SQUARE_BRACKETS;
    public static final StatementTerminator COMMAND_OPTIONS_TERMINATOR = StatementTerminator.COMMA;
    public static final String INDENT_CHARACTER = "\t";

    private final CommandName name;
    private final ExpressionList optionList;
    private final ExpressionList body;


    public Command(CommandName name, ExpressionList body) {
        this(name, body, null);
    }

    public Command(CommandName name, ExpressionList body, ExpressionList optionList) {
        this.name = name;
        this.optionList = optionList;
        this.body = body;
    }

    public List<String> getBlock() {
        Code.CodeBuilder body = new Code.CodeBuilder(this.body)
                .brackets(COMMAND_BODY_BRACKETS)
                .terminator(COMMAND_BODY_TERMINATOR);
        if (optionList != null) {
            Code optionsCode = new Code.CodeBuilder(optionList)
                    .brackets(COMMAND_OPTIONS_BRACKETS)
                    .terminator(COMMAND_OPTIONS_TERMINATOR)
                    .build();
            body.prepend(optionsCode);
        }
        List<String> codeLines = new ArrayList<>();
        codeLines.add(COMMAND_MARKER_CHAR + name.getString());
        codeLines.addAll(indent(body.build().getBlock()));
        return codeLines;
    }


    public String getInline() {
        String options = "";
        if (optionList != null) {
            Code optionsCode = new Code.CodeBuilder(optionList)
                    .brackets(COMMAND_OPTIONS_BRACKETS)
                    .terminator(COMMAND_OPTIONS_TERMINATOR)
                    .build();
            options = optionsCode.getInline();
        }
        Code body = new Code.CodeBuilder(this.body)
                .brackets(COMMAND_BODY_BRACKETS)
                .terminator(COMMAND_BODY_TERMINATOR)
                .build();
        return COMMAND_MARKER_CHAR + name.getString() + options +
                body.getInline();
    }

    private List<String> indent(List<String> code) {
        List<String> indentedCode = new ArrayList<>(code);
        indentedCode.replaceAll(s -> INDENT_CHARACTER + s);
        return indentedCode;
    }


}
