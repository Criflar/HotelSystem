import java.util.ArrayList;
import java.util.Scanner;

public class Hotel {
    private String name;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private double earnings;

    public Hotel(String name) {
        this.name = name;
        this.earnings = 0.0;
    }

    public void viewHighInfo() {
        System.out.println("Hotel Name: " + name);
        System.out.println("Total Number of Rooms: " + rooms.size());
        System.out.println("Earnings Forecast for the Month: $" + earnings);
    }

    public void viewLowInfo1() {
        Scanner input = new Scanner(System.in);
        int checkIn, checkOut;

        System.out.println("Enter check-in day (1-30): ");
        checkIn = input.nextInt();
        while (checkIn < 1 || checkIn > 30) {
            System.out.println("Invalid input. Enter check-in day (1-30): ");
            checkIn = input.nextInt();
        }

        System.out.println("Enter check-out day (2-31): ");
        checkOut = input.nextInt();
        while (checkOut < 2 || checkOut > 31 || checkOut <= checkIn) {
            System.out.println("Invalid input. Enter check-out day (2-31, after check-in day): ");
            checkOut = input.nextInt();
        }

        int totalAvailable = getTotalAvailable(checkIn, checkOut);
        int totalBooked = getTotalBooked(checkIn, checkOut);

        System.out.println("Total available rooms from day " + checkIn + " to " + checkOut + ": " + totalAvailable);
        System.out.println("Total booked rooms from day " + checkIn + " to " + checkOut + ": " + totalBooked);
    }

    public void viewLowInfo2() {
        Scanner input = new Scanner(System.in);
        int roomIndex;

        System.out.println("Rooms Available:");
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (room.isAvailable()) {
                System.out.println((i + 1) + ". Room " + room.getName() + ": $" + room.getBasePrice() + " per night");
            }
        }

        System.out.println("Select a room number to view details:");
        roomIndex = input.nextInt();
        while (roomIndex < 1 || roomIndex > rooms.size()) {
            System.out.println("Invalid input. Select a room number:");
            roomIndex = input.nextInt();
        }

        Room selectedRoom = rooms.get(roomIndex - 1);
        System.out.println("Room Name: " + selectedRoom.getName());
        System.out.println("Price per Night: $" + selectedRoom.getBasePrice());
        System.out.println("Availability for the month:");
        selectedRoom.displayBooked();
    }

    public void viewLowInfo3() {
        Scanner input = new Scanner(System.in);
        int reservationIndex;

        System.out.println("Reservations:");
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            System.out.println((i + 1) + ". Guest: " + reservation.getGuestName());
        }

        System.out.println("Select a reservation number to view details:");
        reservationIndex = input.nextInt();
        while (reservationIndex < 1 || reservationIndex > reservations.size()) {
            System.out.println("Invalid input. Select a reservation number:");
            reservationIndex = input.nextInt();
        }

        Reservation selectedReservation = reservations.get(reservationIndex - 1);
        System.out.println("Guest Name: " + selectedReservation.getGuestName());
        System.out.println("Room Info:");
        System.out.println("  Room Name: " + selectedReservation.getRoom().getName());
        System.out.println("  Room Base Price: $" + selectedReservation.getRoom().getBasePrice());
        System.out.println("  Check-in Date: Day " + selectedReservation.getCheckIn());
        System.out.println("  Check-out Date: Day " + selectedReservation.getCheckOut());
        System.out.println("  Total Price for Stay: $" + selectedReservation.displayTotalPrice());
        selectedReservation.displayPriceBreakdown();
    }

    public int getTotalAvailable(int checkIn, int checkOut) {
        int count = 0;
        for (Room room : rooms) {
            if (room.isAvailable(checkIn, checkOut)) {
                count++;
            }
        }
        return count;
    }

    public int getTotalBooked(int checkIn, int checkOut) {
        return rooms.size() - getTotalAvailable(checkIn, checkOut);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(String roomName) {
        rooms.removeIf(room -> room.getName().equals(roomName));
    }

    public void updateEarnings(double amount) {
        earnings += amount;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
}
