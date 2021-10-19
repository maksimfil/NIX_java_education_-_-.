package nix.education.java.coffeemachine;

public enum Coffee {
    CAPPUCCINO(200, 100, 12, 1, 6),
    ESPRESSO(250, 0, 16, 1, 4),
    LATTE(350, 75, 20, 1, 7);

    public final int water, milk, beans, cups, money;

    Coffee(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }
}
