package nix.education.java.coffeemachine;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine();
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String user_action = scanner.nextLine();
            if (machine.action(user_action)) {
                System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back-to main menu:");
                String user_coffee = scanner.nextLine();
                machine.buying_coffee(user_coffee);
            }else {
                continue;
            }
        }
    }
}

