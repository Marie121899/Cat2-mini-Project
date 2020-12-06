import java.rmi.Naming;

public class Server {
    public static void main(String[] args) throws Exception
    {

        // Create an object of the interface implementation class
        BankDbFunctions dao = new BankDbFunctions();
        // Binds the remote object by the name DAO
        Naming.rebind("DAO", dao);
        System.out.println("Server Started");
    }
}
