// package com.etms.server.customer;



// import org.hibernate.Session;
// import org.hibernate.Transaction;
// import org.hibernate.query.Query;

// import com.etms.server.HibernateUtil;

// import java.rmi.server.UnicastRemoteObject;
// import java.rmi.RemoteException;
// import java.util.List;

// public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService {

//     public CustomerServiceImpl() throws RemoteException {
//         super();
//     }

//     @Override
//     public int createCustomer(Customer customer) throws RemoteException {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             Transaction tx = session.beginTransaction();
//             session.save(customer);
//             tx.commit();
//             return 1;
//         } catch (Exception e) {
//             e.printStackTrace();
//             return 0;
//         }
//     }

//     @Override
//     public Customer getCustomerById(int customerId) throws RemoteException {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             return session.get(Customer.class, customerId);
//         }
//     }

//     @Override
//     public List<Customer> getAllCustomers() throws RemoteException {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             return session.createQuery("from Customer", Customer.class).list();
//         }
//     }

//     @Override
//     public int updateCustomer(Customer customer) throws RemoteException {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             Transaction tx = session.beginTransaction();
//             session.update(customer);
//             tx.commit();
//             return 1;
//         } catch (Exception e) {
//             e.printStackTrace();
//             return 0;
//         }
//     }

//     @Override
//     public int deleteCustomer(int customerId) throws RemoteException {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             Transaction tx = session.beginTransaction();
//             Customer customer = session.get(Customer.class, customerId);
//             if (customer != null) {
//                 session.delete(customer);
//                 tx.commit();
//                 return 1;
//             }
//             return 0;
//         } catch (Exception e) {
//             e.printStackTrace();
//             return 0;
//         }
//     }
// }

