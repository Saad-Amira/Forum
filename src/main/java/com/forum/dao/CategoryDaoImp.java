/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forum.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.entity.Category;

@Service("categoryDao")
@Transactional
public class CategoryDaoImp implements CategoryDao {

    @Autowired
    private SessionFactory SessionFactory;

    @Override
    public void save(Category cat) {
        SessionFactory.getCurrentSession().beginTransaction();
        SessionFactory.getCurrentSession().saveOrUpdate(cat);
        SessionFactory.getCurrentSession().beginTransaction().commit();
    }

    @Override
    public void delete(Category cat) {
        SessionFactory.getCurrentSession().beginTransaction();

//        SessionFactory.getCurrentSession().delete(cat);
        Query query = SessionFactory.getCurrentSession().createQuery("delete Category c where c.idCategory = :id");
        query.setParameter("id", cat.getIdCategory());
        query.executeUpdate();

        SessionFactory.getCurrentSession().beginTransaction().commit();
    }

    @Override
    public Category findById(Integer id) {
        SessionFactory.getCurrentSession().beginTransaction();
        return (Category) SessionFactory.getCurrentSession().load(Category.class, id);
//          return (Category) SessionFactory.getCurrentSession().get(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        SessionFactory.getCurrentSession().beginTransaction();
        String query = "  from Category N where status like 'activated'";

        return SessionFactory.getCurrentSession().createQuery(query).list();
    }

   

    public SessionFactory getSessionFactory() {
        return SessionFactory;
    }

    public void setSessionFactory(SessionFactory SessionFactory) {
        this.SessionFactory = SessionFactory;
    }

    public void init() {
        System.out.println("-------CategoryDaoImp-----------");
    }
}
