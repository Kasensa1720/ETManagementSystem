// package com.etms.client.Vehicle;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.rmi.RemoteException;
// import java.util.ArrayList;

// import javax.swing.JOptionPane;

// import com.etms.server.vehicle.Vehicle;
// import com.etms.server.vehicle.VehicleService;

// public class VehicleController {
//     private VehicleView view;
//     private VehicleService service;  // Replaces DAO with RMI service

//     public VehicleController(VehicleView view, VehicleService service) {
//         this.view = view;
//         this.service = service;

//         this.view.registerCreateAction(new RegisterButtonListener());
//         this.view.registerUpdateAction(new UpdateButtonListener());
//         this.view.registerDeleteAction(new DeleteButtonListener());
//         this.view.registerSearchAction(new SearchButtonListener());
//         this.view.registerViewAllAction(new DisplayAllButtonListener());
//     }

//     class RegisterButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             Vehicle vehicle = view.getVehicleDetails();
//             if (vehicle != null) {
//                 try {
//                     int result = service.createVehicle(vehicle); // RMI call
//                     if (result > 0) {
//                         JOptionPane.showMessageDialog(view, "Vehicle Registered Successfully!");
//                     } else {
//                         JOptionPane.showMessageDialog(view, "Failed to Register Vehicle!", "Error", JOptionPane.ERROR_MESSAGE);
//                     }
//                 } catch (RemoteException ex) {
//                     JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Remote Exception", JOptionPane.ERROR_MESSAGE);
//                     ex.printStackTrace();
//                 }
//             }
//         }
//     }

//     class UpdateButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             Vehicle vehicle = view.getVehicleDetails();
//             if (vehicle != null) {
//                 try {
//                     int result = service.updateVehicle(vehicle); // RMI call
//                     if (result > 0) {
//                         JOptionPane.showMessageDialog(view, "Vehicle Updated Successfully!");
//                     } else {
//                         JOptionPane.showMessageDialog(view, "Failed to Update Vehicle!", "Error", JOptionPane.ERROR_MESSAGE);
//                     }
//                 } catch (RemoteException ex) {
//                     JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Remote Exception", JOptionPane.ERROR_MESSAGE);
//                     ex.printStackTrace();
//                 }
//             }
//         }
//     }

//     class DeleteButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             int vehicleId = view.getVehicleId();
//             try {
//                 int result = service.deleteVehicle(vehicleId); // RMI call
//                 if (result > 0) {
//                     JOptionPane.showMessageDialog(view, "Vehicle Deleted Successfully!");
//                 } else {
//                     JOptionPane.showMessageDialog(view, "Failed to Delete Vehicle!", "Error", JOptionPane.ERROR_MESSAGE);
//                 }
//             } catch (RemoteException ex) {
//                 JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Remote Exception", JOptionPane.ERROR_MESSAGE);
//                 ex.printStackTrace();
//             }
//         }
//     }

//     class SearchButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             int vehicleId = view.getVehicleId();
//             try {
//                 Vehicle vehicle = service.getVehicleById(vehicleId); // RMI call
//                 if (vehicle != null) {
//                     view.displayVehicle(vehicle);
//                 } else {
//                     JOptionPane.showMessageDialog(view, "Vehicle Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
//                 }
//             } catch (RemoteException ex) {
//                 JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Remote Exception", JOptionPane.ERROR_MESSAGE);
//                 ex.printStackTrace();
//             }
//         }
//     }

//     class DisplayAllButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             try {
//                 ArrayList<Vehicle> vehicles = service.getAllVehicles(); // RMI call
//                 view.displayAllVehicles(vehicles);
//             } catch (RemoteException ex) {
//                 JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Remote Exception", JOptionPane.ERROR_MESSAGE);
//                 ex.printStackTrace();
//             }
//         }
//     }
// }
