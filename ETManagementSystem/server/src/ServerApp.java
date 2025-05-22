import service.CustomerServiceImpl;
import service.ServiceHistoryServiceImpl;
import service.TuningJobServiceImpl;
import service.UserServiceImpl;
import service.VehicleServiceImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerApp {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(5000);
            registry.rebind("UserService", new UserServiceImpl());
            registry.rebind("TuningJobService", new TuningJobServiceImpl());
            registry.rebind("VehicleService", new VehicleServiceImpl());
            registry.rebind("ServiceHistoryService", new ServiceHistoryServiceImpl());
            registry.rebind("CustomerService", new CustomerServiceImpl());
            // Add other services as needed
            System.out.println("RMI Server running on port 5000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
