package nix.education.java.coffeemachine;

import java.util.Scanner;

public class Program {
    public static void status_machine() {
        System.out.println("\nThe coffee machine has:");
        System.out.format("%d of water\n", CoffeeMachine.getWater());
        System.out.format("%d of milk\n", CoffeeMachine.getMilk());
        System.out.format("%d of coffee beans\n", CoffeeMachine.getBeans());
        System.out.format("%d of disposable cups\n", CoffeeMachine.getCups());
        System.out.format("%d of money\n\n", CoffeeMachine.getMoney());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String user_action = scanner.nextLine();
            label:
            switch (user_action) {
                case "fill" -> CoffeeMachine.fill();
                case "take" -> CoffeeMachine.take();
                case "remaining" -> status_machine();
                case "exit" -> System.exit(0);
                case "buy" -> {
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back-to main menu:");
                    String user_coffee = scanner.nextLine();
                    switch (user_coffee) {
                        case "back":
                            break label;
                        case "1":
                            if (Espresso.ability_espresso()) {
                                Espresso.doing_espresso();
                                System.out.println("I have enough resources, making you a coffee!\n");
                            } else {
                                System.out.format("Sorry,not enough %s!\n\n", Espresso.not_enough_comp());
                            }
                            break;
                        case "2":
                            if (Latte.ability_latte()) {
                                Latte.doing_latte();
                                System.out.println("I have enough resources, making you a coffee!\n");
                            } else {
                                System.out.format("Sorry,not enough %s!\n\n", Latte.not_enough_comp());
                            }
                            break;
                        case "3":
                            if (Cappuccino.ability_cappuccino()) {
                                Cappuccino.doing_cappuccino();
                                System.out.println("I have enough resources, making you a coffee!\n");
                            } else {
                                System.out.format("Sorry,not enough %s!\n\n", Cappuccino.not_enough_comp());
                            }
                            break;
                    }
                }
            }
        }while (true);
    }
}

