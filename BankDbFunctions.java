
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BankDbFunctions extends UnicastRemoteObject implements DAOInterface{
    DBConnection DBconn = new DBConnection();

    public BankDbFunctions() throws Exception {
        super();
    }

    public boolean createAccount(String firstname, String lastname, String surname, String phone, String email, String accountNo) {
        boolean created = false;
        String query = "INSERT INTO accounts(firstname, lastname, surname, phone, email, balance, accountNo) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DBconn.connect();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, surname);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setDouble(6, 0.00);
            ps.setString(7, accountNo);
            ps.execute();
            created = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return created;
    }

    public double deposit(String accountNo, double amount) {
        double newBalance = -1;
        double currentBalance = this.getBalance(accountNo);
        String query = "UPDATE accounts SET balance=? WHERE accountNo=?";
        try (Connection conn = DBconn.connect();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(2, accountNo);
            ps.setDouble(1, (currentBalance + amount));
            ps.execute();
            newBalance = currentBalance + amount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newBalance;
    }

    public double withdraw(String accountNo, double amount) {
        double newBalance = -1;
        double currentBalance = this.getBalance(accountNo);
        if (currentBalance - amount > 0) {
            String query = "UPDATE accounts SET balance=? WHERE accountNo=?";
            try (Connection conn = DBconn.connect();
                    PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(2, accountNo);
                ps.setDouble(1, currentBalance - amount);
                ps.execute();
            newBalance = currentBalance - amount;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return newBalance;
    }

    public double getBalance(String accountNumber) {
        double balance = -1;
        String query = "SELECT balance from accounts where accountNo=?";
        try (Connection conn = DBconn.connect();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, accountNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    balance = rs.getDouble("balance");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }
}
