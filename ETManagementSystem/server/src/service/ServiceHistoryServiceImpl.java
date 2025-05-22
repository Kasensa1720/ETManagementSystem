package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import dao.ServiceHistoryDaoHibernate;
import model.ServiceHistory;
import remote.ServiceHistoryService;

public class ServiceHistoryServiceImpl extends UnicastRemoteObject implements ServiceHistoryService {
    private final ServiceHistoryDaoHibernate serviceHistoryDao;
    
    public ServiceHistoryServiceImpl() throws RemoteException {
        super();
        this.serviceHistoryDao = new ServiceHistoryDaoHibernate();
    }

    @Override
    public boolean createServiceHistory(ServiceHistory serviceHistory) throws RemoteException {
        return serviceHistoryDao.createServiceHistory(serviceHistory);
    }

    @Override
    public List<ServiceHistory> findAllServiceHistory() throws RemoteException {
        return serviceHistoryDao.findAllServiceHistory();
    }

    @Override
    public List<ServiceHistory> findServiceHistoryByVehicle(int vehicleId) throws RemoteException {
        return serviceHistoryDao.findServiceHistoryByVehicle(vehicleId);
    }

    @Override
    public List<ServiceHistory> findServiceHistoryByTuningJob(int tuningJobId) throws RemoteException {
        return serviceHistoryDao.findServiceHistoryByTuningJob(tuningJobId);
    }

    @Override
    public boolean deleteServiceHistory(int serviceId) throws RemoteException {
        return serviceHistoryDao.deleteServiceHistory(serviceId);
    }
}