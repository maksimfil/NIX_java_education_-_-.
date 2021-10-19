package nix.education.java.coffeemachine;

public class Latte {
    public static int water = 350;
    public static int milk = 75;
    public static int beans = 20;
    public static int cups = 1;
    public static int money = 7;

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
    public static boolean ability_latte() {
        return CoffeeMachine.getWater() >= water &&
                CoffeeMachine.getBeans() >= beans &&
                CoffeeMachine.getCups() >= cups;
    }

    public static void doing_latte(){
        CoffeeMachine.setWater(CoffeeMachine.getWater()- water);
        CoffeeMachine.setMilk(CoffeeMachine.getMilk()- milk);
        CoffeeMachine.setBeans(CoffeeMachine.getBeans()- beans);
        CoffeeMachine.setCups(CoffeeMachine.getCups()- cups);
        CoffeeMachine.setMoney(CoffeeMachine.getMoney()+ money);
    }
}
