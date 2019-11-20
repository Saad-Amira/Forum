/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.forum.dao;

import java.util.List;

import com.forum.entity.Category;

/**
 *
 * @author amira.saad
 */
public interface CategoryDao {

     public void save(Category cat);
      public void delete(Category cat);
        public Category findById(Integer id);
            public List<Category> findAll();
         
}
