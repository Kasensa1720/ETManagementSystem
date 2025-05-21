package remote;

import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserService extends Remote {
    boolean createUser(User user) throws RemoteException;
    boolean authenticateUser(String email, String password) throws RemoteException;
    User getUserByEmail(String email) throws RemoteException;
    boolean deleteUser(int id) throws RemoteException;
    List<User> findAllUsers() throws RemoteException;
    User findUser(int id) throws RemoteException;
    boolean updateUser(User user) throws RemoteException;
}
