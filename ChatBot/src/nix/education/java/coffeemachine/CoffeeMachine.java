package nix.education.java.coffeemachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeMachine {
    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getBeans() {
        return beans;
    }

    public void setBeans(int beans) {
        this.beans = beans;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void fill() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWrite how many ml of water you want to add:");
        int filled_water = scanner.nextInt();
        this.water = this.water + filled_water;
        System.out.println("Write how many ml of milk you want to add:");
        int filled_milk = scanner.nextInt();
        this.milk = this.milk + filled_milk;
        System.out.println("Write how many grams of coffee beans you want to add:");
        int filled_beans = scanner.nextInt();
        this.beans = this.beans + filled_beans;
        System.out.println("Write how many disposable coffee cups you want to add:");
        int filled_cups = scanner.nextInt();
        this.cups = this.cups + filled_cups;
    }

    public void status_machine() {
        System.out.println("\nThe coffee machine has:");
        System.out.format("%d of water\n", this.water);
        System.out.format("%d of milk\n", this.milk);
        System.out.format("%d of coffee beans\n", this.beans);
        System.out.format("%d of disposable cups\n", this.cups);
        System.out.format("%d of money\n\n", this.money);
    }

    public void take() {
        System.out.format("I gave you %d\n\n", this.money);
        this.money = 0;
    }

    public boolean action(String user_action) {
        switch (user_action) {
            case "fill" -> this.fill();
            case "take" -> this.take();
            case "remaining" -> this.status_machine();
            case "exit" -> System.exit(0);
            case "buy" -> {
                return true;
            }
        }return false;
    }
    public void buying_coffee(String user_coffee){
        switch (user_coffee) {
            case "back":
                break;
            case "1":
                making_coffee(1);
                break;
            case "2":
                making_coffee(2);
                break;
            case "3":
                making_coffee(3);
                break;
        }
    }

    public void making_coffee(int num_coffee) {
        Map<Integer, Coffee> states = new HashMap<>();
        states.put(1,Coffee.ESPRESSO);
        states.put(2, Coffee.LATTE);
        states.put(3, Coffee.CAPPUCCINO);
        if (checking_coffee(states.get(num_coffee))) {
            doing_coffee(states.get(num_coffee));
            System.out.println("I have enough resources, making you a coffee!\n");
        }else {
            System.out.format("Sorry,not enough %s!\n\n", not_enough_comp(states.get(num_coffee)));
        }
    }

    public String not_enough_comp(Coffee num_coffee) {
        if (this.getWater() <= num_coffee.water) {
            return "water";
        } else if (this.getMilk() <= num_coffee.milk) {
            return "milk";
        } else if (this.getBeans() <= num_coffee.beans) {
            return "beans";
        } else {
            return "cups";
        }
    }

    public boolean checking_coffee(Coffee num_coffee) {
        return this.getWater() >= num_coffee.water &&
                this.getBeans() >= num_coffee.beans &&
                this.getCups() >= num_coffee.cups;
    }
    public void doing_coffee(Coffee num_coffee){
        this.setWater(this.getWater()- num_coffee.water);
        this.setMilk(this.getMilk()- num_coffee.milk);
        this.setBeans(this.getBeans()- num_coffee.beans);
        this.setCups(this.getCups()- num_coffee.cups);
        this.setMoney(this.getMoney()+ num_coffee.money);
    }
}