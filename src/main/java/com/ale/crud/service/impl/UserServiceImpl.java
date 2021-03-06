package com.ale.crud.service.impl;

import com.ale.crud.bean.User;
import com.ale.crud.dao.UserDAO;
import com.ale.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

    //采用这种方式注入，为了设置父类中的dao
    private UserDAO userDAO;
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        super.setBaseDAO(userDAO);
        this.userDAO = userDAO;
    }



}
