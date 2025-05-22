package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.ServiceHistory;

public interface ServiceHistoryService extends Remote {
    boolean createServiceHistory(ServiceHistory serviceHistory) throws RemoteException;
    List<ServiceHistory> findAllServiceHistory() throws RemoteException;
    List<ServiceHistory> findServiceHistoryByVehicle(int vehicleId) throws RemoteException;
    List<ServiceHistory> findServiceHistoryByTuningJob(int tuningJobId) throws RemoteException;
    boolean deleteServiceHistory(int serviceId) throws RemoteException;
}