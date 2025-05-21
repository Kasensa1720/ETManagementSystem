package utils;


import java.util.Random;
import javax.swing.*;

public class OTPUtil {
    private static String generatedOTP;
    private static long otpExpiryTime;
    
    public static void sendOTP(String emailOrPhone) {
        // Generate 6-digit OTP
        generatedOTP = String.format("%06d", new Random().nextInt(999999));
        otpExpiryTime = System.currentTimeMillis() + 300000; // 5 minutes expiry
        
        // In real app, send via email/SMS
        System.out.println("OTP for " + emailOrPhone + ": " + generatedOTP);
    }
    
    public static boolean verifyOTP(String userInput) {
        if (System.currentTimeMillis() > otpExpiryTime) {
            JOptionPane.showMessageDialog(null, "OTP expired");
            return false;
        }
        return userInput.equals(generatedOTP);
    }
}
