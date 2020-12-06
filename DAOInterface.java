import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DAOInterface extends Remote {
    public  boolean createAccount(String firstname, String lastname, String surname, String phone, String email, String accountNo) throws RemoteException;
    public double deposit(String accountNo, double amount) throws RemoteException;
    public double withdraw(String accountNo, double amount) throws RemoteException;
    public double getBalance(String accountNumber) throws RemoteException;
}
