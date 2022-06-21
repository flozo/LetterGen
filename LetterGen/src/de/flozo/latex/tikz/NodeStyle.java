package de.flozo.latex.tikz;

import de.flozo.latex.core.FormattedExpressionList;
import de.flozo.latex.core.StatementTerminator;

import java.util.List;

public class NodeStyle {

    private final List<String> optionList;

    private NodeStyle(Builder builder) {
        this.optionList = builder.optionList;
    }


    public FormattedExpressionList getOptionList() {
        return new FormattedExpressionList.Builder(optionList)
                .terminator(StatementTerminator.COMMA)
                .skipLastTerminator(true)
                .inlineSpacing(true)
                .build();
    }

    @Override
    public String toString() {
        return "NodeStyle{" +
                "optionList=" + optionList +
                '}';
    }

    public static class Builder {

        private final List<String> optionList;

        public Builder(List<String> optionList) {
            this.optionList = optionList;
        }

        public Builder addNodeOption(NodeOption option, String value) {
            this.optionList.add(option.toString() + "=" + value);
            return this;
        }

        public Builder addCustomOption(String option) {
            this.optionList.add(option);
            return this;
        }

        public NodeStyle build() {
            return new NodeStyle(this);
        }

    }
}
