package nix.education.java.coffeemachine;

public class Espresso {
    public static int water = 250;
    public static int beans = 16;
    public static int cups = 1;
    public static int money = 4;

    public static String not_enough_comp() {
        if (CoffeeMachine.getWater() <= water) {
            return "water";
        } else if (CoffeeMachine.getBeans() <= beans) {
            return "beans";
        } else{
            return "cups";
        }
    }
    public static boolean ability_espresso() {
        return CoffeeMachine.getWater() >= water &&
                CoffeeMachine.getBeans() >= beans &&
                CoffeeMachine.getCups() >= cups;
    }
    public static void doing_espresso(){
        CoffeeMachine.setWater(CoffeeMachine.getWater()- water);
        CoffeeMachine.setBeans(CoffeeMachine.getBeans()- beans);
        CoffeeMachine.setCups(CoffeeMachine.getCups()- cups);
        CoffeeMachine.setMoney(CoffeeMachine.getMoney()+ money);
    }
}

