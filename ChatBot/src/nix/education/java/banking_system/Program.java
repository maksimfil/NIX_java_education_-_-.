package nix.education.java.banking_system;

import java.util.*;

public class Program {
    private long currentCard = 0L;
    private int addingPassword = 0;
    private long addingCard;

    public long getAddingCard() {
        return addingCard;
    }

    public void setAddingCard(long addingCard) {
        this.addingCard = addingCard;
    }

    public long getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(long currentCard) {
        this.currentCard = currentCard;
    }

    public int getAddingPassword() {
        return addingPassword;
    }

    public void setAddingPassword(int addingPassword) {
        this.addingPassword = addingPassword;
    }

    public static void main(String[] args) {
        Database db = new Database();
        db.createCardTable();
        Program bank = new Program();
        bank.startUserMenu();
        bank.actions(db);
    }

    public void startUserMenu() {
        System.out.println("\n1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    private void actions(Database db){
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        switch (userChoice) {
            case 1 -> {
                this.createdCard();
                this.creatingCardOutput();
                db.addingCard(this.getAddingCard(),this.getAddingPassword());
                this.startUserMenu();
                this.actions(db);
                db.disconnect();
            }
            case 2 -> this.checkCard(scanner,db);
            case 0 -> this.exitProgram(db);
        }
    }

    private void creatingCardOutput(){
        System.out.println("\nYour card has been created");
        System.out.println("Your card number:");
        System.out.println(this.getAddingCard());
        System.out.println("Your card PIN:");
        System.out.println(this.getAddingPassword());
    }

    private void createdCard() {
        do {
            generatorCard();
        } while (!luhnAlgorithm(generatorCard()));
        this.generatorPassword();
    }

    private void generatorPassword(){
        int min = 1000;
        int max = 9999;
        max -= min;
        this.setAddingPassword((int) (Math.random() * ++max) + min);
    }

    private long generatorCard() {
        long min = 4000000000000000L;
        long max = 4000009999999999L;
        max -= min;
        return (long) (Math.random() * ++max) + min;
    }

    private boolean luhnAlgorithm(long card) {
        long currentCard = card;
        ArrayList<Integer> nums = new ArrayList<>();
        while (card != 0) {
            nums.add((int) (card % 10));
            card /= 10;
        }
        Collections.reverse(nums);
        ListIterator<Integer> it = nums.listIterator();
        int checksum = 0;
        while (it.hasNext()) {
            if (it.nextIndex() % 2 == 0) {
                int chetNums = it.next() * 2;
                if (chetNums > 9) {
                    chetNums -= 9;
                }
                checksum += chetNums;
            } else {
                checksum += it.next();
            }
        }
        if (checksum % 10 == 0) {
            this.setAddingCard(currentCard);
        }
        return checksum % 10 == 0;
    }

    private void checkCard(Scanner scanner, Database db) {
        System.out.println("Enter your card number:");
        String userCard = scanner.next();
        if (db.checkCardDb(userCard)){
            this.checkPassword(scanner,userCard,db);
            this.setCurrentCard(Long.parseLong(userCard));
            this.menuInProfile(scanner,db);
        }else {
            String cardAgain;
            do {
                System.out.println("Wrong card number");
                System.out.println("Enter your card number:");
                cardAgain = scanner.next();
            } while (!db.checkCardDb(cardAgain));
            this.checkPassword(scanner,cardAgain,db);
            this.setCurrentCard(Long.parseLong(userCard));
            this.menuInProfile(scanner,db);
        }
    }

    private void checkPassword(Scanner scanner,String card, Database db){
        System.out.println("Enter your PIN:");
        String userPassword = scanner.next();
        if (db.checkUser(card,userPassword)){
            System.out.println("\nYou have successfully logged in!");
        }else {
            this.startUserMenu();
            this.actions(db);
        }
    }

    private void menuInProfile(Scanner scanner,Database db){
        System.out.println("\n1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
        this.personalAction(scanner,String.valueOf(this.getCurrentCard()),db);
    }
    private void personalAction(Scanner scanner,String cardNumber, Database db){
        int userChoice = scanner.nextInt();
        switch (userChoice){
            case 1 -> {
                db.showBalance(cardNumber);
                this.menuInProfile(scanner,db);
            }
            case 2 -> {
                this.addIncome(scanner, db);
                this.menuInProfile(scanner,db);
            }
            case 3 -> this.transferMoney(scanner,db);
            case 4 -> {
                db.deleteCard(cardNumber);
                System.out.println("The account has been closed!");
                this.startUserMenu();
                this.actions(db);
            }
            case 5 ->{
                System.out.println("\nYou have successfully logged out!\n");
                this.startUserMenu();
                this.actions(db);
            }
            case 0 -> this.exitProgram(db);
        }
    }

    private void addIncome(Scanner scanner,Database db){
        System.out.println("Enter income:");
        int countAddedMoney = scanner.nextInt();
        db.addMoney(String.valueOf(this.getCurrentCard()),countAddedMoney);
    }

    private void transferMoney(Scanner scanner, Database db){
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        long transferCard = scanner.nextLong();
        if (allChecks(transferCard, db)){
            System.out.println("Enter how much money you want to transfer:");
            int countMoney = scanner.nextInt();
            if(db.enoughMoney(String.valueOf(this.getCurrentCard()),countMoney)){
                db.transferringMoney(String.valueOf(this.getCurrentCard()), String.valueOf(transferCard), countMoney);
                this.menuInProfile(scanner,db);
            }else {
                this.menuInProfile(scanner,db);
            }
        }else {
            this.menuInProfile(scanner,db);
        }

    }

    private boolean allChecks(long transferCard,Database db) {
        return errorLuhnAlgorithm(transferCard) && errorCardAbsent(transferCard, db)
                && errorSameAccount(transferCard);
    }

    private boolean errorSameAccount(long transferCard){
        System.out.println(this.getCurrentCard());
        if (this.getCurrentCard() == transferCard) {
            System.out.println("You can't transfer money to the same account!");
            return false;
        }else {
            return true;
        }
    }

    private boolean errorLuhnAlgorithm(long transferCard){
        if (!luhnAlgorithm(transferCard)){
            System.out.println("Probably you made a mistake in the card number. Please try again!\n");
            return false;
        }return true;
    }

    private boolean errorCardAbsent(long transferCard, Database db){
        if(!db.searchCard(String.valueOf(transferCard))){
            System.out.println("Such a card does not exist.");
            return false;
        }return true;
    }
    private void exitProgram(Database db){
        System.out.println("Bye!");
        db.disconnect();
        System.exit(0);
    }

}

