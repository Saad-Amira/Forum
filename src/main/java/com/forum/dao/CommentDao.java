/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forum.dao;

import java.util.List;

import com.forum.entity.Comment;

/**
 *
 * @author amira.saad
 */
public interface CommentDao {

    public void save(Comment cmt);

    public void delete(Comment cmt);

    public Comment findById(Integer id);

    public List<Comment> findAll(int idFilleDescussion);

    public List<Comment> search(String recherche);
}
