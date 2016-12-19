package utilities;

/**
 * Created by Maksim on 11.12.2016.
 */
public interface RegularExpressions {
    String REG_EXP_FOR_PARAGRAPH = ".*\\n";
    String REG_EXP_FOR_SENTENCE = "[\\w,:;'\"#№$%\\(\\)\\[\\]\\{\\}^\\+\\- ]+[.?!] ?";
    String REG_EXP_FOR_WORD = "(?<" + GlobalConstants.NAME_OF_WORD_GROUP_FOR_REGEXP + ">\\w+)";
    String REG_EXP_FOR_DELIMITER = "(?<" + GlobalConstants.NAME_OF_DELIMITER_GROUP_FOR_REGEXP + ">\\W+( |$))";
    String REG_EXP_FOR_WORD_OR_DELIMITER = REG_EXP_FOR_WORD + "|" + REG_EXP_FOR_DELIMITER;

}
