package nix.education.java.coffeemachine;

import java.util.Scanner;

public class CoffeeMachine {
    private static int water = 400;
    private static int milk = 540;
    private static int beans = 120;
    private static int cups = 9;
    private static int money = 550;

    public static int getWater() {
        return CoffeeMachine.water;
    }

    public static void setWater(int water) {
        CoffeeMachine.water = water;
    }

    public static int getMilk() {
        return CoffeeMachine.milk;
    }

    public static void setMilk(int milk) {
        CoffeeMachine.milk = milk;
    }

    public static int getBeans() {
        return CoffeeMachine.beans;
    }

    public static void setBeans(int beans) {
        CoffeeMachine.beans = beans;
    }

    public static int getCups() {
        return CoffeeMachine.cups;
    }

    public static void setCups(int cups) {
        CoffeeMachine.cups = cups;
    }

    public static int getMoney() {
        return CoffeeMachine.money;
    }

    public static void setMoney(int money) {
        CoffeeMachine.money = money;
    }

    public static void fill(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWrite how many ml of water you want to add:");
        int filled_water = scanner.nextInt();
        CoffeeMachine.setWater(water + filled_water);
        System.out.println("Write how many ml of milk you want to add:");
        int filled_milk = scanner.nextInt();
        CoffeeMachine.setMilk(milk+filled_milk );
        System.out.println("Write how many grams of coffee beans you want to add:");
        int filled_beans = scanner.nextInt();
        CoffeeMachine.setBeans(beans + filled_beans);
        System.out.println("Write how many disposable coffee cups you want to add:");
        int filled_cups = scanner.nextInt();
        CoffeeMachine.setCups(cups + filled_cups);
    }
    public static void take(){
        System.out.format("I gave you %d\n\n",CoffeeMachine.getMoney());
        CoffeeMachine.setMoney(0);
    }
}
