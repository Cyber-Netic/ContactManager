import java.util.Scanner;

public class ContactManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseHelper.createTable();

        while (true) {
            System.out.println("\nContact Manager");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    DatabaseHelper.addContact(name, phone);
                    break;
                case 2:
                    DatabaseHelper.viewContacts();
                    break;
                case 3:
                    System.out.print("Enter Contact ID to Update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Phone: ");
                    String newPhone = scanner.nextLine();
                    DatabaseHelper.updateContact(updateId, newName, newPhone);
                    break;
                case 4:
                    System.out.print("Enter Contact ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    DatabaseHelper.deleteContact(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
