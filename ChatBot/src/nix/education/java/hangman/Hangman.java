package nix.education.java.hangman;

import java.util.*;

public class Hangman {
    private String correctWord = choiceWord();
    private String field = startField();
    private int lives = 8;
    private ArrayList usedWords  = new ArrayList();


    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public String getCorrectWord() {
        return correctWord;
    }

    public void setCorrectWord(String correctWord) {
        this.correctWord = correctWord;
    }

    public ArrayList getUsedWords() {
        return usedWords;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    private void addToList(ArrayList<String> array, String userLetter){
        array.add(userLetter);
    }

    private String choiceWord() {
        ArrayList<String> words = new ArrayList<>();
        Random random = new Random();
        words.add("python");
        words.add("java");
        words.add("javascript");
        words.add("kotlin");
        return words.get(random.nextInt(words.size()));
    }

    private String startField() {
        String[] symbols = this.getCorrectWord().split("");
        Arrays.fill(symbols, "-");
        return String.join("", symbols);
    }

    private void replace(String playerLetter,ArrayList<Integer> indexes){
        for (int i = 0; i < indexes.size();i++){
            StringBuilder replacedStr = new StringBuilder(this.getField());
            replacedStr.setCharAt(indexes.get(i),playerLetter.charAt(0));
            this.setField(replacedStr.toString());
        }
    }

    private ArrayList<Integer> findIndexes(String userLetter,String correctWord) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < this.getCorrectWord().length(); i++) {
            if (userLetter.charAt(0) == correctWord.charAt(i)) {
                indexes.add(i);
            }continue;
        }return indexes;
    }

    private boolean checkLowerEngLetter(String playerLetter) {
        if (Character.isLetter(playerLetter.charAt(0)) && Character.isLowerCase(playerLetter.charAt(0))) {
            return true;
        }
        System.out.println("Please enter a lowercase English letter");
        return false;
    }

    private boolean checkLenLetter(String playerLetter) {
        if (playerLetter.length() < 2){
            return true;
        }else {
            System.out.println("You should input a single letter");
            return false;
        }
    }

    private boolean allChecks(String userLetter){
        return checkLenLetter(userLetter) && checkLowerEngLetter(userLetter)&&
                guessedLetter(this.getUsedWords(), userLetter)&& includeLetter(userLetter);
    }

    private boolean includeLetter(String userLetter){
        if (this.getCorrectWord().contains(userLetter)){
            return true;
        }else {
            System.out.println("That letter doesn't appear in the word\n");
            this.setLives(getLives()-1);
            return false;
        }
    }

    private boolean guessedLetter(ArrayList<String> array,String userLetter){
        if(array.contains(userLetter)){
            System.out.println("You've already guessed this letter\n");
            return false;
        }else {
            return true;
        }
    }

    private boolean checkWin(){
        if (Objects.equals(this.getCorrectWord(), this.getField())){
            System.out.format("You guessed the word %s!\n",this.getCorrectWord());
            System.out.println("You survived!");
            System.exit(0);
            return false;
        }return true;
    }

    private void playing(){
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(this.getField());
            System.out.print("Input a letter: ");
            String userLetter = scanner.nextLine();
            if (userLetter.length() == 0){
                continue;
            }
            if (this.allChecks(userLetter)) {
                this.replace(userLetter,findIndexes(userLetter,this.getCorrectWord()));
                this.addToList(this.getUsedWords(), userLetter);
                if (this.checkWin()) {
                    continue;
                }
            }
            this.addToList(this.getUsedWords(), userLetter);
            continue;
        } while (this.lives > 0);
        System.out.println("You lost!");
    }

    private void game(Hangman hang) {
        this.choiceWord();
        this.startField();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Type \"play\" to play the game, \"exit\" to quit: ");
            String userPlayExit = scanner.nextLine();
            if (Objects.equals(userPlayExit, "play")) {
                hang.playing();
            } else if (Objects.equals(userPlayExit, "exit")) {
                break;
            } else {
                continue;
            }
        }
    }

    public static void main(String[] args) {
        Hangman hang = new Hangman();
        System.out.println("HANGMAN");
        hang.game(hang);
        }
    }