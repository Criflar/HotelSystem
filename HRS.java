import java.util.ArrayList;
import java.util.Scanner;

public class HRS {
    private ArrayList<Hotel> hotelList = new ArrayList<>();

    public void displayHotels() {
        System.out.println("Available Hotels:");
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println((i + 1) + ".) " + hotelList.get(i).getName());
        }
    }

    public void book() {
        Scanner input = new Scanner(System.in);
        int chosenHotelIndex, choice, checkIn, checkOut, chosenRoomIndex;
        String guestName;

        System.out.println("Select a hotel to book:");
        displayHotels();
        chosenHotelIndex = input.nextInt() - 1;
        while (chosenHotelIndex < 0 || chosenHotelIndex >= hotelList.size()) {
            System.out.println("Invalid input. Please choose a valid hotel number:");
            chosenHotelIndex = input.nextInt() - 1;
        }

        System.out.println("Enter Check-In Date (Day 1-30): ");
        checkIn = input.nextInt();
        while (checkIn < 1 || checkIn > 30) {
            System.out.println("Invalid input. Please enter a number between 1 and 30:");
            checkIn = input.nextInt();
        }

        System.out.println("Enter Check-Out Date (Day 2-31): ");
        checkOut = input.nextInt();
        while (checkOut < 2 || checkOut > 31 || checkOut <= checkIn) {
            System.out.println("Invalid input. Please enter a number between 2 and 31 and after Check-In Date:");
            checkOut = input.nextInt();
        }

        System.out.println("Select your booking preference:");
        System.out.println("1. Let system choose available room");
        System.out.println("2. View available rooms");
        System.out.println("3. View all rooms status");
        choice = input.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid input. Please enter 1, 2, or 3:");
            choice = input.nextInt();
        }

        switch (choice) {
            case 1:
                int nextRoomIndex = hotelList.get(chosenHotelIndex).getNextRoom(checkIn, checkOut);
                if (nextRoomIndex == -1) {
                    System.out.println("No available rooms for selected dates.");
                    break;
                }
                Room nextRoom = hotelList.get(chosenHotelIndex).getRoom(nextRoomIndex);
                System.out.println("Enter guest name:");
                guestName = input.nextLine(); // Consume newline left by nextInt
                guestName = input.nextLine(); // Read actual guest name
                hotelList.get(chosenHotelIndex).addReservation(guestName, checkIn, checkOut, nextRoom);
                System.out.println("Reservation successful for Room " + nextRoom.getName());
                break;
            case 2:
                hotelList.get(chosenHotelIndex).getAvailRooms(checkIn, checkOut);
                System.out.println("Enter room number:");
                chosenRoomIndex = input.nextInt() - 1;
                while (!hotelList.get(chosenHotelIndex).getAvailRooms().contains(chosenRoomIndex)) {
                    System.out.println("Invalid input. Please choose an available room:");
                    chosenRoomIndex = input.nextInt() - 1;
                }
                Room chosenRoom = hotelList.get(chosenHotelIndex).getRoom(chosenRoomIndex);
                System.out.println("Enter guest name:");
                guestName = input.nextLine(); // Consume newline left by nextInt
                guestName = input.nextLine(); // Read actual guest name
                hotelList.get(chosenHotelIndex).addReservation(guestName, checkIn, checkOut, chosenRoom);
                System.out.println("Reservation successful for Room " + chosenRoom.getName());
                break;
            case 3:
                hotelList.get(chosenHotelIndex).getAllRooms(checkIn, checkOut);
                System.out.println("Enter room number:");
                chosenRoomIndex = input.nextInt() - 1;
                while (!hotelList.get(chosenHotelIndex).getAvailRooms().contains(chosenRoomIndex)) {
                    System.out.println("Invalid input. Please choose an available room:");
                    chosenRoomIndex = input.nextInt() - 1;
                }
                Room selectedRoom = hotelList.get(chosenHotelIndex).getRoom(chosenRoomIndex);
                System.out.println("Enter guest name:");
                guestName = input.nextLine(); // Consume newline left by nextInt
                guestName = input.nextLine(); // Read actual guest name
                hotelList.get(chosenHotelIndex).addReservation(guestName, checkIn, checkOut, selectedRoom);
                System.out.println("Reservation successful for Room " + selectedRoom.getName());
                break;
        }
    }

    public void addHotel(Hotel hotel) {
        hotelList.add(hotel);
    }

    public void removeHotel(int hotelIndex) {
        hotelList.remove(hotelIndex);
    }
}
