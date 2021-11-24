package nix.education.java.dinner_party;
import java.util.*;

public class DinnerParty {
    public static void main(String[] args) {
        DinnerParty party = new DinnerParty();
        party.game();
    }
    private void game(){
        Map<String, Double> friends = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> membersList = new ArrayList<>();
        System.out.println("Enter the number of friends joining (including you):");
        int countFriends = scanner.nextInt();
        if (countFriends > 0){
            this.playersNotNull(countFriends,membersList,friends);
        }else {
            System.out.println("No one is joining for the party");
        }
    }
    private void playersNotNull(int countFriends, ArrayList membersList, Map friends){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of every friend (including you), each on a new line:");
        this.addingPeople(countFriends,membersList);
        System.out.println("Enter the total amount:");
        double countMoney = scanner.nextInt();
        System.out.println("Do you want to use the \"Who is lucky?\" feature? Write Yes/No:");
        String userAnswer = scanner.next();
        if (Objects.equals(userAnswer, "Yes")){
            this.calcMoneyLucky(countMoney,countFriends,membersList,friends);
            this.setMoneyHappyPerson(this.happyPerson(membersList),friends);
        }else {
            this.calcMoney(countMoney, countFriends, membersList, friends);
        }
        System.out.println(friends);
    }
    private String happyPerson(ArrayList membersList){
        Random random = new Random();
        String happyPerson = (String) membersList.get(random.nextInt(membersList.size()));
        System.out.format("%s is the lucky one!\n",happyPerson);
        return happyPerson;
    }
    private void calcMoney(double countMoney, int countFriends, ArrayList membersList, Map friends){
        double money = Math.round(countMoney/countFriends * 100.00) / 100.0;
        for (int i = 0; i < membersList.size(); i++){
            friends.put(membersList.get(i),money);
        }
        System.out.println("No one is going to be lucky");
    }
    private void addingPeople(int countFriends, ArrayList membersList){
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < countFriends; i++){
            String friend = scanner.next();
            membersList.add(friend);
        }
    }
    private void calcMoneyLucky(double countMoney, int countFriends, ArrayList membersList, Map friends){
        double money = Math.round(countMoney/(countFriends-1) * 100.00) / 100.0;
        for (int i = 0; i < membersList.size(); i++){
            friends.put(membersList.get(i),money);
        }
    }
    private void setMoneyHappyPerson(String person, Map friends){
        friends.put(person,0);
    }
}
