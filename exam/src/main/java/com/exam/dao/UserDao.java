package com.exam.dao;

import com.exam.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
  @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

  public List<UserEntity> getAllUsers(){
      return sessionFactory.getCurrentSession()
              .createQuery("from UserEntity", UserEntity.class)
              .list();
  }

    public UserEntity findByUsername(String username) {
        String hql = "FROM UserEntity u WHERE u.username = :username";
        return entityManager.createQuery(hql, UserEntity.class)
                .setParameter("username", username)
                .getSingleResult();
    }


}
