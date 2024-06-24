import java.util.Scanner;

public class HotelManager {

    public Hotel createHotel(Scanner scanner) {
        System.out.print("Enter hotel name: ");
        String hotelName = scanner.nextLine();
        return new Hotel(hotelName);
    }

    public void viewHotelInformation(Hotel hotel, Scanner scanner) {
        displayHotelInfoMenu();
        int choice = getIntInput(scanner, "Enter your choice: ");

        switch (choice) {
            case 1:
                hotel.viewHighInfo();
                break;
            case 2:
                hotel.viewLowInfo1();
                break;
            case 3:
                hotel.viewLowInfo2();
                break;
            case 4:
                hotel.viewLowInfo3();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }
    }

    public void manageHotel(Hotel hotel, Scanner scanner) {
        displayManageHotelMenu();
        int choice = getIntInput(scanner, "Enter your choice: ");

        switch (choice) {
            case 1:
                String newName = getStringInput(scanner, "Enter new hotel name: ");
                hotel.setHotelName(newName);
                break;
            case 2:
                hotel.addRoom();
                break;
            case 3:
                String roomName = getStringInput(scanner, "Enter room name to remove: ");
                hotel.removeRoom(roomName);
                break;
            case 4:
                double newPrice = getDoubleInput(scanner, "Enter new base price for rooms: ");
                hotel.updateBasePrice(newPrice);
                break;
            case 5:
                hotel.removeReservation();
                break;
            case 6:
                hotel = null; // Reset hotel instance
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
        }
    }

    public void simulateBooking(Hotel hotel, Scanner scanner) {
        System.out.println("\n===== Simulate Booking =====");
        int checkIn = getIntInput(scanner, "Enter check-in date (1-30): ");
        int checkOut = getIntInput(scanner, "Enter check-out date (2-31): ");

        hotel.getAvailRooms(checkIn, checkOut);
        hotel.getAllRooms(checkIn, checkOut);
        hotel.getTotalAvailable(checkIn, checkOut);
        hotel.getTotalBooked(checkIn, checkOut);

        int roomIndex = hotel.getNextRoom(checkIn, checkOut);
        if (roomIndex != -1) {
            System.out.println("Room assigned: " + hotel.getRoom(roomIndex).getName());
            String guestName = getStringInput(scanner, "Enter guest name for reservation: ");
            hotel.addReservation(guestName, checkIn, checkOut, hotel.getRoom(roomIndex));
        } else {
            System.out.println("No available rooms for the specified dates.");
        }
    }

    private void displayHotelInfoMenu() {
        System.out.println("\n===== View Hotel Information =====");
        System.out.println("1. View High-level Information");
        System.out.println("2. View Low-level Information - Available and Booked Rooms");
        System.out.println("3. View Low-level Information - Room Details");
        System.out.println("4. View Low-level Information - Reservation Details");
    }

    private void displayManageHotelMenu() {
        System.out.println("\n===== Manage Hotel =====");
        System.out.println("1. Change Hotel Name");
        System.out.println("2. Add Room");
        System.out.println("3. Remove Room");
        System.out.println("4. Update Base Price");
        System.out.println("5. Remove Reservation");
        System.out.println("6. Back to main menu");
    }

    private int getIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

    private String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private double getDoubleInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextDouble();
    }
}
