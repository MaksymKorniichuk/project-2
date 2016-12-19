package model.compositeChilds;

import model.composite.Component;
import model.composite.Composite;
import model.nodeChildsFactories.SymbolOfWordFactory;

/**
 * Created by Maksim on 11.12.2016.
 */
public class Word extends Composite {

    public Word(String text) {
        setSubcomponents(text);
    }

    @Override
    public void setSubcomponents(String text) {
        char[] symbols = text.toCharArray();
        for (int i = 0; i < symbols.length; i++) {

            addComponent(SymbolOfWordFactory.getSymbol(symbols[i]));

        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Component component: getComponents()){
            stringBuilder.append(component.toString());
        }
        return stringBuilder.toString();
    }


}
