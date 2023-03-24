import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
/**
 * Tool to write and read from the terminal 
 */
public class Prompt {

    public ArrayList<String> read() {
        /**
         * Read information from the terminal
         * Return an ArrayList with first the name of the command and then the parameters
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un mot :");
        String input = sc.nextLine();
        String inputClear = input.replaceAll(",", "").replaceAll("x", " ");
        System.out.println("Vous avez saisi : " + inputClear);
        sc.close();
        String[] inputTab = inputClear.split("\\s+");
        ArrayList<String> inputList = new ArrayList<>(Arrays.asList(inputTab));
        ListIterator<String> li = inputList.listIterator();
    
        while (li.hasNext()) {
            String currentString = li.next();
            if (currentString.equals("at")) {
                li.remove();
            }
        }
        return inputList;
    }

    public void print(String message) {
        /**
         * Print information into the terminal
         */
        System.out.println(message);
    }
    

    public static void main(String[] argv) {
        Prompt prompt = new Prompt();
        ArrayList<String> inputList = prompt.read();
        ListIterator<String> li = inputList.listIterator();

        while (li.hasNext()) {
            String currentString = li.next();
            System.out.println(currentString);
        }
        prompt.print("test");
    }
}
