/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forum.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forum.entity.Category;
import com.forum.entity.FilDiscussion;
import com.forum.services.CategoryMetier;
import com.forum.services.FilDiscussionMetier;

/**
 *
 * @author amira.saad
 */
//@ManagedBean(name = "CategoryBean")
@Component("CategoryBean")
@RequestScoped
public class CategoryBean implements Serializable {


    @Autowired
    private CategoryMetier categoryMetier;
    private List<Category> categories;
    @Autowired
    private FilDiscussionMetier filDiscussionMetier;
    private List<FilDiscussion> filDiscussions;
    private String rechercheValue;

    private String idUrlCategorie;//récupérer l'id url à partire de la catégorie pour trie les publication par cat

    @PostConstruct
    public void init() {


    }

    public CategoryBean() {

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

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public FilDiscussionMetier getFilDiscussionMetier() {
        return filDiscussionMetier;
    }

    public void setFilDiscussionMetier(FilDiscussionMetier filDiscussionMetier) {
        this.filDiscussionMetier = filDiscussionMetier;
    }

    public List<FilDiscussion> getFilDiscussions() {

 //get id fill discussion {publication} from url
        Map<String, String> urlParams;//parametre passe en URL
        urlParams = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        idUrlCategorie = urlParams.get("idCat");

        if (idUrlCategorie != null && !"".equals(idUrlCategorie)) {

            filDiscussions = filDiscussionMetier.findByCategory(Integer.parseInt(idUrlCategorie));
            //idUrlCategorie = null;
        } else 
        if (rechercheValue == null && !"".equals(rechercheValue)) {
            filDiscussions = filDiscussionMetier.findAll();
        }
        rechercheValue = null;
        return filDiscussions;
    }

    public void setFilDiscussions(List<FilDiscussion> filDiscussions) {
        this.filDiscussions = filDiscussions;
    }

    public String getRechercheValue() {
        return rechercheValue;
    }

    public void setRechercheValue(String rechercheValue) {
        this.rechercheValue = rechercheValue;
    }

    public String getIdUrlCategorie() {
        return idUrlCategorie;
    }

    public int sizeOfList(Set t) {
        if (t != null) {
            return t.size();
        } else {
            return 0;
        }
    }

    public String dateToString(java.util.Date d2) {
        java.util.Date d1 = new java.util.Date();
        long time = ((d1.getTime() - d2.getTime()) / 60000);
        if (time == 0) {
            return "À l’instant";
        } else if (time < 60) {
            return time + " min";
        } else if (time >= 60 && time < 1440) {
            return (time / 60) + " h";
        } else {
            return d2.toString();

        }

    }

    public void rechercher() {

        if (rechercheValue != null) {
            filDiscussions = filDiscussionMetier.search(rechercheValue);
        }

    }
}
