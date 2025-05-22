package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.ServiceHistory;
import utils.HibernateUtil;

public class ServiceHistoryDaoHibernate {
    public boolean createServiceHistory(ServiceHistory serviceHistory) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(serviceHistory);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<ServiceHistory> findAllServiceHistory() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from ServiceHistory", ServiceHistory.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<ServiceHistory> findServiceHistoryByVehicle(int vehicleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "from ServiceHistory where vehicleId = :vehicleId", ServiceHistory.class)
                .setParameter("vehicleId", vehicleId)
                .list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<ServiceHistory> findServiceHistoryByTuningJob(int tuningJobId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "from ServiceHistory where tuningJobId = :tuningJobId", ServiceHistory.class)
                .setParameter("tuningJobId", tuningJobId)
                .list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public boolean deleteServiceHistory(int serviceId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ServiceHistory serviceHistory = session.get(ServiceHistory.class, serviceId);
            if (serviceHistory != null) {
                session.delete(serviceHistory);
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
}