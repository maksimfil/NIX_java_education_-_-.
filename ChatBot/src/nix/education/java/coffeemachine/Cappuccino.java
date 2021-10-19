package nix.education.java.coffeemachine;

public class Cappuccino {
    public static int water = 200;
    public static int milk = 100;
    public static int beans = 12;
    public static int cups = 1;
    public static int money = 6;

    public static String not_enough_comp() {
        if (CoffeeMachine.getWater() <= water) {
            return "water";
        } else if (CoffeeMachine.getMilk() <= milk) {
            return "milk";
        } else if (CoffeeMachine.getBeans() <= beans) {
            return "beans";
        } else {
            return "cups";
        }
    }

    public static boolean ability_cappuccino() {
        return CoffeeMachine.getWater() >= water &&
                CoffeeMachine.getBeans() >= beans &&
                CoffeeMachine.getCups() >= cups;
    }

    public static void doing_cappuccino(){
        CoffeeMachine.setWater(CoffeeMachine.getWater()- water);
        CoffeeMachine.setMilk(CoffeeMachine.getMilk()- milk);
        CoffeeMachine.setBeans(CoffeeMachine.getBeans()- beans);
        CoffeeMachine.setCups(CoffeeMachine.getCups()- cups);
        CoffeeMachine.setMoney(CoffeeMachine.getMoney()+ money);
    }
}
