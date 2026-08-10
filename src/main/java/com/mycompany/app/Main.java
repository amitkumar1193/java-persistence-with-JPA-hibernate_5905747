package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;
import com.mycompany.app.entities.ArtClass;
import com.mycompany.app.entities.Review;
import com.mycompany.app.entities.Student;
import com.mycompany.app.entities.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("artclass_persistence_unit");

    // create(emf);
    // update(emf);
    // attachAndDetach(emf);
    // remove(emf);
    //oneToOneRelationship(emf);
   //oneToManyRelationship(emf);
    manyToManyRelationship(emf);
  }

  private static void create(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      Student student = new Student();
      student.setName("John");

      em.persist(student);
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void update(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();

      Student student = em.find(Student.class, 1);
      student.setName("Peter");

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void attachAndDetach(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();

      Student student2 = new Student();
      student2.setName("Mary");
      em.merge(student2);
      em.detach(student2);
      student2.setName("Sue");

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void remove(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      Student student = em.find(Student.class, 1);
      em.remove(student);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void oneToOneRelationship(EntityManagerFactory emf) {
	  EntityManager em = emf.createEntityManager();
	    try {
	      em.getTransaction().begin();
	      ArtClass ac = new ArtClass();
	     ac.setName("Painting");
	     ac.setDayOfWeek("Tuesday");
	     
	     Teacher tch = new Teacher();
	     tch.setName("Ravi");
	     
	     ac.setTeacher(tch);

	      em.persist(ac);
	      em.getTransaction().commit();
	    } finally {
	      em.close();
	    }
  }
  private static void oneToManyRelationship(EntityManagerFactory emf) {
	    EntityManager em = emf.createEntityManager();
	    try {
	        em.getTransaction().begin();

	        Teacher tch1 = new Teacher();
	        tch1.setName("Ravi");

	        Teacher tch12 = new Teacher();
	        tch12.setName("Ramesh");

	        Review r1 = new Review();
	        r1.setComment("good");
	        r1.setRating(2);
	        r1.setTeacher(tch1);
	        tch1.getReviews().add(r1);

	        Review r2 = new Review();
	        r2.setComment("Very good");
	        r2.setRating(5);
	        r2.setTeacher(tch12);
	        tch12.getReviews().add(r2);

	        em.persist(tch1);
	        em.persist(tch12);

	        em.getTransaction().commit();
	    } finally {
	        em.close();
	    }
	}


  private static void manyToManyRelationship(EntityManagerFactory emf) {
	    EntityManager em = emf.createEntityManager();

	    try {
	        em.getTransaction().begin();

	        // Create Students
	        Student s1 = new Student();
	        s1.setName("Amit");

	        Student s2 = new Student();
	        s2.setName("Ravi");

	        // Create ArtClass
	        ArtClass c1 = new ArtClass();
	        c1.setName("Painting");
	        c1.setDayOfWeek("Monday");

	        // Add students to the class
	        c1.getStudents().add(s1);
	        c1.getStudents().add(s2);

	        // Persist the class (students will also be persisted because ManyToMany has no cascade)
	        em.persist(s1);
	        em.persist(s2);
	        em.persist(c1);

	        em.getTransaction().commit();
	    } finally {
	        em.close();
	    }
	}

}
