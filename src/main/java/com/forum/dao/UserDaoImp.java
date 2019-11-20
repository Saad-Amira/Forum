/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.forum.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.entity.User;

/**
 *
 * @author amira.saad
 */
@Service("userDao")
@Transactional
public class UserDaoImp implements UserDao{
@Autowired
    private SessionFactory SessionFactory;

    @Override
    public void save(User usr) {
        SessionFactory.getCurrentSession().beginTransaction();
        SessionFactory.getCurrentSession().saveOrUpdate(usr);
        SessionFactory.getCurrentSession().beginTransaction().commit();   }
   
    @Override
    public void delete(User usr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public SessionFactory getSessionFactory() {
        return SessionFactory;
    }

    public void setSessionFactory(SessionFactory SessionFactory) {
        this.SessionFactory = SessionFactory;
    }
    public void init() {
        System.out.println("-------UserDaoImp-----------");
    }

}
