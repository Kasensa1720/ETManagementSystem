package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.TuningJob;
import utils.HibernateUtil;

public class TuningJobDaoHibernate {
    public boolean createTuningJob(TuningJob tuningJob) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tuningJob);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTuningJob(TuningJob tuningJob) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(tuningJob);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public TuningJob findTuningJob(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TuningJob.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteTuningJob(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TuningJob tuningJob = session.get(TuningJob.class, id);
            if (tuningJob != null) {
                session.delete(tuningJob);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<TuningJob> findAllTuningJobs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from TuningJob", TuningJob.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<TuningJob> findTuningJobsByVehicle(int vehicleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "from TuningJob where vehicleId = :vehicleId", TuningJob.class)
                .setParameter("vehicleId", vehicleId)
                .list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}