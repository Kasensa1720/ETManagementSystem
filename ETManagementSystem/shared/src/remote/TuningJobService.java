package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.TuningJob;

public interface TuningJobService extends Remote {
    boolean createTuningJob(TuningJob tuningJob) throws RemoteException;
    boolean updateTuningJob(TuningJob tuningJob) throws RemoteException;
    TuningJob findTuningJob(int id) throws RemoteException;
    boolean deleteTuningJob(int id) throws RemoteException;
    List<TuningJob> findAllTuningJobs() throws RemoteException;
    List<TuningJob> findTuningJobsByVehicle(int vehicleId) throws RemoteException;
}