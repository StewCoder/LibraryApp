
/*
 * Name        : LibraryApp.java
 * Author      : StewCode
 * Version     : 1.0
 * Description : Manages a library system with books, members, and staff. It allows for registering books, adding 
 * members, it also lists the contents of the library.
 * 
 * <p>Classes in this system:</p>
 * <ul>
 *     <li>Library: Represents the library that contains books, members, and staff.</li>
 *     <li>Book: An abstract class representing general book properties (title, author, ISBN).</li>
 *     <li>Fiction: A subclass of Book representing fiction books.</li>
 *     <li>NonFiction: A subclass of Book representing non-fiction books.</li>
 *     <li>Person: An abstract class for shared properties of members and staff.</li>
 *     <li>Member: A subclass of Person representing library members.</li>
 *     <li>Staff: A subclass of Person representing library staff with additional functionalities like 
 *         registering books and members.</li>
 * </ul>
 */
package com.example.library;
import java.util.ArrayList;
import java.util.List;

public class LibraryApp {
    //main
    public static void main(String[] args) {
        // Main, testing is done in UItest
    }
}

/*
 * Abstract class representing general book properties.
 * 
 * <p>Books have a title, author, ISBN, and checked-out status. Derived classes must implement the 
 * describe method to describe specific book types.</p>
 */
abstract class Book {
    String title;  /*< Title of the book */
    String author; /*< Author of the book */
    String ISBN;   /*< ISBN number of the book */
    boolean isCheckedOut; /**< Book's checked-out status */

    /*
     * Constructs a new book with the given title, author, and ISBN.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param ISBN The ISBN of the book.
     */
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isCheckedOut = false;
    }

    //Marks the book as checked out.
    public void checkOut() {
        isCheckedOut = true;
    }

    //Marks the book as returned.
    public void returnBook() {
        isCheckedOut = false;
    }

    /*
     * Abstract method to describe the book, implemented by subclasses to specify type of book.
     *
     * @return A string describing the book.
     */
    public abstract String describe();
}

/*
 * Represents a fiction book.
 * 
 * <p>This class inherits from Book and implements the describe method for fiction books.</p>
 */
class Fiction extends Book {
    /*
     * Constructs a fiction book with the given title, author, and ISBN.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param ISBN The ISBN of the book.
     */
    public Fiction(String title, String author, String ISBN) {
        super(title, author, ISBN);
    }

    /*
     * Describes the fiction book in a string format.
     *
     * @return A string description of the fiction book.
     */
    @Override
    public String describe() {
        return "Fiction Book: " + title + " by " + author;
    }
}

/*
 * Represents a non-fiction book.
 * 
 * <p>This class inherits from Book and implements the describe method for non-fiction books.</p>
 */
class NonFiction extends Book {
    /**
     * Constructs a non-fiction book with the given title, author, and ISBN.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param ISBN The ISBN of the book.
     */
    public NonFiction(String title, String author, String ISBN) {
        super(title, author, ISBN);
    }

    /*
     * Describes the non-fiction book in a string format.
     *
     * @return A string description of the non-fiction book.
     */
    @Override
    public String describe() {
        return "Non-Fiction Book: " + title + " by " + author;
    }
}

/*
 * Abstract class representing general properties of a person.
 * 
 * <p>This class serves as a base for both Member and Staff classes.</p>
 */
abstract class Person {
    String name;  /*< Name of the person */
    String id;    /*< ID of the person (either Member ID or Staff ID) */

    /*
     * Constructs a new person with the given name and ID.
     *
     * @param name The name of the person.
     * @param id The ID of the person.
     */
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Abstract method to describe the person, implemented by subclasses.
     *
     * @return A string describing the person.
     */
    public abstract String describe();
}

/**
 * Represents a library member.
 * 
 * <p>Inherits from Person and implements the describe method for members.</p>
 */
class Member extends Person {
    /**
     * Constructs a new member with the given name and ID.
     *
     * @param name The name of the member.
     * @param id The ID of the member.
     */
    public Member(String name, String id) {
        super(name, id);
    }

    /**
     * Describes the library member.
     *
     * @return A string describing the member.
     */
    @Override
    public String describe() {
        return "Library Member: " + name + ", ID: " + id;
    }
}

/**
 * Represents a library staff member.
 * 
 * <p>Inherits from Person and implements the describe method for staff members. 
 * Staff members can register books and members in the library.</p>
 */
class Staff extends Person {
    /**
     * Constructs a new staff member with the given name and ID.
     *
     * @param name The name of the staff member.
     * @param id The ID of the staff member.
     */
    public Staff(String name, String id) {
        super(name, id);
    }

    /**
     * Describes the library staff member.
     *
     * @return A string describing the staff member.
     */
    @Override
    public String describe() {
        return "Library Staff: " + name + ", ID: " + id;
    }

    /**
     * Registers a new member in the library.
     *
     * @param library The library where the member is to be registered.
     * @param member The member to register.
     */
    public void registerMember(Library library, Member member) {
        library.addMember(member);
    }

    /**
     * Registers a new book in the library.
     *
     * @param library The library where the book is to be registered.
     * @param book The book to register.
     */
    public void registerBook(Library library, Book book) {
        library.addBook(book);
    }
}

/**
 * Represents the library, which manages books, members, and staff.
 * 
 * <p>The library allows for adding books, members, and staff, and can list all registered books, members, and staff.</p>
 */
class Library {
    public List<Book> books;   /**< List of books in the library */
    public List<Member> members; /**< List of members registered in the library */
    public List<Staff> staff;    /**< List of staff in the library */
    
    //Constructs a new library.
    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        staff = new ArrayList<>();
    }

    /**
     * Adds a new book to the library.
     *
     * @param book The book to add.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Registers a new member in the library.
     *
     * @param member The member to register.
     */
    public void addMember(Member member) {
        members.add(member);
    }

    /**
     * Adds a staff member to the library.
     *
     * @param staffMember The staff member to add.
     */
    public void addStaff(Staff staffMember) {
        staff.add(staffMember);
    }

    //Lists all books currently in the library.
    public void listBooks() {
        System.out.println("Books in Library:");
        for (Book book : books) {
            System.out.println(book.describe());
        }
    }

    //Lists all members registered in the library.
    public void listMembers() {
        System.out.println("Library Members:");
        for (Member member : members) {
            System.out.println(member.describe());
        }
    }

    // Lists all staff members working in the library
    public void listStaff() {
        System.out.println("Library Staff:");
        for (Staff staffMember : staff) {
            System.out.println(staffMember.describe());
        }
    }
}


