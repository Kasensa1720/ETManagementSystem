package view;


import javax.swing.*;


import remote.UserService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientApp {

    public static UserService userService;
    
    // Application State
    private static boolean isConnected = false;
    
    public static void main(String[] args) {
        initializeApplication();
    }
    
    private static void initializeApplication() {
        setupLookAndFeel();
        connectToServer();
        if (isConnected) {
            showLoginScreen();
        } else {
            showConnectionErrorAndExit();
        }
    }
    
    private static void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Warning: Could not set system look and feel");
        }
    }
    
    public static void connectToServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 5000);
            userService = (UserService) registry.lookup("UserService");
            isConnected = true;
            System.out.println("Connected to server successfully");
        } catch (Exception e) {
            handleConnectionError(e);
        }
    }
    
    private static void handleConnectionError(Exception e) {
        isConnected = false;
        System.err.println("Connection error: " + e.getMessage());
    }
    
    private static void showConnectionErrorAndExit() {
        JOptionPane.showMessageDialog(null,
            "Failed to connect to server. Application will exit.",
            "Connection Error",
            JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
    
    private static void showLoginScreen() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ET Management System - Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new LoginSystem());
            frame.setVisible(true);
        });
    }
    
    public static void launchMainApplication() {
        SwingUtilities.invokeLater(() -> {
            MainDashboard dashboard = new MainDashboard();
            dashboard.setVisible(true);
            dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
        });
    }
    
    public static boolean reconnectToServer() {
        try {
            connectToServer();
            return isConnected;
        } catch (Exception e) {
            return false;
        }
    }
    
}