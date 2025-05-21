package utils;

import model.User;

public class SessionManager {
    private static User currentUser;
    private static long lastActivityTime;
    
    public static void login(User user) {
        currentUser = user;
        lastActivityTime = System.currentTimeMillis();
    }
    
    public static void logout() {
        currentUser = null;
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static void updateActivity() {
        lastActivityTime = System.currentTimeMillis();
    }
    
    public static boolean isSessionActive() {
        return currentUser != null && 
               (System.currentTimeMillis() - lastActivityTime) < 1800000; // 30 min timeout
    }
}