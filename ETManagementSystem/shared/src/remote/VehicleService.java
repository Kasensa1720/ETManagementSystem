package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Vehicle;

public interface VehicleService extends Remote {
    boolean createVehicle(Vehicle vehicle) throws RemoteException;
    boolean updateVehicle(Vehicle vehicle) throws RemoteException;
    Vehicle findVehicle(int id) throws RemoteException;
    boolean deleteVehicle(int id) throws RemoteException;
    List<Vehicle> findAllVehicles() throws RemoteException;
    List<Vehicle> findVehiclesByCustomer(int customerId) throws RemoteException;
}