/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forum.dao;

import java.util.List;

import com.forum.entity.User;

/**
 *
 * @author amira.saad
 */
public interface UserDao {

    public void save(User usr);

    public void delete(User usr);

    public User findById(Integer id);
}
