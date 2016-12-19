package model.compositeChilds;

import model.composite.Composite;
import utilities.Parser;

import java.util.List;

/**
 * Created by Maksim on 11.12.2016.
 */
public class Paragraph extends Composite {

    public Paragraph(String text) {
        setSubcomponents(text);
    }

    @Override
    public void setSubcomponents(String text) {
        List<String> sentencesStrings = new Parser().divideStringIntoListOfSentencesStrings(text);
        for(String currentSentencesString: sentencesStrings){
            addComponent(new Sentence(currentSentencesString));
        }
    }
}
