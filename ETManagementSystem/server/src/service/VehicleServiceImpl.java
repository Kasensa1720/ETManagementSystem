// package com.etms.server.vehicle;

// import java.rmi.RemoteException;
// import java.rmi.server.UnicastRemoteObject;
// import java.util.ArrayList;

// import org.hibernate.Session;
// import org.hibernate.Transaction;
// import org.hibernate.query.Query;

// import com.etms.server.HibernateUtil;

// public class VehicleServiceImpl extends UnicastRemoteObject implements VehicleService {

//     public VehicleServiceImpl() throws RemoteException {
//         super();
//     }

//     @Override
//     public int createVehicle(Vehicle vehicle) throws RemoteException {
//         Transaction tx = null;
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             tx = session.beginTransaction();
//             session.save(vehicle);
//             tx.commit();
//             return 1;
//         } catch (Exception e) {
//             if (tx != null) tx.rollback();
//             e.printStackTrace();
//             return 0;
//         }
//     }

//     @Override
//     public int updateVehicle(Vehicle vehicle) throws RemoteException {
//         Transaction tx = null;
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             tx = session.beginTransaction();
//             session.update(vehicle);
//             tx.commit();
//             return 1;
//         } catch (Exception e) {
//             if (tx != null) tx.rollback();
//             e.printStackTrace();
//             return 0;
//         }
//     }

//     @Override
//     public int deleteVehicle(int vehicleId) throws RemoteException {
//         Transaction tx = null;
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             Vehicle vehicle = session.get(Vehicle.class, vehicleId);
//             if (vehicle == null) return 0;
//             tx = session.beginTransaction();
//             session.delete(vehicle);
//             tx.commit();
//             return 1;
//         } catch (Exception e) {
//             if (tx != null) tx.rollback();
//             e.printStackTrace();
//             return 0;
//         }
//     }

//     @Override
//     public Vehicle getVehicleById(int vehicleId) throws RemoteException {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             return session.get(Vehicle.class, vehicleId);
//         } catch (Exception e) {
//             e.printStackTrace();
//             return null;
//         }
//     }

//     @Override
//     public ArrayList<Vehicle> getAllVehicles() throws RemoteException {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             Query<Vehicle> query = session.createQuery("from Vehicle", Vehicle.class);
//             return new ArrayList<>(query.list());
//         } catch (Exception e) {
//             e.printStackTrace();
//             return null;
//         }
//     }
// }
