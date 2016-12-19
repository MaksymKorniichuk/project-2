import model.WorkWithText;
import model.composite.Composite;
import model.compositeChilds.Text;

/**
 * Created by Maksim on 11.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        String inputText = "First, paragraph! Sentence.\nSecond, paragraph? Sentence.\n";
        Composite composite = new Text(inputText);
        WorkWithText workWithText = new WorkWithText();
        workWithText.printAllWordsFromTextInCorrectOrder((Text) composite);
    }
}
