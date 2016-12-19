package model.nodeChildsFactories;

import model.nodeChilds.DelimiterSymbol;
import model.nodeChilds.SymbolOfWord;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksim on 18.12.2016.
 */
public class DelimiterFactory {

    private static Map<Character, DelimiterSymbol> delimiters = new HashMap<>();

    public static DelimiterSymbol getDelimiter(Character character){
        DelimiterSymbol delimiterSymbol = delimiters.get(character);
        if(delimiterSymbol == null){
            delimiterSymbol = new DelimiterSymbol(character);
            delimiters.put(character, delimiterSymbol);
        }
        return delimiterSymbol;
    }

}
