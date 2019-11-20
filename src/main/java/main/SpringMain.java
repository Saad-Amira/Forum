package main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.forum.entity.Category;
import com.forum.services.CategoryMetier;



public class SpringMain {
	public static void main(String arg[]) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");

		CategoryMetier sudentDao = (CategoryMetier) context.getBean("CategorieService");

		Category s = sudentDao.findById(2);
		System.out.println(s);
		context.close();

	}
}
