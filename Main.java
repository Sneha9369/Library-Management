import java.util.*;
import manager.LibraryManager;
import manager.LoginManager;
import model.Book;
import model.User;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryManager library = new LibraryManager();
        LoginManager l = new LoginManager();
        User currentUser = null;

        while (currentUser == null) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("Enter your username:");
                String username = sc.next();
                System.out.println("Enter your password:");
                String password = sc.next();

                currentUser = l.login(username, password, library.getAllUsers());

                if (currentUser == null) {
                    System.out.println("Invalid username or password. Try Again.");
                } else {
                    System.out.println("Login successful! Welcome " + currentUser.getName());

                    while (true) {
                        System.out.println("\nLibrary Menu:");
                        System.out.println("1. Add Book");
                        System.out.println("2. View All Books");
                        System.out.println("3. View All Users");
                        System.out.println("4. Search Book by Title");
                        System.out.println("5. Borrow Book");
                        System.out.println("6. Return Book");
                        System.out.println("7. Logout");

                        int option = sc.nextInt();
                        sc.nextLine(); // consume newline

                        if (option == 1) {
                            System.out.println("Enter Book Title:");
                            String title = sc.nextLine();
                            System.out.println("Enter Author:");
                            String author = sc.nextLine();
                            System.out.println("Enter Publisher:");
                            String publisher = sc.nextLine();
                            System.out.println("Enter Year:");
                            int year = sc.nextInt();
                            System.out.println("Enter Total Copies:");
                            int total = sc.nextInt();
                            int bookId = 1000 + new Random().nextInt(9000);
                            Book book = new Book(bookId, title, author, publisher, year, total, total);
                            library.addBook(book);
                            System.out.println("Book added successfully!");
                        } else if (option == 2) {
                            library.viewAllBooks();
                        } else if (option == 3) {
                            for (User u : library.getAllUsers()) {
                                System.out.println("ID: " + u.getId() + ", Name: " + u.getName() + ", Username: " + u.getUsername());
                            }
                        } else if (option == 4) {
                            System.out.println("Enter title to search:");
                            String title = sc.nextLine();
                            library.searchBookByTitle(title);
                        } else if (option == 5) {
                            System.out.println("Enter Book ID to borrow:");
                            int bookId = sc.nextInt();
                            library.borrowBook(bookId, currentUser.getId());
                        } else if (option == 6) {
                            System.out.println("Enter Book ID to return:");
                            int bookId = sc.nextInt();
                            library.returnBook(bookId, currentUser.getId());
                        } else if (option == 7) {
                            System.out.println("Logged out.");
                            currentUser = null;
                            break;
                        } else {
                            System.out.println("Invalid option!");
                        }
                    }
                }
            } else if (choice == 2) {
                System.out.println("Enter your name:");
                String name = sc.next();
                System.out.println("Enter your username:");
                String username = sc.next();
                System.out.println("Enter your password:");
                String password = sc.next();
                int userId = 1000 + new Random().nextInt(9000);
                User newUser = new User(userId, name, username, password, false);
                library.addUser(newUser);
                System.out.println("User registered successfully! Please login to continue.");
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                return;
            } else {
                System.out.println("Invalid choice. Try Again.");
            }
        }
    }
}