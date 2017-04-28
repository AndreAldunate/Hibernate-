package com.javahelps.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
	// Create an EntityManagerFactory when you start the application.
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("JavaHelps");

	public static void main(String[] args) {
		System.out.println();
		// Create two Students
		insertarEstudiante(1, "Juan",19 ); 
		insertarEstudiante(2, "Andre", 20); 
		insertarEstudiante(3, "Carlos", 18); 

		

		List<Student> students = readAll();
		if (students != null) {
			for (Student stu : students) {
				System.out.println(stu);
			}
		}

		// NEVER FORGET TO CLOSE THE ENTITY_MANAGER_FACTORY
		ENTITY_MANAGER_FACTORY.close();
	}

	
	public static void insertarEstudiante(int id, String name, int age) {
		// Create an EntityManager
        System.out.println("Insertando estudiante : " + name+ " id : "+id);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			// Begin the transaction
			transaction.begin();

			// Create a new Student object
			Student stu = new Student();
			stu.setId(id);
			stu.setName(name);
			stu.setAge(age);

			// Save the student object
			manager.persist(stu);

			// Commit the transaction
			transaction.commit();
		} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
		} finally {
			// Close the EntityManager
			manager.close();
		}
	}

	/**
	 * Read all the Students.
	 * 
	 * @return a List of Students
	 */
	public static List<Student> readAll() {

		List<Student> students = null;

		// Create an EntityManager
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			// Begin the transaction
			transaction.begin();

			// Get a List of Students
			students = manager.createQuery("SELECT s FROM Student s", Student.class).getResultList();

			// Commit the transaction
			transaction.commit();
		} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
		} finally {
			// Close the EntityManager
			manager.close();
		}
		return students;
	}

	/**
	 * Delete the existing Student.
	 * 
	 * @param id
	 */
	public static void borrarEstudiante(int id) {
		// Create an EntityManager
        System.out.println("Borrando estudiante con id : "+id);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			// Begin the transaction
			transaction.begin();

			// Get the Student object
			Student stu = manager.find(Student.class, id);

			// Delete the student
			manager.remove(stu);

			// Commit the transaction
			transaction.commit();
		} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
		} finally {
			// Close the EntityManager
			manager.close();
		}
	}

	/**
	 * Update the existing Student.
	 * 
	 * @param id
	 * @param name
	 * @param age
	 */
	public static void actualizar(int id, String name, int age) {
		// Create an EntityManager
        System.out.println("Actualizando Estudiante : "+name+ "con id : "+id);
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			// Begin the transaction
			transaction.begin();

			// Get the Student object
			Student stu = manager.find(Student.class, id);

			// Change the values
			stu.setName(name);
			stu.setAge(age);

			// Update the student
			manager.persist(stu);

			// Commit the transaction
			transaction.commit();
		} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
		} finally {
			// Close the EntityManager
			manager.close();
		}
	}
}
