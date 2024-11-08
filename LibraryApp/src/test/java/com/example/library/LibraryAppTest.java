//Imports to start Tests
package com.example.library;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.github.stefanbirkner.systemlambda.SystemLambda;

class LibraryAppTest {

    @Test
    void testListBooks() throws Exception {
        
        // Create a new library instance
        Library library = new Library();
        
        // Create instances of Fiction and NonFiction books
        Fiction fictionBook = new Fiction("The real one", "F. Bomb", "12345");
        NonFiction nonFictionBook = new NonFiction("Not Again, Wake up.", "Yikes Mink", "67890");

        // Add books to the library
        library.addBook(fictionBook);
        library.addBook(nonFictionBook);

        // Capture system output when listing books
        String output = SystemLambda.tapSystemOut(() -> {
            library.listBooks();
        });

        // Assert that the output contains the expected book descriptions
        assertTrue(output.contains("Fiction Book: The real one by F. Bomb"));
        assertTrue(output.contains("Non-Fiction Book: Not Again, Wake up. by Yikes Mink"));
        
    }
}
