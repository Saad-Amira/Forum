/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forum.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

/**
 *
 * @author amira.saad
 */
//@ManagedBean(name = "CategoryBean")
@Component("AuthentificationBean")
@RequestScoped
public class AuthentificationBean implements Serializable {

	public String doLogin() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
				.getRequestDispatcher("/j_spring_security_check");
		try {
			dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		} catch (IOException ex) {
			Logger.getLogger(AuthentificationBean.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ServletException ex) {
			Logger.getLogger(AuthentificationBean.class.getName()).log(Level.SEVERE, null, ex);
		}

		FacesContext.getCurrentInstance().responseComplete();
		System.out.println("Helo - oooooooooooo");

		return null;

	}
}
