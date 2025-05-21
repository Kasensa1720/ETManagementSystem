// package com.etms.client.Customer;


// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.List;

// import javax.swing.JOptionPane;

// import com.etms.server.customer.Customer;
// import com.etms.server.customer.CustomerService;

// public class CustomerController {
//     private CustomerView view;
//     private CustomerService service;

//     public CustomerController(CustomerView view) {
//         this.view = view;
//         try {
//             service = (CustomerService) java.rmi.Naming.lookup("rmi://localhost:1099/CustomerService");
//         } catch (Exception e) {
//             e.printStackTrace();
//             JOptionPane.showMessageDialog(view, "Failed to connect to CustomerService via RMI.", "Error", JOptionPane.ERROR_MESSAGE);
//         }

//         this.view.registerCreateAction(new RegisterButtonListener());
//         this.view.registerUpdateAction(new UpdateButtonListener());
//         this.view.registerDeleteAction(new DeleteButtonListener());
//         this.view.registerSearchAction(new SearchButtonListener());
//         this.view.registerViewAllAction(new DisplayAllButtonListener());
//     }

//     class RegisterButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             Customer customer = view.getCustomerDetails();
//             if (customer != null) {
//                 try {
//                     service.createCustomer(customer);
//                     JOptionPane.showMessageDialog(view, "Customer Registered Successfully!");
//                 } catch (Exception ex) {
//                     ex.printStackTrace();
//                     JOptionPane.showMessageDialog(view, "Failed to Register Customer!", "Error", JOptionPane.ERROR_MESSAGE);
//                 }
//             }
//         }
//     }

//     class UpdateButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             Customer customer = view.getCustomerDetails();
//             if (customer != null) {
//                 try {
//                     service.updateCustomer(customer);
//                     JOptionPane.showMessageDialog(view, "Customer Updated Successfully!");
//                 } catch (Exception ex) {
//                     ex.printStackTrace();
//                     JOptionPane.showMessageDialog(view, "Failed to Update Customer!", "Error", JOptionPane.ERROR_MESSAGE);
//                 }
//             }
//         }
//     }

//     class DeleteButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             int id = view.getId();
//             try {
//                 service.deleteCustomer(id);
//                 JOptionPane.showMessageDialog(view, "Customer Deleted Successfully!");
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//                 JOptionPane.showMessageDialog(view, "Failed to Delete Customer!", "Error", JOptionPane.ERROR_MESSAGE);
//             }
//         }
//     }

//     class SearchButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             int id = view.getId();
//             try {
//                 Customer customer = service.getCustomerById(id);
//                 if (customer != null) {
//                     view.displayCustomer(customer);
//                 } else {
//                     JOptionPane.showMessageDialog(view, "Customer Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
//                 }
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//                 JOptionPane.showMessageDialog(view, "Error searching for Customer!", "Error", JOptionPane.ERROR_MESSAGE);
//             }
//         }
//     }

//     class DisplayAllButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             try {
//                 List<Customer> customers = service.getAllCustomers();
//                 view.displayAllCustomers(customers);
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//                 JOptionPane.showMessageDialog(view, "Error retrieving all customers!", "Error", JOptionPane.ERROR_MESSAGE);
//             }
//         }
//     }
// }
