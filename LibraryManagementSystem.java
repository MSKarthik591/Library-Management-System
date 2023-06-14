import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean available;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> searchResults = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword) || book.getISBN().contains(keyword)) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", ISBN: " + book.getISBN());
            }
        }
        System.out.println();
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("You have borrowed the book: " + book.getTitle());
        } else {
            System.out.println("Sorry, the book is not available for borrowing.");
        }
    }

    public void returnBook(Book book) {
        book.setAvailable(true);
        System.out.println("You have returned the book: " + book.getTitle());
    }
}

public class LibraryManagementSystem {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayMenu();
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        while (choice != 0) {
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    displayAvailableBooks();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
        }

        System.out.println("Thank you for using the Library Management System!");
    }

    private static void displayMenu() {
        System.out.println("===== Library Management System =====");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Search Books");
        System.out.println("4. Display Available Books");
        System.out.println("5. Borrow Book");
        System.out.println("6. Return Book");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addBook() {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();
        System.out.print("Enter the ISBN of the book: ");
        String ISBN = scanner.nextLine();

        Book book = new Book(title, author, ISBN);
        library.addBook(book);
        System.out.println("The book has been added to the library.");
        System.out.println();
    }

    private static void removeBook() {
        System.out.print("Enter the ISBN of the book to remove: ");
        String ISBN = scanner.nextLine();

        List<Book> searchResults = library.searchBooks(ISBN);
        if (!searchResults.isEmpty()) {
            Book bookToRemove = searchResults.get(0);
            library.removeBook(bookToRemove);
            System.out.println("The book has been removed from the library.");
        } else {
            System.out.println("No book found with the provided ISBN.");
        }
        System.out.println();
    }

    private static void searchBooks() {
        System.out.print("Enter a keyword to search for books: ");
        String keyword = scanner.nextLine();

        List<Book> searchResults = library.searchBooks(keyword);
        if (!searchResults.isEmpty()) {
            System.out.println("Search Results:");
            for (Book book : searchResults) {
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", ISBN: " + book.getISBN());
            }
        } else {
            System.out.println("No books found with the provided keyword.");
        }
        System.out.println();
    }

    private static void displayAvailableBooks() {
        library.displayAvailableBooks();
    }

    private static void borrowBook() {
        System.out.print("Enter the ISBN of the book to borrow: ");
        String ISBN = scanner.nextLine();

        List<Book> searchResults = library.searchBooks(ISBN);
        if (!searchResults.isEmpty()) {
            Book bookToBorrow = searchResults.get(0);
            library.borrowBook(bookToBorrow);
        } else {
            System.out.println("No book found with the provided ISBN.");
        }
        System.out.println();
    }

    private static void returnBook() {
        System.out.print("Enter the ISBN of the book to return: ");
        String ISBN = scanner.nextLine();

        List<Book> searchResults = library.searchBooks(ISBN);
        if (!searchResults.isEmpty()) {
            Book bookToReturn = searchResults.get(0);
            library.returnBook(bookToReturn);
        } else {
            System.out.println("No book found with the provided ISBN.");
        }
        System.out.println();
    }
}
