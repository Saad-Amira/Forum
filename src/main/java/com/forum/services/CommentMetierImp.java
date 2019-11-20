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

import com.forum.dao.CommentDao;
import com.forum.entity.Comment;


@Service("commentMetier")
@Transactional
public class CommentMetierImp implements CommentMetier {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void save(Comment cmt) {
         
        commentDao.save(cmt);
    }

    @Override
    public void delete(Comment cmt) {
        commentDao.delete(cmt);
    }

    @Override
    public Comment findById(Integer id) {
        return commentDao.findById(id);
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> findAll(int idFilleDescussion) {
        return commentDao.findAll(idFilleDescussion);
    }

    public void init() {
        System.out.println("-------CommentMetierImp-----------");
    }

}
