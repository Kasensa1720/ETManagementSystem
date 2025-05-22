package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import dao.TuningJobDaoHibernate;
import model.TuningJob;
import remote.TuningJobService;

public class TuningJobServiceImpl extends UnicastRemoteObject implements TuningJobService {
    private final TuningJobDaoHibernate tuningJobDao;
    
    public TuningJobServiceImpl() throws RemoteException {
        super();
        this.tuningJobDao = new TuningJobDaoHibernate();
    }

    @Override
    public boolean createTuningJob(TuningJob tuningJob) throws RemoteException {
        return tuningJobDao.createTuningJob(tuningJob);
    }

    @Override
    public boolean updateTuningJob(TuningJob tuningJob) throws RemoteException {
        return tuningJobDao.updateTuningJob(tuningJob);
    }

    @Override
    public TuningJob findTuningJob(int id) throws RemoteException {
        return tuningJobDao.findTuningJob(id);
    }

    @Override
    public boolean deleteTuningJob(int id) throws RemoteException {
        return tuningJobDao.deleteTuningJob(id);
    }

    @Override
    public List<TuningJob> findAllTuningJobs() throws RemoteException {
        return tuningJobDao.findAllTuningJobs();
    }

    @Override
    public List<TuningJob> findTuningJobsByVehicle(int vehicleId) throws RemoteException {
        return tuningJobDao.findTuningJobsByVehicle(vehicleId);
    }
}