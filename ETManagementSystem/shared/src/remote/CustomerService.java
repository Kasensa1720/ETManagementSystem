package com.etms.server.customer;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CustomerService extends Remote {
    int createCustomer(Customer customer) throws RemoteException;
    Customer getCustomerById(int customerId) throws RemoteException;
    List<Customer> getAllCustomers() throws RemoteException;
    int updateCustomer(Customer customer) throws RemoteException;
    int deleteCustomer(int customerId) throws RemoteException;
}
