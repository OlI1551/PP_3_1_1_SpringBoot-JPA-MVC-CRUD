package ru.javamentor.springbootcrud.dao;

import org.springframework.stereotype.Repository;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import java.util.List;
import ru.javamentor.springbootcrud.model.User;
import jakarta.persistence.EntityNotFoundException;


@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public List<User> getUsersList() {
      return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
   }

   @Override
   public User getUserById(Long id) {
      if (entityManager.find(User.class, id) == null) {
         throw new EntityNotFoundException(String.format("Пользователь c ID '%s'  не найден", id));
      }
      return entityManager.find(User.class, id);
   }

   @Override
   public void addUser(User user) {
      entityManager.persist(user);
   }

   @Override
   public void updateUser(User user) {
      if (entityManager.find(User.class, user.getId()) == null) {
         throw new EntityNotFoundException(String.format("Пользователь c ID '%s'  не найден", user.getId()));
      }
      entityManager.merge(user);
   }

   @Override
   public void deleteUserById(Long id) {
      User user = entityManager.find(User.class, id);
      if (user == null) {
         throw new EntityNotFoundException(String.format("Пользователь c ID '%s'  не найден", id));
      }
      entityManager.remove(user);
   }
}
