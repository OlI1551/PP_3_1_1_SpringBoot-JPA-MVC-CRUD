package ru.javamentor.springbootcrud.dao;

import ru.javamentor.springbootcrud.model.User;
import java.util.List;


public interface UserDao {
   List<User> getUsersList();
   User getUserById(Long id);
   void addUser(User user);
   void updateUser(User user);
   void deleteUserById(Long id);
}
