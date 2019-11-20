/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forum.beans;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forum.entity.Category;
import com.forum.entity.Role;
import com.forum.entity.User;
import com.forum.services.CategoryMetier;
import com.forum.services.UserMetier;

/**
 *
 * @author amira.saad
 */
@Component("NVCompteBean")
@RequestScoped
public class NVCompteBean {

    @Autowired
    private CategoryMetier categoryMetier;
    private List<Category> categories;

    private User nvUser;
    private UploadedFile file;

    @Autowired
    private UserMetier userMetier;

    @PostConstruct
    public void init() {

        categories = categoryMetier.findAll();

    }

    public NVCompteBean() {nvUser=new User();
    }

    public CategoryMetier getCategoryMetier() {
        return categoryMetier;
    }

    public void setCategoryMetier(CategoryMetier categoryMetier) {
        this.categoryMetier = categoryMetier;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public User getNvUser() {
        return nvUser;
    }

    public void setNvUser(User nvuser) {
        this.nvUser = nvuser;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UserMetier getUserMetier() {
        return userMetier;
    }

    public void setUserMetier(UserMetier userMetier) {
        this.userMetier = userMetier;
    }

    public int sizeOfList(Set t) {
        if (t != null) {
            return t.size();
        } else {
            return 0;
        }
    }

    public void OnAddPres() {
  
    
//        if (file != null) {
//            Set<PhotoUserProfile> phts = new HashSet<PhotoUserProfile>();
//            PhotoUserProfile f = new PhotoUserProfile();
//            f.setPhoto(file.getContents());
//            phts.add(f);
            nvUser.setBirthdate(new Date());
//            nvUser.setPhotoUserProfiles(phts);
            nvUser.setStatus("activated");
            Role r=new Role();
            r.setIdRole(2);
            nvUser.setRole(r);
            userMetier.save(nvUser);
            FacesMessage message = new FacesMessage("Succesful", " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println(nvUser);
//        }
//        FacesMessage message = new FacesMessage("Error", " file note  uploaded.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
