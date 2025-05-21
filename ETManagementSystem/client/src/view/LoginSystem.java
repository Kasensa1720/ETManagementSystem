package view;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import utils.OTPUtil;
import utils.SessionManager;
import utils.Components;
import model.User;
import model.User.UserType;

public class LoginSystem extends JPanel {
    private static final Color PRIMARY_BG = new Color(248, 248, 248);
    private static final Color BUTTON_BG = new Color(70, 130, 180);
    private static final Color BUTTON_HOVER = new Color(100, 150, 200);
    private static final Color TEXT_COLOR = new Color(60, 60, 60);
    private static final Color BORDER_COLOR = new Color(200, 200, 200);
    private static final Color LINK_COLOR = new Color(0, 120, 215);

    private static final Font PRIMARY_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    private static final int BUTTON_CORNER_RADIUS = 12;
    private static final Dimension BUTTON_SIZE = new Dimension(280, 50);
    private static final Dimension FIELD_SIZE = new Dimension(300, 40);

    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField otpField;
    private JButton loginButton;
    private JLabel switchToSignup;
    private JPanel cards;
    private CardLayout cardLayout;

    private JPanel loginPanel;
    private JPanel otpPanel;
    private JPanel signupPanel;

    // Signup fields
    private JTextField signupEmailField;
    private JPasswordField signupPasswordField;
    private JTextField signupNameField;

    public LoginSystem() {
        setLayout(new BorderLayout());
        setBackground(PRIMARY_BG);
        initializeUI();
    }

    private void initializeUI() {

        
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setBackground(PRIMARY_BG);

        loginPanel = createLoginPanel();
        otpPanel = createOTPPanel();
        signupPanel = createSignupPanel();

        cards.add(loginPanel, "login");
        cards.add(otpPanel, "otp");
        cards.add(signupPanel, "signup");

        add(cards, BorderLayout.CENTER);
        cardLayout.show(cards, "login");
    }

    private JPanel createLoginPanel() {
        JPanel panel = createBasePanel();
        GridBagConstraints gbc = createGBC();

        JLabel titleLabel = createTitle("Login to Your Account");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        addLabeledField(panel, gbc, 1, "Email:", emailField = createModernTextField());
        addLabeledField(panel, gbc, 2, "Password:", passwordField = createPasswordField());

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginButton = Components.createRoundedButton("Login", BUTTON_HOVER, BUTTON_BG, BUTTON_CORNER_RADIUS,
                BUTTON_SIZE, BUTTON_FONT);
        loginButton.addActionListener(this::handleLogin);
        panel.add(loginButton, gbc);

        gbc.gridy = 4;
        switchToSignup = createLinkLabel("Don't have an account? Sign up", () -> cardLayout.show(cards, "signup"));
        panel.add(switchToSignup, gbc);

        return panel;
    }

    private JPanel createOTPPanel() {
        JPanel panel = createBasePanel();
        GridBagConstraints gbc = createGBC();

        JLabel titleLabel = createTitle("Verify Your Identity");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        JLabel instruction = new JLabel("We've sent an OTP to your email");
        instruction.setFont(LABEL_FONT);
        instruction.setForeground(TEXT_COLOR);
        panel.add(instruction, gbc);

        gbc.gridwidth = 1;
        addLabeledField(panel, gbc, 2, "Enter OTP:", otpField = createModernTextField());

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton verifyButton = Components.createRoundedButton("Verify OTP", BUTTON_HOVER, BUTTON_BG,
                BUTTON_CORNER_RADIUS, BUTTON_SIZE, BUTTON_FONT);
        verifyButton.addActionListener(this::handleOTPVerification);
        panel.add(verifyButton, gbc);

        gbc.gridy = 4;
        JLabel resendLink = createLinkLabel("Resend OTP", () -> sendOTP(emailField.getText()));
        panel.add(resendLink, gbc);

        return panel;
    }

