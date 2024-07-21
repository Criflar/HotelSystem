import java.util.ArrayList;
import java.util.Scanner;

/**
 * The HRS class represents a Hotel Reservation System that manages a list of hotels.
 * It allows users to view available hotels, book rooms, and manage hotel listings.
 */
public class HRS {
    private ArrayList<Hotel> hotelList; // List of hotels
    private Scanner scanner = new Scanner(System.in); // Scanner for user input

    /**
     * Constructor to initialize HRS with an empty list of hotels.
     * Post-condition: Initializes an empty ArrayList for hotelList.
     */
    public HRS() {
        this.hotelList = new ArrayList<>();
    }

    /**
     * Displays the list of hotels available in the system.
     * Post-condition: Prints the list of available hotels to the console.
     */
    public void displayHotels() {
        if (hotelList.isEmpty()) {
            System.out.println("No hotels available.");
            return;
        }

        System.out.println("\nList of Available Hotels:");
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + "\u001b[36;1m" + hotelList.get(i).getName() + "\u001b[0m");
        }
        System.out.println();
    }

    /**
     * Facilitates booking a room in a chosen hotel.
     * Pre-condition: There must be at least one hotel in hotelList.
     *               Check-in and check-out dates must be valid (1-30 for check-in, 2-31 for check-out).
     * Post-condition: The room is booked if available for the specified dates and guest name.
     */
    public void book() {
        int chosenHotelIndex;
        String guestName;
        int checkIn, checkOut;
        int choice;
        int option;
        boolean avail;
        boolean roomBooked;

        if (hotelList.isEmpty()) {
            System.out.println("No hotels available to book.");
            return;
        }

        System.out.print("Enter the hotel number to book: ");
        chosenHotelIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline after nextInt()

        while (chosenHotelIndex < 0 || chosenHotelIndex >= hotelList.size()) {
            System.out.print("Invalid input. Please enter a valid hotel number: ");
            chosenHotelIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline after nextInt()
        }

        Hotel chosenHotel = hotelList.get(chosenHotelIndex);

        System.out.print("\nEnter Check-In Date (1-30): ");
        checkIn = scanner.nextInt();
        while (checkIn < 1 || checkIn > 30) {
            System.out.print("\nInvalid input. Please enter a valid Check-In Date (1-30): ");
            checkIn = scanner.nextInt();
        }

        System.out.print("Enter Check-Out Date (2-31): ");
        checkOut = scanner.nextInt();
        while (checkOut < 2 || checkOut > 31 || checkOut <= checkIn) {
            System.out.print("\nInvalid input. Please enter a valid Check-Out Date (2-31, after Check-In): ");
            checkOut = scanner.nextInt();
        }

        System.out.println("\nChoose an option:");
        System.out.println("[1] Let us choose a room for you.");
        System.out.println("[2] See available rooms.");
        System.out.println("[3] See all rooms and their status.\n");
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline after nextInt()

        switch (choice) {
            case 0:
                System.out.println("\nBooking canceled. Going back to the previous menu...\n");
                break;

            case 1:
                roomBooked = false;
                while (!roomBooked) {
                    int roomIndex = chosenHotel.getNextRoom(checkIn, checkOut);
                    if (roomIndex == -1) {
                        System.out.println("\nSorry! No available rooms for your dates.");
                        break;
                    }
                    Room room = chosenHotel.getRoom(roomIndex);

                    System.out.print("To confirm your booking, please enter your name: ");
                    guestName = scanner.nextLine();

                    System.out.println("\nAre you sure you want to book this room?");
                    System.out.println("[1] Yes                  [2] No");
                    System.out.print("INPUT: ");
                    option = scanner.nextInt();
                    scanner.nextLine();
                    while (option != 1 && option != 2) {
                        Driver.confirmationChoices();
                        option = scanner.nextInt();
                        scanner.nextLine(); // leftover newline
                    }

                    if (option == 1) {
                        if (room.checkAvailability(checkIn, checkOut)) {
                            chosenHotel.addReservation(guestName, checkIn, checkOut, room);
                            System.out.println("\nReservation confirmed! Room \u001b[36;1m" + room.getName() + "\u001b[0m booked.");
                            roomBooked = true;
                        } else {
                            System.out.println("Sorry, that room is unavailable. Please try again.");
                            // Check if there are still any rooms available
                            if (chosenHotel.getNextRoom(checkIn, checkOut) == -1) {
                                System.out.println("\nSorry! No other available rooms for your dates.");
                                break;
                            }
                        }
                    } else {
                        System.out.println("\nBooking canceled. Going back to the previous menu...\n");
                        break;
                    }
                }
                break;

            case 2:
                avail = chosenHotel.getAvailRooms(checkIn, checkOut);

                if (!avail) {
                    System.out.println("\nSorry! No available rooms for your dates.");
                    break;
                }

                roomBooked = false;
                while (!roomBooked) {
                    System.out.print("\nEnter the room number to book: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline after nextInt()

                    if (roomNumber == 0) {
                        System.out.println("\nBooking canceled. Going back to the previous menu...\n");
                        break;
                    }

                    roomNumber--;
                    while (roomNumber < 0 || roomNumber >= chosenHotel.getRooms().size()) {
                        System.out.println("\nInvalid room number. Please input a number from the list: ");
                        roomNumber = scanner.nextInt();
                        scanner.nextLine(); // leftover newline
                    }

                    Room selectedRoom = chosenHotel.getRoom(roomNumber);

                    if (!selectedRoom.checkAvailability(checkIn, checkOut)) {
                        System.out.println("Sorry, that room is unavailable. Please choose another room.");
                        // Check if there are still any rooms available
                        avail = chosenHotel.getAvailRooms(checkIn, checkOut);
                        if (!avail) {
                            System.out.println("\nSorry! No other available rooms for your dates.");
                            break;
                        }
                        continue; // Ask for another room
                    }

                    System.out.print("\nTo confirm your booking, please enter your name: ");
                    guestName = scanner.nextLine();

                    System.out.println("\nAre you sure you want to book this room?");
                    System.out.println("[1] Yes                  [2] No");
                    System.out.print("INPUT: ");
                    option = scanner.nextInt();
                    scanner.nextLine();
                    while (option != 1 && option != 2) {
                        Driver.confirmationChoices();
                        option = scanner.nextInt();
                        scanner.nextLine(); // leftover newline
                    }

                    if (option == 1) {
                        chosenHotel.addReservation(guestName, checkIn, checkOut, selectedRoom);
                        System.out.println("\nReservation confirmed! Room \u001b[36;1m" + selectedRoom.getName() + "\u001b[0m booked.");
                        roomBooked = true;
                    } else {
                        System.out.println("\nBooking canceled. Going back to the previous menu...\n");
                        break;
                    }
                }
                break;

            case 3:
                avail = chosenHotel.getAllRooms(checkIn, checkOut);

                if (!avail) {
                    System.out.println("\nSorry! No available rooms for your dates.");
                    break;
                }

                roomBooked = false;
                while (!roomBooked) {
                    System.out.print("\nEnter the room number to book: ");
                    int roomNum = scanner.nextInt();
                    scanner.nextLine(); // Consume newline after nextInt()

                    if (roomNum == 0) {
                        System.out.println("\nBooking canceled. Going back to the previous menu...\n");
                        break;
                    }

                    roomNum--;
                    while (roomNum < 0 || roomNum >= chosenHotel.getRooms().size()) {
                        System.out.println("\nInvalid room number. Please input a number from the list: ");
                        roomNum = scanner.nextInt();
                        scanner.nextLine(); // leftover newline
                    }

                    Room roomSelected = chosenHotel.getRoom(roomNum);

                    if (!roomSelected.checkAvailability(checkIn, checkOut)) {
                        System.out.println("Sorry, that room is unavailable. Please choose another room.");
                        // You can add a check here to see if there are still any rooms available
                        avail = chosenHotel.getAllRooms(checkIn, checkOut);
                        if (!avail) {
                            System.out.println("\nSorry! No other available rooms for your dates.");
                            break;
                        }
                        continue; // Ask for another room
                    }

                    System.out.print("\nTo confirm your booking, please enter your name: ");
                    guestName = scanner.nextLine();

                    System.out.println("\nAre you sure you want to book this room?");
                    System.out.println("[1] Yes                  [2] No");
                    System.out.print("INPUT: ");
                    option = scanner.nextInt();
                    scanner.nextLine();
                    while (option != 1 && option != 2) {
                        Driver.confirmationChoices();
                        option = scanner.nextInt();
                        scanner.nextLine(); // leftover newline
                    }

                    if (option == 1) {
                        chosenHotel.addReservation(guestName, checkIn, checkOut, roomSelected);
                        System.out.println("\nReservation confirmed! Room \u001b[36;1m" + roomSelected.getName() + "\u001b[0m booked.");
                        roomBooked = true;
                    } else {
                        System.out.println("\nBooking canceled. Going back to the previous menu...\n");
                        break;
                    }
                }
                break;
        }
    }

    /**
     * Adds a new hotel to the system with the given name.
     *
     * @param hotelName the name of the hotel to add
     */
    public void addHotel(String hotelName) {
        Hotel newHotel = new Hotel(hotelName);
        hotelList.add(newHotel);
    }

    /**
     * Removes the hotel at the specified index from the system.
     *
     * @param index the index of the hotel to remove
     */
    public void removeHotel(int index) {
        if (index < 0 || index >= hotelList.size()) {
            System.out.println("Invalid hotel index.");
            return;
        }
        hotelList.remove(index);
        System.out.println("Hotel removed successfully.");
    }

    /**
     * Retrieves the list of hotels currently in the system.
     *
     * @return the ArrayList containing the list of hotels
     */
    public ArrayList<Hotel> getHotelList() {
        return hotelList;
    }

}
