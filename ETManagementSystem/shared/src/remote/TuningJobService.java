package com.etms.server.tuningjob;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface TuningJobService extends Remote {
    int createTuningJob(TuningJob tuningJob) throws RemoteException;
    int updateTuningJob(TuningJob tuningJob) throws RemoteException;
    int deleteTuningJob(int tuningJobId) throws RemoteException;
    TuningJob getTuningJobById(int tuningJobId) throws RemoteException;
    ArrayList<TuningJob> getAllTuningJobs() throws RemoteException;
}

