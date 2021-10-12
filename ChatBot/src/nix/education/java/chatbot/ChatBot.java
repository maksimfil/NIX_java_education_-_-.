package nix.education.java.chatbot;

import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        System.out.println("Hello! My name is Amogus.");
        System.out.println("I was created in 2021.");
        System.out.println("Please, remind me your name.");
        Scanner scanner = new Scanner(System.in);
        String name_user = scanner.nextLine();
        System.out.format("What a great name you have, %s!\n",name_user);
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");
        int remainder3 = scanner.nextInt();
        int remainder5 = scanner.nextInt();
        int remainder7 = scanner.nextInt();
        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.format("Your age is %d; that's a good time to start programming!\n",age);
        System.out.println("Now I can prove to you that I can count to any number you want.");
        int user_number = scanner.nextInt();
        for (int i=0;i<user_number+1;i++) {
            System.out.format("%d !\n", i);
        }
        System.out.println("Let's test your programming knowledge.");
        System.out.println("Для какого типа данных предназначен форматирование %d");
        System.out.println("1.Любая строка");
        System.out.println("2.Символ (char)");
        System.out.println("3.Число с плавающей точкой");
        System.out.println("4.Целое число (int,byte long)");
        int user_answer = scanner.nextInt();
        while (user_answer != 4){
            System.out.println("Please try again!");
            user_answer = scanner.nextInt();
        }
        System.out.println("Great,you right");
        System.out.println("Goodbye,have a nice day!");
    }
}
