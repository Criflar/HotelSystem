import java.util.Scanner;

public class HotelReservationSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = null;
        HotelManager hotelManager = new HotelManager();

        while (true) {
            displayMainMenu();
            int choice = getIntInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    hotel = hotelManager.createHotel(scanner);
                    break;
                case 2:
                    if (hotel == null) {
                        System.out.println("No hotel created yet.");
                    } else {
                        hotelManager.viewHotelInformation(hotel, scanner);
                    }
                    break;
                case 3:
                    if (hotel == null) {
                        System.out.println("No hotel created yet.");
                    } else {
                        hotelManager.manageHotel(hotel, scanner);
                    }
                    break;
                case 4:
                    if (hotel == null) {
                        System.out.println("No hotel created yet.");
                    } else {
                        hotelManager.simulateBooking(hotel, scanner);
                    }
                    break;
                case 5:
                    System.out.println("Exiting Hotel Reservation System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n===== Hotel Reservation System =====");
        System.out.println("1. Create Hotel");
        System.out.println("2. View Hotel Information");
        System.out.println("3. Manage Hotel");
        System.out.println("4. Simulate Booking");
        System.out.println("5. Exit");
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }
}
