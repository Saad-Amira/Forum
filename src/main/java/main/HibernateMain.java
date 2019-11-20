package main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.forum.entity.Category;

public class HibernateMain {
	// Create the SessionFactory when you start the application.
	private static final SessionFactory SESSION_FACTORY;

	/**
	 * Initialize the SessionFactory instance.
	 */
	static {
		// Create a Configuration object.
		Configuration config = new Configuration();
		config.configure();

		SESSION_FACTORY = config.buildSessionFactory();
	}

	public static void main(String[] args) {
		// Create two Students
		create(1, "arij", 19); // Alice will get an id 1
		/*
		 * create(2, "Bob", 20); // Bob will get an id 2 create(3, "Charlie", 25); //
		 * Charlie will get an id 3
		 * 
		 * // Update the age of Bob using the id upate(2, "Bob", 25);
		 * 
		 * // Delete the Alice from database delete(1);
		 * 
		 * // Print all the Students List<Student> students = readAll(); if (students !=
		 * null) { for (Student stu : students) { System.out.println(stu); } }
		 */
		// NEVER FORGET TO CLOSE THE SESSION_FACTORY
		SESSION_FACTORY.close();
	}

	/**
	 * Create a new Student.
	 *
	 * @param name
	 * @param age
	 */
	public static void create(int id, String name, int age) {
		// Create a session
		Session session = SESSION_FACTORY.openSession();
		Transaction transaction = null;
		try {
			// Begin a transaction
			transaction = session.beginTransaction();
			Category stu = new Category("toto1", "toto2");
			stu.setIdCategory(13);
			stu.setStatus("activated");

			// Save the student
			session.save(stu);
			// Commit the transaction
			transaction.commit();
		} catch (HibernateException ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
		} finally {
			// Close the session
			session.close();
		}
	}

}