package de.flozo.latex.tikz.commands;

import de.flozo.latex.core.Environment;
import de.flozo.latex.core.EnvironmentName;

import java.util.ArrayList;
import java.util.List;

public class LayerEnvironment {

    String layerName;

    List<String> body;

    public LayerEnvironment(String layerName, String... body) {
        this(layerName, new ArrayList<>(List.of(body)));
    }

    public LayerEnvironment(String layerName, List<String> body) {
        this.layerName = layerName;
        this.body = body;
    }

    public List<String> getBlock() {
        return new Environment.Builder(EnvironmentName.PGFONLAYER)
                .requiredArguments(layerName)
                .body(body)
                .build().getBlock();
    }

    @Override
    public String toString() {
        return "LayerEnvironment{" +
                "layerName='" + layerName + '\'' +
                ", body=" + body +
                '}';
    }
}
