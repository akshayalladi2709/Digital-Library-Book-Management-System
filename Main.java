package library;

import java.util.*;

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        boolean running = true;

        while (running) {
            System.out.println("\nBook Manager System: \n1. Add Book \n2. View Books \n3. Search Book \n4. Update Book \n5. Delete Book \n6. Exit");
            System.out.print("Select an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Enter Writer: ");
                        String writer = scanner.nextLine().trim();
                        System.out.print("Enter Category: ");
                        String category = scanner.nextLine().trim();
                        System.out.print("Enter Status (Available/Checked Out): ");
                        String status = scanner.nextLine().trim();
                        library.addBook(new Book(id, name, writer, category, status));
                        break;
                    case 2:
                        library.displayAllBooks();
                        break;
                    case 3:
                        System.out.print("Enter ID or Name to search: ");
                        String keyword = scanner.nextLine().trim();
                        library.findBook(keyword);
                        break;
                    case 4:
                        System.out.print("Enter ID to update: ");
                        String updateId = scanner.nextLine().trim();
                        System.out.print("Enter new Name (leave blank to keep unchanged): ");
                        String updateName = scanner.nextLine().trim();
                        System.out.print("Enter new Writer (leave blank to keep unchanged): ");
                        String updateWriter = scanner.nextLine().trim();
                        System.out.print("Enter new Status (Available/Checked Out): ");
                        String updateStatus = scanner.nextLine().trim();
                        library.modifyBook(updateId, updateName, updateWriter, updateStatus);
                        break;
                    case 5:
                        System.out.print("Enter ID to remove: ");
                        String removeId = scanner.nextLine().trim();
                        library.removeBook(removeId);
                        break;
                    case 6:
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}


// Model class representing a Book
class Book {
    private final String id;
    private String name;
    private String writer;
    private final String category;
    private String status;

    public Book(String id, String name, String writer, String category, String status) {
        this.id = id;
        this.name = name;
        this.writer = writer;
        this.category = category;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("Available") || status.equals("Checked Out")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status.");
        }
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Writer: " + writer + ", Category: " + category + ", Status: " + status;
    }
}

// Service class handling Library operations
class Library {
    private final Map<String, Book> collection = new HashMap<String, Book>();

    public void addBook(Book book) {
        if (collection.containsKey(book.getId())) {
            throw new IllegalArgumentException("ID already exists.");
        }
        collection.put(book.getId(), book);
        System.out.println("Book successfully added.");
    }

    public void displayAllBooks() {
        if (collection.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book book : collection.values()) {
            System.out.println(book);
        }
    }

    public void findBook(String keyword) {
        boolean found = false;
        for (Book book : collection.values()) {
            if (book.getId().equalsIgnoreCase(keyword) || book.getName().equalsIgnoreCase(keyword)) {
                System.out.println(book);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No matching book found.");
        }
    }

    public void modifyBook(String id, String name, String writer, String status) {
        Book book = collection.get(id);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (!name.isEmpty()) book.setName(name);
        if (!writer.isEmpty()) book.setWriter(writer);
        book.setStatus(status);
        System.out.println("Book updated successfully.");
    }

    public void removeBook(String id) {
        if (collection.remove(id) != null) {
            System.out.println("Book successfully removed.");
        } else {
            System.out.println("Book not found.");
        }
    }
}

