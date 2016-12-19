package utilities;

import model.composite.Component;
import model.compositeChilds.Word;
import model.nodeChilds.DelimiterSymbol;
import model.nodeChildsFactories.DelimiterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Maksim on 11.12.2016.
 */
public class Parser {

    private Pattern universalPattern;

    private Matcher matcher;


    public List<String> divideStringIntoListOfParagraphsStrings(String text){
        text = prepareText(text); // правильно, що тільки тут, чи в кожному методі, бо їх можуть заюзати незалежно ???

        // витягую частини тексту що відповідають абзацу
        List<String> allMatches = findAllMatches(text, RegularExpressions.REG_EXP_FOR_PARAGRAPH);

        // рахую к-сть символів розподілених по абзацах
        int numberOfSymbolsInAllMatchedStrings = 0;
        for(String match: allMatches){
            numberOfSymbolsInAllMatchedStrings += match.length();
        }

        // останній абзац може не мати в кінці переводу на новий рядок, тому якщо на абзаци розбився не весь текст, то примусово витягую все що залишилося в кінці в ще один абзац
        if(numberOfSymbolsInAllMatchedStrings < text.length()){ // не вичиталися останні символи (бо в кінці не було "\n"), але вони все одно являють собою абзац
            allMatches.add(text.substring(numberOfSymbolsInAllMatchedStrings, text.length())); // додаю симоли останнього абзацу
        }

        // видаляю останній символ "\n" з кінця кожного абзацу, якщо він там є
        allMatches = deleteLastSymbolInEachStringInList(allMatches, '\n');

        // аналог всього попереднього коду в один рядок, але без явної роботи з паттрнами й матчерами
        //List<String> allMatches = new ArrayList<>(Arrays.asList(text.split(RegularExpressions.REG_EXP_FOR_PARAGRAPH)));

        return allMatches;
    }

    public List<String> divideStringIntoListOfSentencesStrings(String text){
        // витягую частини тексту що відповідають реченню
        List<String> allMatches = findAllMatches(text, RegularExpressions.REG_EXP_FOR_SENTENCE);

        // видаляю останній символ (пробіл) з кінця кожного абзацу, якщо він там є
        allMatches = deleteLastSymbolInEachStringInList(allMatches, ' ');

        return allMatches;
    }

    public List<Component> dividesStringIntoListOfWordsAndDelimiters(String text){
        List<Component> allComponents = new ArrayList<>();
        universalPattern = Pattern.compile(RegularExpressions.REG_EXP_FOR_WORD_OR_DELIMITER);
        matcher = universalPattern.matcher(text);
        while(matcher.find()){
            if(matcher.group(GlobalConstants.NAME_OF_WORD_GROUP_FOR_REGEXP) != null){
                allComponents.add(new Word(matcher.group(GlobalConstants.NAME_OF_WORD_GROUP_FOR_REGEXP)));
            } else if(matcher.group(GlobalConstants.NAME_OF_DELIMITER_GROUP_FOR_REGEXP) != null){

                // из-за того как регексом я получаю разделитель (может быть строка подряд из нескольких разделителей; еще может быть в конце пробел)
                char[] delimiterChars = matcher.group(GlobalConstants.NAME_OF_DELIMITER_GROUP_FOR_REGEXP).toCharArray();
                for (int i = 0; i < delimiterChars.length -1; i++) { // добавляю по отдельности все символы кроме последнего

                    //allComponents.add(new DelimiterSymbol(delimiterChars[i])); // todo FlyWeight (V)
                    allComponents.add(DelimiterFactory.getDelimiter(delimiterChars[i]));

                }
                if(!new Character(delimiterChars[delimiterChars.length-1]).equals(' ')){ // если последний символ не пробле, его тоже добавляю как отдельный разделитель

                    // allComponents.add(new DelimiterSymbol(delimiterChars[delimiterChars.length-1])); // todo FlyWeight  (V)
                    allComponents.add(DelimiterFactory.getDelimiter(delimiterChars[delimiterChars.length-1]));

                }

            }
        }

        return allComponents;
    }

    // повертає частини тексту, що повністю сходяться з регексом
    private List<String> findAllMatches(String text, String regex){
        List<String> allMatches = new ArrayList<>();

        universalPattern = Pattern.compile(regex);

        matcher = universalPattern.matcher(text);

        while (matcher.find()) {
            allMatches.add(matcher.group());
        }

        return allMatches;
    }

    private List<String> deleteLastSymbolInEachStringInList(List<String> strings, Character lastSymbolForDeleting){
        List<String> res = new ArrayList<>();
        String currentString;
        Character currentLastCharacter;
        for (int i = 0; i < strings.size(); i++) {
            currentString = strings.get(i);
            currentLastCharacter = currentString.charAt(currentString.length()-1);
            if(currentLastCharacter.equals(lastSymbolForDeleting)){ // якщо останній символ саме той, що треба видалити
                res.add(currentString.substring(0, currentString.length()-1)); // додаю без сотаннього символу
            } else {
                res.add(currentString); // інакше додаю як є
            }

        }
        return res;
    }

    private String prepareText(String text){
        text = text.replaceAll("\\t+", " ");
        text = text.replaceAll(" +", " ");
        return text;
    }

}