    private JPanel createSignupPanel() {
        JPanel panel = createBasePanel();
        GridBagConstraints gbc = createGBC();

        JLabel titleLabel = createTitle("Create New Account");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        addLabeledField(panel, gbc, 1, "Name:", signupNameField = createModernTextField());
        addLabeledField(panel, gbc, 2, "Email:", signupEmailField = createModernTextField());
        addLabeledField(panel, gbc, 3, "Password:", signupPasswordField = createPasswordField());

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton signupButton = Components.createRoundedButton("Create Account", BUTTON_HOVER, BUTTON_BG,
                BUTTON_CORNER_RADIUS, BUTTON_SIZE, BUTTON_FONT);
        signupButton.addActionListener(this::handleSignUp);
        panel.add(signupButton, gbc);

        gbc.gridy = 5;
        JButton backToLogin = Components.createRoundedButton("Back to Login", BUTTON_HOVER, BUTTON_BG,
                BUTTON_CORNER_RADIUS, BUTTON_SIZE, BUTTON_FONT);
        backToLogin.addActionListener(_ -> cardLayout.show(cards, "login"));
        panel.add(backToLogin, gbc);

        return panel;
    }

    private JPanel createBasePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
        panel.setBackground(PRIMARY_BG);
        return panel;
    }

    private GridBagConstraints createGBC() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    private JLabel createTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(HEADER_FONT);
        label.setForeground(TEXT_COLOR);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private void addLabeledField(JPanel panel, GridBagConstraints gbc, int y, String labelText, JComponent field) {
        gbc.gridy = y;
        gbc.gridx = 0;
        JLabel label = new JLabel(labelText);
        label.setFont(LABEL_FONT);
        label.setForeground(TEXT_COLOR);
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private JTextField createModernTextField() {
        JTextField field = new JTextField();
        styleField(field);
        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField();
        styleField(field);
        return field;
    }

    private void styleField(JTextComponent field) {
        field.setFont(PRIMARY_FONT);
        field.setBackground(Color.WHITE);
        field.setForeground(TEXT_COLOR);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        field.setPreferredSize(FIELD_SIZE);
    }

    private JLabel createLinkLabel(String text, Runnable onClick) {
        JLabel label = new JLabel("<html><u>" + text + "</u></html>");
        label.setFont(LABEL_FONT);
        label.setForeground(LINK_COLOR);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                onClick.run();
            }
        });
        return label;
    }

    private void sendOTP(String email) {
        OTPUtil.sendOTP(email);
        JOptionPane.showMessageDialog(this, "OTP sent to your email", "OTP Sent", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleSignUp(ActionEvent e){
        String name = signupNameField.getText();
         String email = signupEmailField.getText();
        String password = new String(signupPasswordField.getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Input Required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

         try {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setFullName(name);
            user.setUserType(UserType.ADMIN);

            if (ClientApp.userService.createUser(user)) {
                sendOTP(email);
                cardLayout.show(cards, "otp");
            } else {
                JOptionPane.showMessageDialog(this, "Unable to create user", "Account Creation Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (RemoteException ex) {
            ex.printStackTrace(); // See the root cause
            handleServerError(ex);
        }


    }

    private void handleLogin(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password", "Input Required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (ClientApp.userService.authenticateUser(email, password)) {
                sendOTP(email);
                cardLayout.show(cards, "otp");

            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password", "Authentication Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (RemoteException ex) {
            handleServerError(ex);
        }
    }

    private void handleOTPVerification(ActionEvent e) {
        String otp = otpField.getText();

        if (otp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the OTP", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (OTPUtil.verifyOTP(otp)) {
            try {
                User user = ClientApp.userService.getUserByEmail(emailField.getText());
                SessionManager.login(user);
                ClientApp.launchMainApplication();
                closeLoginWindow();
            } catch (RemoteException ex) {
                handleServerError(ex);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Invalid OTP. Please try again.", "Verification Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void closeLoginWindow() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.dispose();
    }

    private void handleServerError(Exception ex) {
        System.out.println(ex);
                    ex.printStackTrace(); // 👈 this is key

        JOptionPane.showMessageDialog(this, "Server communication error: " + ex.getMessage(), "Connection Error",
                JOptionPane.ERROR_MESSAGE);

        if (!ClientApp.reconnectToServer()) {
            JOptionPane.showMessageDialog(this, "Failed to reconnect to server. Application will exit.", "Fatal Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
