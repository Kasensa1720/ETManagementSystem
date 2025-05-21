import service.UserServiceImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(5000);
            registry.rebind("UserService", new UserServiceImpl());
            // Add other services as needed
            System.out.println("RMI Server running on port 5000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
