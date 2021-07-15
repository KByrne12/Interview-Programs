import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.exit;

public class Crossword {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Model model = new Model();
    private View view = new View();
    private boolean done = false;


    public Crossword(){}

    public void runCrossword () throws FileNotFoundException {
        System.out.println("Crossword is running!");
        createGame();
        Model.setView(view);
        view.setModel(model);
        view.show();
        playGame();
    }

    public void createGame() throws FileNotFoundException{
        File file = new File("C:\\Users\\ksaso\\Desktop\\Important Info\\" +
                "Coding\\Java\\CrosswordDatabase\\Words.txt");                                                          // open file database of words
        Scanner reader = new Scanner(file);
        String word,clue;
        while(reader.hasNext()) {
                word = reader.next();                                                                                   // first word on the line is the word
                word.toLowerCase();                                                                                     // make word lowercase fo consistency
                clue = reader.nextLine();                                                                               // the rest of the line is the clue
                model.attemptAddWord(word,clue);                                                                        // attempt to add the word / clue to model
        }
    }

    public void playGame(){
        while (!done){
            int word = getCommand();                                                                                    // selection of word to guess
            String guess = getToken("Guess: ");                                                                  // input of guess
            model.guess(guess.toLowerCase(),word);                                                                      // check model with given selection / guess
            view.refresh();                                                                                             // refresh the model
            if(model.isGameDone())                                                                                      // check if all words have been discovered
                done = true;                                                                                            // end game if all words discovered
        }
    }

    // used for int input for selecting word
    public int getCommand() {
        do {
            try {
                int value = Integer.parseInt(getToken("Which word would you like to try?\n"));
                if (value > 0 ) {
                    value -= 1;
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid, try again");
            }
        } while (true);
    }

    // used for string input for making guesses
    public String getToken(String prompt) {
        do {
            try {
                System.out.print(prompt);
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } catch (IOException ioe) {
                exit(0);
            }
        } while (true);
    }
}
