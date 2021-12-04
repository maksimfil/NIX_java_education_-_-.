package nix.education.java.tictactoe;

import java.util.*;

public class TicTacToe {
    private String field = "         ";
    private char player= 'O';

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    private void showField(){
        System.out.println("---------");
        System.out.printf("| %s %s %s |\n",getField().charAt(0),getField().charAt(1),getField().charAt(2));
        System.out.printf("| %s %s %s |\n",getField().charAt(3),getField().charAt(4),getField().charAt(5));
        System.out.printf("| %s %s %s |\n",getField().charAt(6),getField().charAt(7),getField().charAt(8));
        System.out.println("---------");
    }

    private void replaceLetter(int index){
        StringBuilder replace = new StringBuilder(getField());
        replace.setCharAt(index, this.getPlayer());
        this.setField(replace.toString());
    }

    private int indexOfString(String[] userInput){
        Map<String, Integer> mapIndexes = new HashMap<>();
        String index = Arrays.toString(userInput).charAt(1) + " " + Arrays.toString(userInput).charAt(4);
        mapIndexes.put("1 1",0);
        mapIndexes.put("1 2",1);
        mapIndexes.put("1 3",2);
        mapIndexes.put("2 1",3);
        mapIndexes.put("2 2",4);
        mapIndexes.put("2 3",5);
        mapIndexes.put("3 1",6);
        mapIndexes.put("3 2",7);
        mapIndexes.put("3 3",8);
        return mapIndexes.get(index);
    }

    private boolean userInputIsNumber(String[] userInput){
        if (Character.isDigit(Arrays.toString(userInput).charAt(1)) &&
                Character.isDigit(Arrays.toString(userInput).charAt(4))){
            return true;
        }else {
            System.out.println("You should enter numbers!");
        }return false;
    }

    private boolean userInput1_3(String[] userInput){
        String tempStr1 = String.valueOf(Arrays.toString(userInput).charAt(1));
        String tempStr2 = String.valueOf(Arrays.toString(userInput).charAt(4));
        int symbol1 = Integer.parseInt(tempStr1);
        int symbol2 = Integer.parseInt(tempStr2);
        if (symbol1 >= 1 && symbol1 <=3 && symbol2>=1 && symbol2 <=3){
            return true;
        }else {
            System.out.println("Coordinates should be from 1 to 3!");
        }return false;
    }

    private boolean cellNotOccupied(String[] userInput){
        if (this.getField().charAt(indexOfString(userInput)) == ' '){
            return true;
        }else {
            System.out.println("This cell is occupied! Choose another one!");
        }return false;

    }

    private boolean allChecks(String[] userInput){
        return userInputIsNumber(userInput) && userInput1_3(userInput) && cellNotOccupied(userInput);
    }

    private boolean winCheck(){
        Integer[][] winCombinations =  {{2,1,0},{5,4,3},{8,7,6},{6,3,0},{7,4,1},{8,5,2},{8,4,0},{6,4,2}};
        for (Integer[] comb : winCombinations) {
            if(((this.getField().charAt(comb[0]) == this.getField().charAt(comb[1]) && this.getField().charAt(comb[0]) !=' ') &&
                    (this.getField().charAt(comb[0]) == this.getField().charAt(comb[2]) && this.getField().charAt(comb[0]) !=' '))){
                return true;
            }
        }
        return false;
    }

    private void switchPlayer(char player){
        if (player == 'X'){
            this.setPlayer('O');
        }else {
            this.setPlayer('X');
        }
    }

    private boolean checkDraw(){
        return !this.getField().contains(" ");
    }

    private void game(){
        this.showField();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter the coordinates: ");
            String[] userInput = scanner.nextLine().split(" ");
            if (allChecks(userInput)){
                this.switchPlayer(this.getPlayer());
                this.replaceLetter(indexOfString(userInput));
                this.showField();
                if (this.winCheck()){
                    System.out.printf("%c wins",this.getPlayer());
                    break;
                }
                if (checkDraw()){
                    System.out.println("Draw");
                    break;
                }
            }
        }while (true);
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.game();
    }
}