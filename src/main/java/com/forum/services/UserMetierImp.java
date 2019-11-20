/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forum.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.dao.UserDao;
import com.forum.entity.User;


@Service("userMetier")
@Transactional
public class UserMetierImp implements UserMetier {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User usr) {
         
        userDao.save(usr);
    }

    @Override
    public void delete(User usr) {
        userDao.delete(usr);
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao commentDao) {
        this.userDao = commentDao;
    }

    
    public void init() {
        System.out.println("-------UserMetierImp-----------");
    }

}
