package com.etms.server.tuningjob;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.etms.server.HibernateUtil;

public class TuningJobServiceImpl extends UnicastRemoteObject implements TuningJobService {

    public TuningJobServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int createTuningJob(TuningJob tuningJob) throws RemoteException {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(tuningJob);
            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateTuningJob(TuningJob tuningJob) throws RemoteException {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(tuningJob);
            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteTuningJob(int tuningJobId) throws RemoteException {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TuningJob job = session.get(TuningJob.class, tuningJobId);
            if (job == null) return 0;
            tx = session.beginTransaction();
            session.delete(job);
            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public TuningJob getTuningJobById(int tuningJobId) throws RemoteException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TuningJob.class, tuningJobId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
public ArrayList<TuningJob> getAllTuningJobs() throws RemoteException {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        Query<TuningJob> query = session.createQuery("from TuningJob", TuningJob.class);
        return new ArrayList<>(query.list()); // Convert List to ArrayList explicitly
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

}
