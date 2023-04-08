import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
/**
 * Tool to write and read from the terminal 
 */
public class Prompt {

    Scanner sc;

    public Prompt(Scanner sc) {
        this.sc = sc;
    }

    public ArrayList<String> read() {
        /**
         * Read information from the terminal
         * Return an ArrayList with first the name of the command and then the parameters
         */
        
        System.out.println("Veuillez saisir une commande :");
        String input = sc.nextLine();
        String inputClear = input.replaceAll(",", "").replaceAll("x", " ");
        System.out.println("Vous avez saisi : " + inputClear);
        ArrayList<String> inputList =  parse(inputClear,"\\s+");
        return inputList;

    }

    public void print(String message) {
        /**
         * Print information into the terminal
         */
        System.out.println(message);
    }
    
    public ArrayList<String> parse(String inputClear, String splitter ){
        String[] inputTab = inputClear.split(splitter);
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

    public static void main(String[] argv) {
        Scanner sc = new Scanner(System.in);
        Prompt prompt = new Prompt(sc);
        ArrayList<String> inputList = prompt.read();
        ListIterator<String> li = inputList.listIterator();

        while (li.hasNext()) {
            String currentString = li.next();
            System.out.println(currentString);
        }
        prompt.print("test");
    }
}
