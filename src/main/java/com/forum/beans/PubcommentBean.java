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
import com.forum.entity.Comment;
import com.forum.entity.FilDiscussion;
import com.forum.entity.User;
import com.forum.services.CategoryMetier;
import com.forum.services.CommentMetier;
import com.forum.services.FilDiscussionMetier;

/**
 *
 * @author amira.saad
 */
//@ManagedBean(name = "PubcommentBean")
@Component("PubcommentBean")
@RequestScoped
public class PubcommentBean implements Serializable {

    ////    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
    User currentUser;//utilisateur connecté
    @Autowired
    private CategoryMetier categoryMetier;
    private List<Category> categories;

    String idFD;
    @Autowired
    private FilDiscussionMetier filDiscussionMetier;
    private FilDiscussion filDiscussion;

    @Autowired
    private CommentMetier CommentMetier;
    private List<Comment> comments;

    private Comment commentaire;

    @PostConstruct
    public void init() {
        commentaire = new Comment();
    }

    public PubcommentBean() {
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

    public FilDiscussion getFilDiscussion() {
        if (idFD == null || "".equals(idFD)) {
            //get id fill discussion {publication} from url
            Map<String, String> urlParams;//parametre passe en URL
            urlParams = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap();
            String parameterOne = urlParams.get("id");

            if (parameterOne != null && !parameterOne.equals("")) {
                idFD = parameterOne;
                filDiscussion = filDiscussionMetier.findById(Integer.parseInt(parameterOne));
            }
        } else {
            filDiscussion = filDiscussionMetier.findById(Integer.parseInt(idFD));
        }
        return filDiscussion;
    }

    public void setFilDiscussion(FilDiscussion filDiscussion) {
        this.filDiscussion = filDiscussion;
    }

    public CommentMetier getCommentMetier() {
        return CommentMetier;
    }

    public void setCommentMetier(CommentMetier CommentMetier) {
        this.CommentMetier = CommentMetier;
    }

    public List<Comment> getComments() {
        if (idFD == null || idFD.equals("")) {
            //get id fill descussion {publication} from url
            Map<String, String> urlParams;//parametre passe en URL
            urlParams = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap();
            String parameterOne = urlParams.get("id");

            if (parameterOne != null && !parameterOne.equals("")) {
                idFD = parameterOne;
                comments = CommentMetier.findAll(Integer.parseInt(parameterOne));
            }
        } else {
            comments = CommentMetier.findAll(Integer.parseInt(idFD));
        }

        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**/
    public String getIdFD() {
        return idFD;
    }

    public void setIdFD(String idFD) {
        this.idFD = idFD;
    }

    public Comment getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Comment commentaire) {
        this.commentaire = commentaire;
    }

    public void onPostCommentPers() {

        currentUser = new User();
        currentUser.setIdUser(5);
        commentaire.setDateComment(new java.util.Date());
        FilDiscussion f = new FilDiscussion();

        f.setIdFilDiscussion(Integer.parseInt(idFD));
        commentaire.setFilDiscussion(f);
        commentaire.setStatus("activated");
        commentaire.setUser(currentUser);
        CommentMetier.save(commentaire);
        commentaire = new Comment();

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
}
