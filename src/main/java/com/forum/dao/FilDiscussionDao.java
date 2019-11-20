/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.forum.dao;

import java.util.List;

import com.forum.entity.FilDiscussion;

/**
 *
 * @author amira.saad
 */
public interface FilDiscussionDao {
    public void save(FilDiscussion filDisc);
      public void delete(FilDiscussion filDisc);
        public FilDiscussion findById(Integer id);
            public List<FilDiscussion> findAll();   
             public List<FilDiscussion> search(String recherche);
             public List<FilDiscussion> findByCategory(int id);
}
