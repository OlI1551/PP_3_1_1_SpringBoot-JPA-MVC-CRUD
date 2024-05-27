package ru.javamentor.springbootcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.springbootcrud.dao.UserDao;
import ru.javamentor.springbootcrud.model.User;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUsersList() {
        return  userDao.getUsersList();
    }

    @Override
    public List<User> getUsersList(int count) {
        if (count <= 0 || count >= userDao.getUsersList().size()) {
            return userDao.getUsersList();
        } else {
            return userDao.getUsersList().stream().limit(count).collect(Collectors.toList());
        }
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }
}
