package com.forum.beans;

import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

@Component("KeyboardBean")
@SessionScoped
public class KeyboardBean {
	private String value;

	public String getValue() {
		System.out.println("obobob" + value);
		return value;
	}

	public void setValue(String values) {
		System.out.println(" bibi" + values);
		this.value = values;
	}

}
