package ru.javamentor.springbootcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    public List<User> getUsersList() {
        return  userDao.getUsersList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersList(int count) {
        if (count >= userDao.getUsersList().size()) {
            return userDao.getUsersList();
        } else {
            return userDao.getUsersList().stream().limit(count).collect(Collectors.toList());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }
}
