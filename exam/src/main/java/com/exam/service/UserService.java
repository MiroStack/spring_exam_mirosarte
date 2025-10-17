package com.exam.service;

import com.exam.dao.UserDao;
import com.exam.entity.UserEntity;
import com.exam.model.User;
import com.exam.util.MD5Util;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao dao;

    @Transactional(readOnly = true)
    public List<UserEntity> listAll() {
        return dao.getAllUsers();
    }

    @Transactional(readOnly = true)
    public UserEntity findByUsername(String username){
        return dao.findByUsername(username);
    }
    @Transactional(readOnly = true)
    public User authenticate(String username, String password){
        try{
            UserEntity userEntity = findByUsername(username);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String birthdateStr = sdf.format(userEntity.getBirthdate());
            if(userEntity.getPassword().equals(MD5Util.hash(password))){
                return new User(
                        userEntity.getLastname(),
                        userEntity.getFirstname(),
                        userEntity.getMiddlename(),
                        birthdateStr
                );
            }else{
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }
}
