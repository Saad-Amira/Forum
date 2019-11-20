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

import com.forum.entity.FilDiscussion;

@Service("filDiscussionDao")
@Transactional
public class FilDiscussionDaoImp implements FilDiscussionDao {

    @Autowired
    private SessionFactory SessionFactory;

    @Override
    public void save(FilDiscussion cat) {
        SessionFactory.getCurrentSession().beginTransaction();
        SessionFactory.getCurrentSession().saveOrUpdate(cat);
        SessionFactory.getCurrentSession().beginTransaction().commit();
    }

    @Override
    public void delete(FilDiscussion filDisc) {
        SessionFactory.getCurrentSession().beginTransaction();

//        SessionFactory.getCurrentSession().delete(cat);
        Query query = SessionFactory.getCurrentSession().createQuery("delete FilDiscussion c where c.idFilDiscussion = :id");
        query.setParameter("id", filDisc.getIdFilDiscussion());
        query.executeUpdate();

        SessionFactory.getCurrentSession().beginTransaction().commit();
    }

    @Override
    public FilDiscussion findById(Integer id) {
        SessionFactory.getCurrentSession().beginTransaction();
        return (FilDiscussion) SessionFactory.getCurrentSession().load(FilDiscussion.class, id);
//          return (FilDiscussion) SessionFactory.getCurrentSession().get(FilDiscussion.class, id);
    }

    @Override
    public List<FilDiscussion> findAll() {
        SessionFactory.getCurrentSession().beginTransaction();
        String query = "  from FilDiscussion N  order by dateDiscussion desc  where type like 'public' and status like 'activated'";

        return SessionFactory.getCurrentSession().createQuery(query).list();
    }

    public void setSessionFactory(SessionFactory SessionFactory) {
        this.SessionFactory = SessionFactory;
    }

    @Override
    public List<FilDiscussion> search(String recherche) {
        String rechNonSpace = recherche.replaceAll("\\s+", "");
        String parames = "1=0";
        String[] parts = recherche.split(" ");
        if (rechNonSpace.length() > 0) {
            for (int i = 0; i < parts.length; i++) {
                parames = parames + " or c.contents LIKE :param" + i;

            }
        }
        SessionFactory.getCurrentSession().beginTransaction();
        Query query = SessionFactory.getCurrentSession().createQuery("  from FilDiscussion c where " + parames + " order by c.dateDiscussion desc");
        if (rechNonSpace.length() > 0) {
            for (int i = 0; i < parts.length; i++) {

                query.setParameter("param" + i, "%" + parts[i] + "%");
            }
        }
        return query.list();
    }

    @Override
    public List<FilDiscussion> findByCategory(int idCategory) {
        SessionFactory.getCurrentSession().beginTransaction();
        Query query = SessionFactory.getCurrentSession().createQuery("  from FilDiscussion f where f.category.idCategory=:idCat   order by f.dateDiscussion desc");
        query.setParameter("idCat", idCategory);
        return query.list();
    }

    public void init() {
        System.out.println("-------FilDiscussion-----------");
    }
}
