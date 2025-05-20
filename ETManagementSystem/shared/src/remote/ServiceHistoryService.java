package com.etms.server.servicehistory;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServiceHistoryService extends Remote {

    // Create a new service history entry
    int createServiceHistory(ServiceHistory serviceHistory) throws RemoteException;

    // Retrieve all service history entries
    ArrayList<ServiceHistory> getAllServiceHistory() throws RemoteException;

    // Optional: Retrieve a single service history by ID
    ServiceHistory getServiceHistoryById(int id) throws RemoteException;

    // Optional: Update an existing service history
    int updateServiceHistory(ServiceHistory serviceHistory) throws RemoteException;

    // Optional: Delete a service history entry
    int deleteServiceHistory(int id) throws RemoteException;
}
