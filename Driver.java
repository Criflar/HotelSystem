import java.util.*;

public class Driver {

    public static void main(String[] args) {
        HRS hrs = new HRS(); // Creating an instance of Hotel Reservation System

        // Adding some hotels for demonstration
        Hotel hotel1 = new Hotel("Hotel A", 100);
        Hotel hotel2 = new Hotel("Hotel B", 150);
        hrs.addHotel(hotel1);
        hrs.addHotel(hotel2);

        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nWelcome to the Hotel Reservation System!");
            System.out.println("1. Display available hotels");
            System.out.println("2. Book a reservation");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    hrs.displayHotels();
                    break;
                case 2:
                    hrs.book();
                    break;
                case 3:
                    System.out.println("Exiting the system. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 3.");
            }
        } while (choice != 3);

        input.close();
    }
}
