package model.nodeChilds;

import model.composite.Node;

/**
 * Created by Maksim on 11.12.2016.
 */
public class SymbolOfWord extends Node {
    public SymbolOfWord(Character value) {
        setSubcomponents(value.toString());
    }

    @Override
    public void setSubcomponents(String text) {
        setValue(text.charAt(0));
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
}
