/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forum.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forum.entity.Category;
import com.forum.entity.FilDiscussion;
import com.forum.entity.User;
import com.forum.services.CategoryMetier;
import com.forum.services.FilDiscussionMetier;

/**
 *
 * @author amira.saad
 */
//@ManagedBean(name = "PublicationBean")
@Component("PublicationBean")
@RequestScoped
public class PublicationBean implements Serializable {

////    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
    User currentUser;
    @Autowired
    private CategoryMetier categoryMetier;
    private List<Category> categories;
    @Autowired
    private FilDiscussionMetier filDiscussionMetier;

    private FilDiscussion filDiscussion;
    private int idCategorySelected;
    
    @PostConstruct
    public void init() {

//        categoryMetier = (CategoryMetier) this.context.getBean("categoryMetier");
//        categories = categoryMetier.findAll();

//        filDiscussionMetier = (FilDiscussionMetier) this.context.getBean("filDiscussionMetier");
        filDiscussion = new FilDiscussion();        
        idCategorySelected=0;
    }

    public PublicationBean() {

    }

    public CategoryMetier getCategoryMetier() {
        return categoryMetier;
    }

    public void setCategoryMetier(CategoryMetier categoryMetier) {
        this.categoryMetier = categoryMetier;
    }

    public List<Category> getCategories() {
         categories = categoryMetier.findAll();
        return categories;
    }

  

    public FilDiscussion getFilDiscussion() {
         
        return filDiscussion;
    }

    public void setFilDiscussion(FilDiscussion filDiscussion) {
        this.filDiscussion = filDiscussion;
    }

    public int getIdCategorySelected() {
        return idCategorySelected;
    }

    public void setIdCategorySelected(int idCategorySelected) {
        this.idCategorySelected = idCategorySelected;
    }

    public String dateFilDiscussion(java.util.Date d2) {
        java.util.Date d1 = new java.util.Date();
        long time = ((d1.getTime() - d2.getTime()) / 60000);
        if (time < 60) {
            return time + " min";
        } else if (time >= 60 && time < 1440) {
            return (time / 60) + " h";
        } else {
            return d2.toString();

        }

    }

    public void onPostPers() {
     
        currentUser = new User();
        currentUser.setIdUser(5);
        
        Category c=categoryMetier.findById(idCategorySelected);
        filDiscussion.setCategory(c);
        filDiscussion.setDateDiscussion(new java.util.Date());
        filDiscussion.setStatus("desactivated");
        filDiscussion.setUser(currentUser);
       filDiscussionMetier.save(filDiscussion);

        System.out.println(filDiscussion);
       addMessage("Enregistrement", "Succée");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
        } catch (IOException ex) {
            Logger.getLogger(PublicationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
       public int sizeOfList(Set t) {
        if (t != null) {
            return t.size();
        } else {
            return 0;
        }
    }

public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
