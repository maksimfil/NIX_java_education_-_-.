package nix.education.java.banking_system;

import java.sql.*;

public class Database{
    public Database() {
        connect();
    }

    private static final String URL = "jdbc:sqlite:card.s3db.db";

    private Connection conn = null;

    private void connect() {
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite established");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void disconnect(){
        try {
            if (conn != null){
                conn.close();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void createCardTable(){
        String query = "CREATE TABLE IF NOT EXISTS card(" +
                "id INTEGER PRIMARY KEY," +
                "number TEXT," +
                "pin TEXT," +
                "balance INTEGER DEFAULT 0)";
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            System.out.println("Card table created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addingCard(long userCard, int userPassword){
        String query = "INSERT INTO card (number,pin) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(userCard));
            preparedStatement.setString(2, String.valueOf(userPassword));
            preparedStatement.executeUpdate();
            System.out.println("Card added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkCardDb(String card){
        String query = "SELECT id FROM card WHERE number = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,card);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean checkUser(String card,String pin){
        String query = "SELECT id FROM card WHERE number = ? AND pin = ?";
        try {
            PreparedStatement preparedStatement= conn.prepareStatement(query);
            preparedStatement.setString(1,card);
            preparedStatement.setString(2,pin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }else {
                System.out.println("\nWrong PIN!\n");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void showBalance(String cardNumber){
        String query = "SELECT balance FROM card WHERE number = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("\nBalance: " + resultSet.getString("balance") + "\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMoney(String cardNumber,int countMoney){
        String query = "SELECT balance FROM card WHERE number = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            int userBalance = resultSet.getInt("balance");
            this.updatingMoney(cardNumber,userBalance,countMoney);
            System.out.println("Income was added!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updatingMoney(String cardNumber,int userBalance,int countMoney) {
        String queryAddMoney = "UPDATE card SET balance = ? WHERE number = ?";
        try {
            PreparedStatement addMoneyStatement = conn.prepareStatement(queryAddMoney);
            int newCountMoney = userBalance + countMoney;
            addMoneyStatement.setInt(1, newCountMoney);
            addMoneyStatement.setString(2, cardNumber);
            addMoneyStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean searchCard(String transferCard){
        String query = "SELECT id FROM card WHERE number = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,transferCard);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean enoughMoney(String myCard, int transferMoney){
        String query = "SELECT balance FROM card WHERE number = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,myCard);
            ResultSet resultSet = preparedStatement.executeQuery();
            int myMoney = resultSet.getInt("balance");
            if (myMoney< transferMoney){
                System.out.println("Not enough money!");
                return false;
            }else {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void transferringMoney(String myCard, String otherCard, int transferMOney){
            this.addressingMoney(myCard,transferMOney);
            this.receiveMoney(otherCard,transferMOney);
    }

    private void addressingMoney(String myCard,int transferMoney){
        String query = "UPDATE card SET balance = ? WHERE number = ?";
        try {
            PreparedStatement preparedStatement  = conn.prepareStatement(query);
            preparedStatement.setInt(1,this.currentBalance(myCard) - transferMoney);
            preparedStatement.setString(2,myCard);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void receiveMoney(String transferCard,int transferMoney){
        String query = "UPDATE card SET balance = ? WHERE number = ?";
        try {
            PreparedStatement preparedStatement  = conn.prepareStatement(query);
            preparedStatement.setInt(1,this.otherBalance(transferCard) + transferMoney);
            preparedStatement.setString(2,transferCard);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int currentBalance(String myCard){
        String query = "SELECT balance FROM card WHERE number = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,myCard);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt("balance");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int otherBalance(String transferCard){
        String query = "SELECT balance FROM card WHERE number = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,transferCard);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt("balance");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void deleteCard(String cardNumber){
        String query = "DELETE FROM card WHERE number = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,cardNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

