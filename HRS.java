import java.util.ArrayList;
import java.util.Scanner;

public class HRS {
    private ArrayList<Hotel> hotelList;

    // Constructor to initialize HRS with an empty list of hotels
    public HRS() {
        this.hotelList = new ArrayList<>();
    }

    // Constructor to initialize HRS with a predefined list of hotels
    public HRS(ArrayList<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    public void displayHotels() {
        if (hotelList.isEmpty()) {
            System.out.println("No hotels available.");
            return;
        }

        System.out.println("List of Available Hotels:");
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println((i + 1) + ". " + hotelList.get(i).getName());
        }
    }

    public void book() {
        Scanner scanner = new Scanner(System.in);
        int chosenHotelIndex;
        String guestName;
        int checkIn, checkOut;
        int choice;

        displayHotels();

        if (hotelList.isEmpty()) {
            System.out.println("No hotels available to book.");
            return;
        }

        System.out.print("Enter the hotel number to book: ");
        chosenHotelIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline after nextInt()

        while (chosenHotelIndex < 0 || chosenHotelIndex >= hotelList.size()) {
            System.out.println("Invalid input. Please enter a valid hotel number: ");
            chosenHotelIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline after nextInt()
        }

        Hotel chosenHotel = hotelList.get(chosenHotelIndex);

        System.out.print("Enter Check-In Date (1-30): ");
        checkIn = scanner.nextInt();
        while (checkIn < 1 || checkIn > 30) {
            System.out.print("Invalid input. Please enter a valid Check-In Date (1-30): ");
            checkIn = scanner.nextInt();
        }

        System.out.print("Enter Check-Out Date (2-31): ");
        checkOut = scanner.nextInt();
        while (checkOut < 2 || checkOut > 31 || checkOut <= checkIn) {
            System.out.print("Invalid input. Please enter a valid Check-Out Date (2-31, after Check-In): ");
            checkOut = scanner.nextInt();
        }

        System.out.println("Choose an option:");
        System.out.println("1. Let us choose a room for you.");
        System.out.println("2. See available rooms.");
        System.out.println("3. See all rooms and their status.");
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline after nextInt()

        switch (choice) {
            case 1:
                int roomIndex = chosenHotel.getNextRoom(checkIn, checkOut);
                if (roomIndex == -1) {
                    System.out.println("Sorry! No available rooms for your dates.");
                    break;
                }
                Room room = chosenHotel.getRoom(roomIndex);
                System.out.print("Enter your name: ");
                guestName = scanner.nextLine();
                chosenHotel.addReservation(guestName, checkIn, checkOut, room);
                System.out.println("Reservation confirmed! Room " + room.getName() + " booked.");
                break;
            case 2:
                chosenHotel.getAvailRooms(checkIn, checkOut);
                System.out.print("Enter the room number to book: ");
                int roomNumber = scanner.nextInt() - 1;
                scanner.nextLine(); // Consume newline after nextInt()
                if (roomNumber < 0 || roomNumber >= chosenHotel.getRooms().size()) {
                    System.out.println("Invalid room number.");
                    break;
                }
                Room selectedRoom = chosenHotel.getRoom(roomNumber);
                System.out.print("Enter your name: ");
                guestName = scanner.nextLine();
                chosenHotel.addReservation(guestName, checkIn, checkOut, selectedRoom);
                System.out.println("Reservation confirmed! Room " + selectedRoom.getName() + " booked.");
                break;
            case 3:
                chosenHotel.getAllRooms(checkIn, checkOut);
                System.out.print("Enter the room number to book: ");
                int roomNum = scanner.nextInt() - 1;
                scanner.nextLine(); // Consume newline after nextInt()
                if (roomNum < 0 || roomNum >= chosenHotel.getRooms().size()) {
                    System.out.println("Invalid room number.");
                    break;
                }
                Room roomSelected = chosenHotel.getRoom(roomNum);
                System.out.print("Enter your name: ");
                guestName = scanner.nextLine();
                chosenHotel.addReservation(guestName, checkIn, checkOut, roomSelected);
                System.out.println("Reservation confirmed! Room " + roomSelected.getName() + " booked.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public void addHotel() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the new hotel: ");
        String hotelName = scanner.nextLine();
        Hotel newHotel = new Hotel(hotelName);
        hotelList.add(newHotel);
        System.out.println("Hotel '" + hotelName + "' added successfully.");
    }

    public void removeHotel(int index) {
        if (index < 0 || index >= hotelList.size()) {
            System.out.println("Invalid hotel index.");
            return;
        }
        hotelList.remove(index);
        System.out.println("Hotel removed successfully.");
    }

    public ArrayList<Hotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(ArrayList<Hotel> hotelList) {
        this.hotelList = hotelList;
    }
}
