package model.nodeChilds;

import model.composite.Node;

/**
 * Created by Maksim on 11.12.2016.
 */
public class DelimiterSymbol extends Node {
    public DelimiterSymbol(Character value) {
        setSubcomponents(value.toString());
    }

    @Override
    public void setSubcomponents(String text) {
        setValue(text.charAt(0));
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
