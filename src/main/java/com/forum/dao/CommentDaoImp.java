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

import com.forum.entity.Comment;

@Service("commentDao")
@Transactional
public class CommentDaoImp implements CommentDao {

    @Autowired
    private SessionFactory SessionFactory;

    @Override
    public void save(Comment cmt) {
        System.out.println("-----------------------------commentaire dao save------------ ");
        System.out.println(cmt + " idfd:" + cmt.getFilDiscussion().getIdFilDiscussion());
        SessionFactory.getCurrentSession().beginTransaction();
        SessionFactory.getCurrentSession().saveOrUpdate(cmt);
        SessionFactory.getCurrentSession().beginTransaction().commit();
        System.out.println("-----------------------------commentaire Fin------------ ");
    }

    @Override
    public void delete(Comment cmt) {
        SessionFactory.getCurrentSession().beginTransaction();

//        SessionFactory.getCurrentSession().delete(cat);
        Query query = SessionFactory.getCurrentSession().createQuery("delete Comment c where c.idComment = :id");
        query.setParameter("id", cmt.getIdComment());
        query.executeUpdate();

        SessionFactory.getCurrentSession().beginTransaction().commit();
    }

    @Override
    public Comment findById(Integer id) {
        SessionFactory.getCurrentSession().beginTransaction();
        return (Comment) SessionFactory.getCurrentSession().load(Comment.class, id);
//          return (Category) SessionFactory.getCurrentSession().get(Category.class, id);  }
    }

    public SessionFactory getSessionFactory() {
        return SessionFactory;
    }

    public void setSessionFactory(SessionFactory SessionFactory) {
        this.SessionFactory = SessionFactory;
    }

    @Override
    public List<Comment> findAll(int idFilleDescussion) {
        SessionFactory.getCurrentSession().beginTransaction();
        Query query = SessionFactory.getCurrentSession().createQuery("from Comment N where status like 'activated' and filDiscussion.idFilDiscussion= :id");
        query.setParameter("id", idFilleDescussion);
        return query.list();

    }
    @Override
    public List<Comment> search(String recherche) {
        String[] parts = recherche.split(" ");
        String parames="1=1";
        for (int i = 0; i < parts.length; i++) {
              parames=parames+" and c.contents LIKE :param"+i;
            
        }
         SessionFactory.getCurrentSession().beginTransaction();
        Query query = SessionFactory.getCurrentSession().createQuery("  from Comment c where "+parames);
       for (int i = 0; i < parts.length; i++) {
            
           query.setParameter("param"+i,parts[i]);
            
        }
       
        return query.list();
        
        
      

    }
    public void init() {
        System.out.println("-------CommentDaoImp-----------");
    }

}
