import java.util.ArrayList;
import java.util.Scanner;

public class Hotel {
    private String name;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private double earnings;
    private int totalAvailable;
    private int totalBooked;

    public Hotel(String name) {
        this.name = name;
        rooms.add(new Room("A0")); // Initial room setup
        updateEarnings();
    }

    public void setHotelName(String name) {
        this.name = name;
        System.out.println("Hotel name updated to: " + name);
    }

    public String getName() {
        return name;
    }

    public double getEarnings() {
        return earnings;
    }

    public int getTotalAvailable() {
        return totalAvailable;
    }

    public int getTotalBooked() {
        return totalBooked;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public int getNextRoom(int checkIn, int checkOut){
        int i;

        for (i = 0; i < rooms.size(); i++){
            if (rooms.get(i).checkAvailability(checkIn, checkOut)){
                return i;
            }
        }
        return -1;
    }

    public Room getRoom(int n){
        return rooms.get(n);
    }

    public void viewHighInfo() {
        System.out.println("Listed below are the high-level information for this hotel: ");
        System.out.println("  Name: " + name);
        System.out.println("  Total number of rooms: " + rooms.size());
        System.out.println("  Earning forecast for the month: $" + String.format("%.2f", earnings));
    }

    public void viewLowInfo1() {
        Scanner input = new Scanner(System.in);
        int checkIn, checkOut;

        System.out.println("Listed below are the low-level information for this hotel: ");

        System.out.println("To see the total available and booked rooms, please select a check-in date (1-30): ");
        checkIn = input.nextInt();
        while (checkIn <= 0 || checkIn >= 31) {
            System.out.println("Invalid value. Please enter a check-in date between 1 and 30: ");
            checkIn = input.nextInt();
        }

        System.out.println("Next, please select a check-out date (2-31, after check-in date): ");
        checkOut = input.nextInt();
        while (checkOut >= 32 || checkOut <= 1 || checkOut <= checkIn) {
            System.out.println("Invalid value. Please enter a check-out date between 2 and 31, after the check-in date: ");
            checkOut = input.nextInt();
        }

        getTotalAvailable(checkIn, checkOut);
        getTotalBooked(checkIn, checkOut);

        System.out.println(" For Days " + checkIn + " to " + checkOut + ", here are the total available and booked rooms:");
        System.out.println("  Available: " + totalAvailable);
        System.out.println("  Booked: " + totalBooked);
    }

    public void viewLowInfo2() {
        Scanner input = new Scanner(System.in);
        int roomIndex;

        System.out.println("To access the room information, please select a room number:");

        for (int i = 0; i < rooms.size(); i++) {
            System.out.println((i + 1) + ".) Room " + rooms.get(i).getName());
        }

        System.out.println("Room number: ");
        roomIndex = input.nextInt();
        roomIndex--; // Adjust to zero-based index

        Room selectedRoom = rooms.get(roomIndex);
        System.out.println("Room Name: " + selectedRoom.getName());
        System.out.println("Price per Night: " + selectedRoom.getBasePrice());
        System.out.println("Availability of the room for the month: ");
        selectedRoom.displayBooked("YES");
    }

    public void viewLowInfo3() {
        Scanner input = new Scanner(System.in);
        int guestIndex;

        System.out.println("To access the reservation information, please input the guest's number on the list: ");

        for (int i = 0; i < reservations.size(); i++) {
            System.out.println((i + 1) + ".) " + reservations.get(i).getGuestName());
        }
        System.out.println("Guest number: ");
        guestIndex = input.nextInt();
        guestIndex--; // Adjust to zero-based index

        Reservation selectedReservation = reservations.get(guestIndex);
        System.out.println("Guest Name: " + selectedReservation.getGuestName());
        System.out.println("Room Info: ");
        System.out.println("  Room Name: " + selectedReservation.getRoomInfo().getName());
        System.out.println("  Room BasePrice: " + selectedReservation.getRoomInfo().getBasePrice());
        System.out.println("  Check-in Date: Day " + selectedReservation.getCheckIn());
        System.out.println("  Check-out Date: Day " + selectedReservation.getCheckOut());
        System.out.println("  Total Price for Stay: $" + String.format("%.2f", selectedReservation.getTotalPrice()));
        selectedReservation.displayPriceBreakdown();
    }

    public void getAvailRooms(int checkIn, int checkOut) {
        System.out.println("The following are the available rooms on your scheduled dates: ");
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).checkAvailability(checkIn, checkOut)) {
                System.out.println((i + 1) + ". Room " + rooms.get(i).getName() + ": AVAILABLE");
            }
        }
    }

    public void getAllRooms(int checkIn, int checkOut) {
        System.out.println("The following are the rooms and their status on your scheduled dates: ");
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).checkAvailability(checkIn, checkOut)) {
                System.out.println((i + 1) + ". Room " + rooms.get(i).getName() + ": AVAILABLE");
            } else {
                System.out.println((i + 1) + ". Room " + rooms.get(i).getName() + ": Booked");
            }
        }
    }

    private void getTotalAvailable(int checkIn, int checkOut) {
        totalAvailable = 0;
        for (Room room : rooms) {
            if (room.checkAvailability(checkIn, checkOut)) {
                totalAvailable++;
            }
        }
    }

    private void getTotalBooked(int checkIn, int checkOut) {
        totalBooked = 0;
        for (Room room : rooms) {
            if (!room.checkAvailability(checkIn, checkOut)) {
                totalBooked++;
            }
        }
    }

    public void displayRooms() {
        System.out.println("List of rooms in the hotel:");
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println((i + 1) + ". Room: " + rooms.get(i).getName());
        }
    }

    public void addRoom() {
        String lastRoomName = rooms.get(rooms.size() - 1).getName();
        char letterPart = lastRoomName.charAt(0); // Get the letter part of the room name
        int numberPart = Integer.parseInt(lastRoomName.substring(1)); // Get the number part of the room name

        numberPart++;
        if (numberPart == 10) {
            letterPart++;
            numberPart = 0;
        }

        String newRoomName = String.format("%c%d", letterPart, numberPart);
        rooms.add(new Room(newRoomName));
    }

    public void removeRoom(String roomName) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getName().equals(roomName)) {
                boolean canRemove = true;
                for (Reservation reservation : reservations) {
                    if (reservation.getRoomInfo().getName().equals(roomName)) {
                        canRemove = false;
                        break;
                    }
                }
                if (canRemove) {
                    rooms.remove(i);
                    System.out.println("Room " + roomName + " removed successfully.");
                } else {
                    System.out.println("The room " + roomName + " has reservations and cannot be removed.");
                }
                return;
            }
        }
        System.out.println("Room " + roomName + " not found.");
    }

    public void addReservation(String guestName, int checkIn, int checkOut, Room room) {
        if (room.checkAvailability(checkIn, checkOut)) {
            reservations.add(new Reservation(guestName, checkIn, checkOut, room));
            updateRoomAvailability(room, checkIn, checkOut, true);
            updateEarnings();
            System.out.println("Reservation added successfully.");
        } else {
            System.out.println("Room " + room.getName() + " is not available for the selected dates.");
        }
    }

    public void removeReservation() {
        Scanner input = new Scanner(System.in);
        System.out.println("To remove a reservation, please select the guest number:");
        for (int i = 0; i < reservations.size(); i++) {
            System.out.println((i + 1) + ". Guest: " + reservations.get(i).getGuestName());
        }
        int guestIndex = input.nextInt();
        if (guestIndex > 0 && guestIndex <= reservations.size()) {
            Reservation removedReservation = reservations.remove(guestIndex - 1);
            updateRoomAvailability(removedReservation.getRoomInfo(), removedReservation.getCheckIn(), removedReservation.getCheckOut(), false);
            updateEarnings();
            System.out.println("Reservation removed successfully.");
        } else {
            System.out.println("Invalid guest number.");
        }
    }

    public void updateBasePrice(double price) {
        boolean canUpdate = true;
        for (Reservation reservation : reservations) {
            if (reservation.getCheckIn() <= 0) {
                canUpdate = false;
                break;
            }
        }
        if (canUpdate) {
            for (Room room : rooms) {
                room.setBasePrice(price);
            }
            System.out.println("Base price updated successfully.");
        } else {
            System.out.println("There are reservations in the hotel. Base price cannot be updated.");
        }
    }

    private void updateRoomAvailability(Room room, int checkIn, int checkOut, boolean booked) {
        for (int i = checkIn - 1; i < checkOut; i++) {
            room.getDaysBooked().set(i, booked);
        }
    }

    private void updateEarnings() {
        earnings = 0;
        for (Reservation reservation : reservations) {
            earnings += reservation.getTotalPrice();
        }
    }
}
