package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import dao.CustomerDaoHibernate;
import model.Customer;
import remote.CustomerService;

public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService {
    private final CustomerDaoHibernate customerDao;
    
    public CustomerServiceImpl() throws RemoteException {
        super();
        this.customerDao = new CustomerDaoHibernate();
    }

    @Override
    public boolean createCustomer(Customer customer) throws RemoteException {
        return customerDao.createCustomer(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) throws RemoteException {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public Customer findCustomer(int id) throws RemoteException {
        return customerDao.findCustomer(id);
    }

    @Override
    public boolean deleteCustomer(int id) throws RemoteException {
        return customerDao.deleteCustomer(id);
    }

    @Override
    public List<Customer> findAllCustomers() throws RemoteException {
        return customerDao.findAllCustomers();
    }
}