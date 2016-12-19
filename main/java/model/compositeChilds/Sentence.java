package model.compositeChilds;

import model.composite.Composite;
import utilities.Parser;

import java.util.List;

/**
 * Created by Maksim on 11.12.2016.
 */
public class Sentence extends Composite {

    public Sentence(String text) {
        setSubcomponents(text);
    }

    @Override
    public void setSubcomponents(String text) {
        addListOfComponent(new Parser().dividesStringIntoListOfWordsAndDelimiters(text));
    }
}
