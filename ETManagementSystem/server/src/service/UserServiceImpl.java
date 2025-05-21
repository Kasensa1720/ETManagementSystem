package service;

import dao.UserDaoHibernate;
import model.User;
import remote.UserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    private final UserDaoHibernate userDao;

    public UserServiceImpl() throws RemoteException {
        userDao = new UserDaoHibernate();
    }

    @Override
    public boolean createUser(User user) throws RemoteException {
        return userDao.save(user);
    }

    @Override
    public boolean authenticateUser(String email, String password) throws RemoteException {
        User user = userDao.getByEmail(email);
        return user != null && user.getPassword().equals(password); // Use hashing in production!
    }

    @Override
    public User getUserByEmail(String email) throws RemoteException {
        return userDao.getByEmail(email);
    }

    @Override
    public boolean deleteUser(int id) throws RemoteException {
        return userDao.delete(id);
    }

    @Override
    public List<User> findAllUsers() throws RemoteException {
        return userDao.getAll();
    }

    @Override
    public User findUser(int id) throws RemoteException {
        return userDao.getById(id);
    }

    @Override
    public boolean updateUser(User user) throws RemoteException {
        return userDao.update(user);
    }
}
