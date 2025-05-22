package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Customer;

public interface CustomerService extends Remote {
    boolean createCustomer(Customer customer) throws RemoteException;
    boolean updateCustomer(Customer customer) throws RemoteException;
    Customer findCustomer(int id) throws RemoteException;
    boolean deleteCustomer(int id) throws RemoteException;
    List<Customer> findAllCustomers() throws RemoteException;
}