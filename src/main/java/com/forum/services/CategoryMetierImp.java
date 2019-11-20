/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forum.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.dao.CategoryDao;
import com.forum.entity.Category;

@Service("categoryMetier")
@Transactional
public class CategoryMetierImp implements CategoryMetier {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void save(Category cat) {
        categoryDao.save(cat);
    }

    @Override
    public void delete(Category cat) {
        categoryDao.delete(cat);
    }

    @Override
    public Category findById(Integer id) {
        return categoryDao.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
public void init(){
    System.out.println("-------CategoryMetierImp-----------");
}
}
