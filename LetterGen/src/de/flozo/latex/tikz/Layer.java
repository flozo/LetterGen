package de.flozo.latex.tikz;

import de.flozo.latex.core.Command2;
import de.flozo.latex.core.CommandName;
import de.flozo.latex.core.StatementTerminator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Layer {

    List<String> layers;

    public Layer(LayerBuilder builder) {
        this.layers = builder.layers;
    }


    public List<String> getBlock() {
        List<String> codeLines = new ArrayList<>(getDeclarationBlock());
        codeLines.add(getSetLayers());
        return codeLines;
    }

    public List<String> getDeclarationBlock() {
        List<String> codeLines = new ArrayList<>();
        for (String layer : layers) {
            if (!Objects.equals(layer, "main")) {
                codeLines.add(new Command2.Command2Builder(CommandName.PGFDECLARELAYER.getString()).body(layer).build().getInline());
            }
        }
        return codeLines;
    }

    public String getSetLayers() {
        return new Command2.Command2Builder(CommandName.PGFSETLAYERS.getString()).body(layers).bodyTerminator(StatementTerminator.COMMA).build().getInline();
    }


    public static class LayerBuilder {

        List<String> layers;

        public LayerBuilder(String... layers) {
            this(new ArrayList<>(List.of(layers)));
        }

        public LayerBuilder(List<String> layers) {
            this.layers = layers;
        }

        public LayerBuilder addLayer(String layer) {
            layers.add(layer);
            return this;
        }




        public Layer build() {
            return new Layer(this);
        }
    }
}
