// package com.etms.client.TuningJob;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.rmi.RemoteException;
// import java.util.ArrayList;

// import javax.swing.JOptionPane;

// import com.etms.server.tuningjob.TuningJob;
// import com.etms.server.tuningjob.TuningJobService;

// public class TuningJobController {
//     private TuningJobView view;
//     private TuningJobService service;

//     public TuningJobController(TuningJobView view, TuningJobService service) {
//         this.view = view;
//         this.service = service;

//         // Registering action listeners for buttons in the view
//         this.view.registerCreateAction(new RegisterButtonListener());
//         this.view.registerUpdateAction(new UpdateButtonListener());
//         this.view.registerDeleteAction(new DeleteButtonListener());
//         this.view.registerSearchAction(new SearchButtonListener());
//         this.view.registerViewAllAction(new DisplayAllButtonListener());
//     }

//     // Action listener for Register/Create Button
//     class RegisterButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             TuningJob tuningJob = view.getTuningJobDetails();
//             if (tuningJob != null) {
//                 try {
//                     int result = service.createTuningJob(tuningJob);
//                     if (result > 0) {
//                         JOptionPane.showMessageDialog(view, "Tuning Job Created Successfully!");
//                     } else {
//                         JOptionPane.showMessageDialog(view, "Failed to Create Tuning Job!", "Error", JOptionPane.ERROR_MESSAGE);
//                     }
//                 } catch (RemoteException ex) {
//                     JOptionPane.showMessageDialog(view, "Remote error: " + ex.getMessage(), "RMI Error", JOptionPane.ERROR_MESSAGE);
//                 }
//             }
//         }
//     }

//     // Action listener for Update Button
//     class UpdateButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             TuningJob tuningJob = view.getTuningJobDetails();
//             if (tuningJob != null) {
//                 try {
//                     int result = service.updateTuningJob(tuningJob);
//                     if (result > 0) {
//                         JOptionPane.showMessageDialog(view, "Tuning Job Updated Successfully!");
//                     } else {
//                         JOptionPane.showMessageDialog(view, "Failed to Update Tuning Job!", "Error", JOptionPane.ERROR_MESSAGE);
//                     }
//                 } catch (RemoteException ex) {
//                     JOptionPane.showMessageDialog(view, "Remote error: " + ex.getMessage(), "RMI Error", JOptionPane.ERROR_MESSAGE);
//                 }
//             }
//         }
//     }

//     // Action listener for Delete Button
//     class DeleteButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             int tuningId = view.getTuningJobId();
//             try {
//                 int result = service.deleteTuningJob(tuningId);
//                 if (result > 0) {
//                     JOptionPane.showMessageDialog(view, "Tuning Job Deleted Successfully!");
//                 } else {
//                     JOptionPane.showMessageDialog(view, "Failed to Delete Tuning Job!", "Error", JOptionPane.ERROR_MESSAGE);
//                 }
//             } catch (RemoteException ex) {
//                 JOptionPane.showMessageDialog(view, "Remote error: " + ex.getMessage(), "RMI Error", JOptionPane.ERROR_MESSAGE);
//             }
//         }
//     }

//     // Action listener for Search Button
//     class SearchButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             Integer tuningId = view.getTuningJobId();
//             if (tuningId == null) {
//                 return;
//             }
//             try {
//                 TuningJob tuningJob = service.getTuningJobById(tuningId);
//                 if (tuningJob != null) {
//                     view.displayTuningJob(tuningJob);
//                 } else {
//                     JOptionPane.showMessageDialog(view, "Tuning Job Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
//                 }
//             } catch (RemoteException ex) {
//                 JOptionPane.showMessageDialog(view, "Remote error: " + ex.getMessage(), "RMI Error", JOptionPane.ERROR_MESSAGE);
//             }
//         }
//     }

//     // Action listener for View All Button
//     class DisplayAllButtonListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             try {
//                 ArrayList<TuningJob> tuningJobs = service.getAllTuningJobs();
//                 view.displayAllTuningJobs(tuningJobs);
//             } catch (RemoteException ex) {
//                 JOptionPane.showMessageDialog(view, "Remote error: " + ex.getMessage(), "RMI Error", JOptionPane.ERROR_MESSAGE);
//             }
//         }
//     }
// }
