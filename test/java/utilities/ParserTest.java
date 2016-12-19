package utilities;

import model.composite.Component;
import model.compositeChilds.Word;
import model.nodeChilds.DelimiterSymbol;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Maksim on 19.12.2016.
 */
public class ParserTest {
    private static Parser parser;

    @BeforeClass
    public static void init(){
        parser = new Parser();
    }

    @Test
    public void divideStringIntoListOfParagraphsStrings() throws Exception {
        String inputStr = "qwe    qwe    qwe.\n" +
                "rty\t\trty.\n" +
                "asdf     asdf.\n" +
                "zxcv\t\t\tzxcv.";
        List<String> paragraphs = parser.divideStringIntoListOfParagraphsStrings(inputStr);

        assertEquals("Zero element of list is wrong.", paragraphs.get(0), "qwe qwe qwe.");
        assertEquals("First element of list is wrong.", paragraphs.get(1), "rty rty.");
        assertEquals("Second element of list is wrong.", paragraphs.get(2), "asdf asdf.");
        assertEquals("Third element of list is wrong.", paragraphs.get(3), "zxcv zxcv.");
    }

    @Test
    public void divideStringIntoListOfSentencesStrings() throws Exception {
        String inputStr = "London, apple. House #1 and(4+2)! Why?";
        List<String> paragraphs = parser.divideStringIntoListOfSentencesStrings(inputStr);

        assertEquals("Zero element of list is wrong.", paragraphs.get(0), "London, apple.");
        assertEquals("First element of list is wrong.", paragraphs.get(1), "House #1 and(4+2)!");
        assertEquals("Second element of list is wrong.", paragraphs.get(2), "Why?");
    }

    @Test
    public void dividesStringIntoListOfWordsAndDelimiters() throws Exception {
        String inputStr = "Word1, word2, word3: word4!?";
        List<Component> words = parser.dividesStringIntoListOfWordsAndDelimiters(inputStr);

        assertEquals("Zero element of list is wrong.", words.get(0), new Word("Word1"));
        assertEquals("First element of list is wrong.", words.get(1), new DelimiterSymbol(','));
        assertEquals("Second element of list is wrong.", words.get(2), new Word("word2"));
        assertEquals("Third element of list is wrong.", words.get(3), new DelimiterSymbol(','));
        assertEquals("Fourth element of list is wrong.", words.get(4), new Word("word3"));
        assertEquals("5th element of list is wrong.", words.get(5), new DelimiterSymbol(':'));
        assertEquals("6th element of list is wrong.", words.get(6), new Word("word4"));
        assertEquals("7th element of list is wrong.", words.get(7), new DelimiterSymbol('!'));
        assertEquals("8th element of list is wrong.", words.get(8), new DelimiterSymbol('?'));
    }

}