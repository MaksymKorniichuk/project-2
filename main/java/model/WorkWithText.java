package model;

import model.composite.Component;
import model.composite.Composite;
import model.compositeChilds.Paragraph;
import model.compositeChilds.Sentence;
import model.compositeChilds.Text;
import model.compositeChilds.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Maksim on 14.12.2016.
 */
public class WorkWithText {
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_WHITE = "\u001B[37m";

    // return null если instanceOf не сработал, ведь тогда мы имеем неправильную иерархию вложености
    private List<Word> getAllWordsFromText(Text text){
        List<Component> componentsFromText = text.getComponents();
        List<Word> res = new ArrayList<>();

        for (Component curParagraph: componentsFromText) { // перебираю абзацы
            if(curParagraph instanceof Paragraph){ // если текущий компонент текста - параграф
                for(Component curSentence: ((Paragraph) curParagraph).getComponents()){ // перебираю предложения
                    if(curSentence instanceof Sentence){ // если текущий компонент абзаца - предложение
                        res.addAll(getAllWordsFromSentences((Sentence) curSentence)); // вытягиваю с него все слова
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
        }

        return res;
    }

    private List<Word> getAllWordsFromSentences(Sentence sentence){
        List<Word> words = new ArrayList<>();

        for(Component curWord: (sentence.getComponents())){
            if(curWord instanceof Word){
                words.add((Word)curWord);
            } // else не пишемо, бо в нього попадуть всі роздільники, або щось неправильно структуроване, якщо воно буде на цьому рівні вкладеності
        }
        return words;
    }

    public void printAllWordsFromTextInCorrectOrder(Text text){
        List<Word> allWordsFromText = getAllWordsFromText(text);

        sortCollection(allWordsFromText);

        Character lastChar = allWordsFromText.get(0).toString().charAt(0);
        String textColor = ANSI_RED;
        for(Word curWord: allWordsFromText){
            Character curFirstChar = new Character(curWord.toString().charAt(0));
            if(!curFirstChar.equals(lastChar)){
                textColor = ANSI_RED;
                lastChar = curFirstChar;
            }
            System.out.println(textColor + curWord);
            textColor = ANSI_WHITE;
        }

    }

    // todo невышло подругому
    private void sortCollection(List<Word> words){
        Collections.sort(words, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
    }
}
