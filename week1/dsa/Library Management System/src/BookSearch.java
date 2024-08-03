/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.Comparator;

class Book {
    String bookId;
    String title;
    String author;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author;
    }
}

public class BookSearch {

    // Linear Search
    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Binary Search
    public static Book binarySearch(Book[] books, String title) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(title);

            if (cmp == 0) {
                return books[mid];
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Create an array of books
        Book[] books = {
            new Book("B001", "To Kill a Mockingbird", "Harper Lee"),
            new Book("B002", "1984", "George Orwell"),
            new Book("B003", "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book("B004", "The Catcher in the Rye", "J.D. Salinger"),
            new Book("B005", "Moby-Dick", "Herman Melville")
        };

        // Sort books by title for binary search
        Arrays.sort(books, Comparator.comparing(book -> book.title));

        // Perform linear search
        String titleToSearchLinear = "1984";
        Book foundBookLinear = linearSearch(books, titleToSearchLinear);
        if (foundBookLinear != null) {
            System.out.println("Linear Search Result: " + foundBookLinear);
        } else {
            System.out.println("Book with title '" + titleToSearchLinear + "' not found.");
        }

        // Perform binary search
        String titleToSearchBinary = "The Catcher in the Rye";
        Book foundBookBinary = binarySearch(books, titleToSearchBinary);
        if (foundBookBinary != null) {
            System.out.println("Binary Search Result: " + foundBookBinary);
        } else {
            System.out.println("Book with title '" + titleToSearchBinary + "' not found.");
        }
    }
}

