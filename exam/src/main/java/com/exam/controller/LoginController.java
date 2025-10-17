package com.exam.controller;


import com.exam.entity.UserEntity;
import com.exam.model.ResponseModel;
import com.exam.model.User;
import com.exam.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;


@RestController
public class LoginController {
    @Autowired
    UserService service;

    @GetMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> login(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Basic ")) {

            String base64Credentials = authHeader.substring("Basic ".length());
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(decodedBytes);
            String[] parts = credentials.split(":", 2);
            String username = parts[0];
            String password = parts[1];
            User user = service.authenticate(username, password);
            if(user == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseModel("Login Failed"));
            }else{
                return ResponseEntity.ok(user);
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseModel("No Authorization header"));

        }
    }

//    @GetMapping("/list_users")
//    public List<UserEntity> listUsers(){
//        return service.listAll();
//    }
//
//    @GetMapping("/findByUsername/{username}")
//    public UserEntity userInfo(@PathVariable("username") String username){
//        return service.findByUsername(username);
//    }
}
