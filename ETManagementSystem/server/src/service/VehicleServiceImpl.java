package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import dao.VehicleDaoHibernate;
import model.Vehicle;
import remote.VehicleService;

public class VehicleServiceImpl extends UnicastRemoteObject implements VehicleService {
    private final VehicleDaoHibernate vehicleDao;
    
    public VehicleServiceImpl() throws RemoteException {
        super();
        this.vehicleDao = new VehicleDaoHibernate();
    }

    @Override
    public boolean createVehicle(Vehicle vehicle) throws RemoteException {
        return vehicleDao.createVehicle(vehicle);
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) throws RemoteException {
        return vehicleDao.updateVehicle(vehicle);
    }

    @Override
    public Vehicle findVehicle(int id) throws RemoteException {
        return vehicleDao.findVehicle(id);
    }

    @Override
    public boolean deleteVehicle(int id) throws RemoteException {
        return vehicleDao.deleteVehicle(id);
    }

    @Override
    public List<Vehicle> findAllVehicles() throws RemoteException {
        return vehicleDao.findAllVehicles();
    }

    @Override
    public List<Vehicle> findVehiclesByCustomer(int customerId) throws RemoteException {
        return vehicleDao.findVehiclesByCustomer(customerId);
    }
}