package model.nodeChildsFactories;

import model.nodeChilds.SymbolOfWord;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maksim on 18.12.2016.
 */
public class SymbolOfWordFactory {

    private static Map<Character, SymbolOfWord> symbols = new HashMap<>();

    public static SymbolOfWord getSymbol(Character character){
        SymbolOfWord symbolOfWord = symbols.get(character);
        if(symbolOfWord == null){
            symbolOfWord = new SymbolOfWord(character);
            symbols.put(character, symbolOfWord);
        }
        return symbolOfWord;
    }


}
