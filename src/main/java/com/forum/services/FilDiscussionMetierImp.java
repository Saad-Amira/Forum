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

import com.forum.dao.FilDiscussionDao;
import com.forum.entity.FilDiscussion;

@Service("filDiscussionMetier")
@Transactional
public class FilDiscussionMetierImp implements FilDiscussionMetier {

    @Autowired
    private FilDiscussionDao filDiscussionDao;

    @Override
    public void save(FilDiscussion filDisc) {
        filDiscussionDao.save(filDisc);
    }

    @Override
    public void delete(FilDiscussion filDisc) {
        filDiscussionDao.delete(filDisc);
    }

    @Override
    public FilDiscussion findById(Integer id) {
        return filDiscussionDao.findById(id);
    }

    @Override
    public List<FilDiscussion> findByCategory(int idCategory) {
        return filDiscussionDao.findByCategory(idCategory);
    }

    @Override
    public List<FilDiscussion> findAll() {
        return filDiscussionDao.findAll();
    }

    @Override
    public List<FilDiscussion> search(String recherche) {
        return filDiscussionDao.search(recherche);
    }

    public void setFilDiscussionDao(FilDiscussionDao filDiscussionDao) {
        this.filDiscussionDao = filDiscussionDao;
    }

    public void init() {
        System.out.println("-------FilDiscussionMetierImp-----------");
    }

}
