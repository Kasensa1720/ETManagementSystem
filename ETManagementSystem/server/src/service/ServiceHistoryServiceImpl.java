// package com.etms.server.servicehistory;

// import java.rmi.RemoteException;
// import java.rmi.server.UnicastRemoteObject;
// import java.util.ArrayList;

// import org.hibernate.Session;
// import org.hibernate.Transaction;
// import org.hibernate.query.Query;

// import com.etms.server.HibernateUtil;

// public class ServiceHistoryServiceImpl extends UnicastRemoteObject implements ServiceHistoryService {

//     public ServiceHistoryServiceImpl() throws RemoteException {
//         super();
//     }

//     @Override
//     public int createServiceHistory(ServiceHistory serviceHistory) throws RemoteException {
//         Transaction tx = null;
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             tx = session.beginTransaction();
//             session.save(serviceHistory);
//             tx.commit();
//             return 1;
//         } catch (Exception e) {
//             if (tx != null) tx.rollback();
//             e.printStackTrace();
//             return 0;
//         }
//     }

//     @Override
//     public ArrayList<ServiceHistory> getAllServiceHistory() throws RemoteException {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             Query<ServiceHistory> query = session.createQuery("from ServiceHistory", ServiceHistory.class);
//             return new ArrayList<>(query.getResultList());
//         } catch (Exception e) {
//             e.printStackTrace();
//             return new ArrayList<>();
//         }
//     }

//     @Override
//     public ServiceHistory getServiceHistoryById(int id) throws RemoteException {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             return session.get(ServiceHistory.class, id);
//         } catch (Exception e) {
//             e.printStackTrace();
//             return null;
//         }
//     }

//     @Override
//     public int updateServiceHistory(ServiceHistory serviceHistory) throws RemoteException {
//         Transaction tx = null;
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             tx = session.beginTransaction();
//             session.update(serviceHistory);
//             tx.commit();
//             return 1;
//         } catch (Exception e) {
//             if (tx != null) tx.rollback();
//             e.printStackTrace();
//             return 0;
//         }
//     }

//     @Override
//     public int deleteServiceHistory(int id) throws RemoteException {
//         Transaction tx = null;
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             ServiceHistory history = session.get(ServiceHistory.class, id);
//             if (history == null) return 0;
//             tx = session.beginTransaction();
//             session.delete(history);
//             tx.commit();
//             return 1;
//         } catch (Exception e) {
//             if (tx != null) tx.rollback();
//             e.printStackTrace();
//             return 0;
//         }
//     }
// }
