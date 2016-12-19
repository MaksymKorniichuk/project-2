package model.compositeChilds;

import model.composite.Composite;
import utilities.Parser;

import java.util.List;

/**
 * Created by Maksim on 11.12.2016.
 */
public class Text extends Composite {

    public Text(String text){
        setSubcomponents(text);
    }

    @Override
    public void setSubcomponents(String text) {
        List<String> paragraphsStrings = new Parser().divideStringIntoListOfParagraphsStrings(text);
        for(String currentParagraphsString: paragraphsStrings){
            addComponent(new Paragraph(currentParagraphsString));
        }
    }


}
