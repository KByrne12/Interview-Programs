import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

public class Model {
    private Vector<Word> wordList = null;

    private static View view = null;
    private static Context context;
    private int number = 1;

    // constructor
    public Model(){ wordList = new Vector(); }

    public static void setUI(Context context){
        Model.context = context;
        Word.setContext(context);
    }

    // set reference to view
    public static void setView(View view){
        Model.view = view;
    }

    // return list of words for view to draw
    public Enumeration getWords(){ return wordList.elements(); }

    // add word to wordList
    public void addWord(Word word){
        wordList.add(word);
        view.refresh();

    }

    // check given guess with the list of words
    // marks the word as discovered if correct for the view to update
    public void guess(String guess, int position){
        String word = ((Word) wordList.elementAt(position)).getWord();                                                  // grab word being guessed
        if(word.compareTo(guess) == 0){                                                                                 // check if words are the same
            System.out.println(guess + " is correct.");
            ((Word) wordList.elementAt(position)).setDiscovered();                                                      // mark word as discovered
        } else {
            System.out.println(guess + " is incorrect.");
        }
    }

    // offset[0] = which letter on new word
    // offset[1] = which letter on old word
    public void checkSpot(String newWord, String clue){
        //initial variables
        int[] offset = new int[2];
        Point referencePoint;
        Word tempWord;
        offset[0] = 0;
        offset[1] = 0;

        for(Word words : wordList){                                                                                     // cycle through list of added words
            String check = words.getWord();
            for(int i = 0; i < newWord.length(); i++){                                                                  // i = letter in new word
                for(int j = 0; j < check.length(); j++){                                                                // j = letter in current word from wordList
                    if(newWord.charAt(i) == check.charAt(j) && words.isSpotAvailable(j)){                               // if available and letter is the same
                        offset[0] = i;
                        offset[1] = j;
                        referencePoint = words.getCharPosition(offset[1]);
                        int i1 = (offset[0] + newWord.length()) * 25;
                        if (words.getDirection()) {                                                                     // check direction for algorithm ( horizontal )
                            if (((referencePoint.y - i1) < 780 && (referencePoint.y - (offset[0] * 25)) > 10 )){        // check bounds for within game screen
                                tempWord = new Word(newWord, clue, referencePoint.x,
                                        referencePoint.y - (offset[0] * 25), false, number);                            // construct a temporary word
                                if(checkWords(tempWord, referencePoint)){                                               // call checkWords for testing position
                                    tempWord.setConnected(offset[0]);                                                   // set newWord position as connected / unavailable
                                    words.setConnected(offset[1]);                                                      // set connected word as connected / unavailable
                                    number++;                                                                           // number for clue / word assignment
                                    try{
                                        addWord(tempWord);                                                              //add word to wordList
                                    } catch (NullPointerException e){}
                                    return;
                                }
                            }
                        } else {                                                                                        // check direction for algorithm ( vertical )
                            if (((referencePoint.x - i1) < 780 && (referencePoint.x - (offset[0] * 25)) > 10 )){        // check bounds for within game screen
                                tempWord = new Word(newWord, clue, referencePoint.x - (offset[0] * 25),
                                        referencePoint.y, true, number);                                                // construct a temporary word
                                if(checkWords(tempWord,referencePoint)){                                                // call checkWords for testing position
                                    tempWord.setConnected(offset[0]);                                                   // set newWord position as connected / unavailable
                                    words.setConnected(offset[1]);                                                      // set connected word as connected / unavailable
                                    number++;                                                                           // number for clue / word assignment
                                    try{
                                        addWord(tempWord);                                                              //add word to wordList
                                    } catch (NullPointerException e){}
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // i = new word letter
    // j = old word letter

    public boolean checkWords(Word check, Point overlap) {
        //initial variables
        boolean available = true;
        Point positionOld, positionNew;
        char charOld, charNew;

        // true = horizontal
        // false = vertical
        // check for letters on either side of new word
        if (check.getDirection()) {                                                                                     // check direction for algorithm ( horizontal )
            for (int i = 0; i < check.getWord().length(); i++) {                                                        // for every letter of new word
                positionNew = check.getCharPosition(i);
                for (Word words : wordList) {                                                                           // for each word in wordList
                    for (int j = 0; j < words.getWord().length(); j++) {                                                // check each letter
                        positionOld = words.getCharPosition(j);
                        if (!(overlap.x == positionNew.x && overlap.y == positionNew.y)) {                              // skip over the overlapped space
                            if (positionNew.x == positionOld.x && positionNew.y - 25== positionOld.y ) {                // if there is a letter above it
                                available = false;                                                                      // mark space unavailable for this word
                                break;
                            }
                            if (positionNew.x == positionOld.x && positionNew.y + 25== (positionOld.y )) {              // if there is a letter below it
                                available = false;                                                                      // mark space unavailable for this word
                                break;
                            }
                        }
                    }
                }
            }
        } else {                                                                                                        // check direction for algorithm ( vertical )
            for (int i = 0; i < check.getWord().length(); i++) {                                                        // for every letter of new word
                positionNew = check.getCharPosition(i);
                for (Word words : wordList) {                                                                           // for each word in wordList
                    for (int j = 0; j < words.getWord().length(); j++) {                                                // check each letter
                        positionOld = words.getCharPosition(j);
                        if (!(overlap.x == positionNew.x && overlap.y == positionNew.y)) {                              // skip over the overlapped space
                            if (positionNew.x - 25 == (positionOld.x) && positionNew.y == positionOld.y) {              // if there is a letter to the left
                                available = false;                                                                      // mark it as unavailable for this word
                                break;
                            }
                            if (positionNew.x  + 25== (positionOld.x) && positionNew.y == positionOld.y) {              //if there is a letter to the right
                                available = false;                                                                      // mark it as unavailable for this word
                                break;
                            }
                        }
                    }
                }
            }
        } // end check for letters on either side of word

            // check for letters before word
            positionNew = check.getCharPosition(0);
            if (check.getDirection()) {                                                                                 // check direction for algorithm ( horizontal )
                for (Word word : wordList) {                                                                            // for each word in wordList
                    for (int i = 0; i < word.getWord().length(); i++) {                                                 // for every letter in each word
                        positionOld = word.getCharPosition(i);
                        if (positionNew.x - 25 == positionOld.x && positionNew.y == positionOld.y) {                    // if the space before the first letter is taken
                            available = false;                                                                          // mark it as unavailable for this word
                            break;
                        }
                    }
                }
            } else {                                                                                                    // check direction for algorithm ( vertical )
                for (Word word : wordList) {                                                                            // for each word in wordList
                    for (int i = 0; i < word.getWord().length(); i++) {                                                 // for every letter in each word
                        positionOld = word.getCharPosition(i);
                        if (positionNew.x == positionOld.x && positionNew.y - 25 == positionOld.y) {                    // if the space before the first letter is taken
                            available = false;                                                                          // mark it as unavailable for this word
                            break;
                        }
                    }
                }
            } // end check for letters before word

            // check for letters after word
            positionNew = check.getCharPosition(check.getWord().length() - 1);
            if (check.getDirection()) {                                                                                 // check direction for algorithm ( horizontal )
                for (Word word : wordList) {                                                                            // for each word in wordList
                    for (int i = 0; i < word.getWord().length(); i++) {                                                 // for each letter in each word
                        positionOld = word.getCharPosition(i);
                        if (positionNew.x + 25 == positionOld.x && positionNew.y == positionOld.y) {                    // if the space after the last letter is taken
                            available = false;                                                                          // mark it as unavailable for this word
                            break;
                        }
                    }
                }
            } else {                                                                                                    // check direction for algorithm ( vertical )
                for (Word word : wordList) {                                                                            // for each word in wordList
                    for (int i = 0; i < word.getWord().length(); i++) {                                                 // for each letter in each word
                        positionOld = word.getCharPosition(i);
                        if (positionNew.x == positionOld.x && positionNew.y + 25 == positionOld.y) {                    // if the space after the last letter is taken
                            available = false;                                                                          // mark it as unavailable for this word
                            break;
                        }
                    }
                }
            } // end check for letters after word

            // check for letters sharing positions
            for (int i = 0; i < check.getWord().length(); i++) {                                                        // for each letter in new word
                positionNew = check.getCharPosition(i);
                for (Word words : wordList) {                                                                           // for each word in wordList
                    for (int j = 0; j < words.getWord().length(); j++) {                                                // check each letter
                        positionOld = words.getCharPosition(j);
                        if ((positionNew.x == positionOld.x && positionNew.y == positionOld.y)) {                       // if any of the letters share a space
                            charOld = words.getCharAt(j);
                            charNew = check.getCharAt(i);
                            if (!(charOld == charNew)) {                                                                // if the letters are not the same
                                available = false;                                                                      // mark it as unavailable for this word
                                break;
                            }

                        }
                    }
                }
            }  // end check for letters sharing positions
        return available;
    }

    // if every word has been discovered, end the game
    public boolean isGameDone(){
        boolean done = true;
        for(Word word: wordList) {                                                                                      // for each word in wordList
            if (!word.isDiscovered())                                                                                   // if the word is not discovered
                done = false;                                                                                           // set done to false
        }
        return done;
    }

    // start algorithm for adding words to model
    // if model is empty start with 80,80, horizontal
    public void attemptAddWord(String word, String clue){
        Word tempWord;
        if (wordList.isEmpty()) {                                                                                       // if the model is empty
            tempWord = new Word(word, clue, 80, 80, true, number);                                                      // create a word at 80, 80, horizontal
            number++;                                                                                                   // number for clue / word assignment
            try{
                addWord(tempWord);                                                                                      // add word to wordList
            } catch (NullPointerException e){}
        } else {
            checkSpot(word,clue);                                                                                       // check availability / attempt adding
        }
    }
}
