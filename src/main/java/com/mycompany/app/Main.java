package com.mycompany.app;

import java.util.List;
import java.util.Set;

import com.mycompany.app.dto.BooksAndAuthors;
import com.mycompany.app.entities.Address;
import com.mycompany.app.entities.Author;
import com.mycompany.app.entities.Book;
import com.mycompany.app.entities.BookType;
import com.mycompany.app.entities.CardPayment;
import com.mycompany.app.entities.CashPayment;
import com.mycompany.app.entities.Category;
import com.mycompany.app.entities.Fiction;
import com.mycompany.app.entities.Field;
import com.mycompany.app.entities.Group;
import com.mycompany.app.entities.Item;
import com.mycompany.app.entities.NonFiction;
import com.mycompany.app.entities.Review;
import com.mycompany.app.entities.User;
import com.mycompany.app.entities.keys.ItemKey;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Main {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("library_persistence_unit");

    // createInstance(emf);
    // findAndUpdateInstance(emf);
    // detachAndReattachInstance(emf);
    // removeInstance(emf);
    // useGetReference(emf);
    // useRefresh(emf);
    // createEntityWithComposedPK(emf);
    // oneToOneRelationship(emf);
    // oneToManyRelationship(emf);
    // manyToManyRelationship(emf);
    // mappedSuperclassStrategy(emf);
    // singleTableStrategy(emf);
    // joinedTableStrategy(emf);
    // tablePerClassStrategy(emf);
    // compositionWithAssociation(emf);
    // compositionWithEmbadable(emf);
    // writeJPQLQuerry(emf);
    // joinsWithJPQL(emf);
    namedQuerries(emf);
  }

  private static void createInstance(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager(); // Represent the persistence context

    try {
      em.getTransaction().begin();

      Book book = new Book();
      book.setName("my book3");
      book.setIsbn("333-456");
      em.persist(book);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void findAndUpdateInstance(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      Book book1 = em.find(Book.class, 1);
      book1.setName("my new book");
      System.out.println(book1);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void detachAndReattachInstance(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      Book book1 = new Book();
      book1.setId(1);
      book1.setName("my newest book");
      book1.setIsbn("123-456");
      em.merge(book1);
      em.detach(book1);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void removeInstance(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      Book book1 = em.find(Book.class, 1);
      em.remove(book1);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void useGetReference(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Book book1 = em.getReference(Book.class, 1);
      System.out.println(book1);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void useRefresh(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      Book book1 = em.find(Book.class, 1);
      System.out.println(book1);
      book1.setName("some book");
      System.out.println("Before " + book1);

      em.refresh(book1);
      System.out.println("After " + book1);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void createEntityWithComposedPK(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      // BookType bookType = new BookType();
      // bookType.setCode("C001");
      // bookType.setSubCode("SC001");
      // bookType.setName("Fiction-Horror");

      // em.persist(bookType);

      ItemKey id = new ItemKey();
      id.setCode("ABC");
      id.setNumber(100);

      Item i = new Item();
      i.setId(id);
      i.setName("ABC-100");

      em.persist(i);

      em.getTransaction().commit();
    } finally {
      em.close();
    }

  }

  private static void oneToOneRelationship(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Book book = new Book();
      book.setName("another book");
      book.setIsbn("1010-111");

      Author author = new Author();
      author.setName("John");

      book.setAuthor(author);

      em.persist(book);
      em.persist(author);

      em.getTransaction().commit();

    } finally {
      em.close();
    }
  }

  private static void oneToManyRelationship(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Book book = new Book();
      book.setName("book 123");
      book.setIsbn("123-123");

      Author author = em.find(Author.class, 1);
      book.setAuthor(author);

      Review review1 = new Review();
      review1.setComment("This book in good");
      review1.setBook(book);
      Review review2 = new Review();
      review2.setComment("This book is lovely");
      review2.setBook(book);

      book.setReviews(List.of(review1, review2));

      em.persist(book);

      em.getTransaction().commit();

    } finally {
      em.close();
    }
  }

  private static void manyToManyRelationship(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      User user1 = new User();
      user1.setName("User1");
      User user2 = new User();
      user2.setName("User2");

      Group group1 = new Group();
      group1.setName("Group1");
      Group group2 = new Group();
      group2.setName("Group2");

      group1.setUsers(List.of(user1, user2));
      group2.setUsers(List.of(user1));

      em.persist(group1);
      em.persist(group2);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  // private static void mappedSuperclassStrategy(EntityManagerFactory emf) {
  // EntityManager em = emf.createEntityManager();

  // try {
  // em.getTransaction().begin();

  // Student s = new Student();
  // s.setName("John");
  // s.setStudentCode("S001");

  // Teacher t = new Teacher();
  // t.setName("David");
  // t.setTeacherCode("T001");

  // em.persist(s);
  // em.persist(t);

  // em.getTransaction().commit();
  // } finally {
  // em.close();
  // }
  // }

  // private static void singleTableStrategy(EntityManagerFactory emf) {
  // EntityManager em = emf.createEntityManager();

  // try {
  // em.getTransaction().begin();

  // Student2 s = new Student2();
  // s.setName("John");
  // s.setStudentCode("S001");

  // Teacher2 t = new Teacher2();
  // t.setName("David");
  // t.setTeacherCode("T001");

  // em.persist(s);
  // em.persist(t);

  // em.getTransaction().commit();
  // } finally {
  // em.close();
  // }
  // }

  private static void joinedTableStrategy(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      Fiction f = new Fiction();
      f.setCode("F001");
      f.setSetting("Forest");

      NonFiction nf = new NonFiction();
      nf.setCode("NF001");
      nf.setTopic("Science");

      em.persist(f);
      em.persist(nf);

      em.getTransaction().commit();
    } finally {
      em.close();
    }

  }

  private static void tablePerClassStrategy(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      CardPayment card = new CardPayment();
      card.setId(100);
      card.setAmount(1000);
      card.setCardNumber("1234 5678 5677 3456");

      CashPayment cash = new CashPayment();
      cash.setId(101);
      cash.setAmount(2000);
      cash.setCode("CA001");

      em.persist(cash);
      em.persist(card);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void compositionWithAssociation(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Field f1 = new Field();
      f1.setName("Music");
      Field f2 = new Field();
      f2.setName("Art");

      Category c1 = new Category();
      c1.setName("History");
      Category c2 = new Category();
      c2.setName("New Advancements");

      f1.setCategories(Set.of(c1, c2));
      f2.setCategories(Set.of(c1, c2));

      c1.setFields(Set.of(f1, f2));
      c2.setFields(Set.of(f1, f2));

      em.persist(f1);
      em.persist(f2);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void compositionWithEmbadable(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      Author author = new Author();
      author.setName("William");

      Address address = new Address();
      address.setStreet("1st street");
      address.setCity("London");
      address.setPostalCode("12345");

      author.setAddress(address);

      em.persist(author);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void writeJPQLQuerry(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      TypedQuery<BookType> q = em.createQuery(
          "SELECT bt FROM BookType bt WHERE bt.subCode = :subCode AND bt.name LIKE :name", BookType.class);

      q.setParameter("subCode", "SC001");
      q.setParameter("name", "Fiction%");

      List<BookType> bookTypes = q.getResultList();

      for (BookType bt : bookTypes) {
        System.out.println(bt);
      }

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void joinsWithJPQL(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();

      String s = """
            SELECT NEW com.mycompany.app.dto.BooksAndAuthors(book, author, address) FROM Book book LEFT JOIN book.author author
          """;

      TypedQuery<BooksAndAuthors> query = em.createQuery(s, BooksAndAuthors.class);

      List<BooksAndAuthors> result = query.getResultList();

      for (BooksAndAuthors r : result) {
        System.out.println(r.book() + " " + r.author() + " " + r.address());
      }

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  private static void namedQuerries(EntityManagerFactory emf) {
    EntityManager em = emf.createEntityManager();

    try {

       em.getTransaction().begin();

       TypedQuery<BookType> q = em.createNamedQuery("bookType.findAll", BookType.class);
       List<BookType> bookTypes = q.getResultList();

       for (BookType bt : bookTypes) {
           System.out.println(bt);
       }

       TypedQuery<BookType> q2 = em.createNamedQuery("bookType.findBySubcodeAndName", BookType.class);

       q2.setParameter("subCode", "SC002");
       q2.setParameter("name", "Fiction%");

      List<BookType> result2 = q2.getResultList();

       for (BookType bt : result2) {
       System.out.println(bt);
       }

      em.getTransaction().commit();

    } finally {
      em.close();
    }
  }
}
