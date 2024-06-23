import java.util.*;
import java.time.*;

public class Hotel {
    private String name;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    private double earnings;
    private String roomName = "A0";
    private int totalAvailable; // Values will change upon use.
    private int totalBooked; // Values will change upon use.

    public Hotel (String name, double price) {
        this.name = name;
        this.rooms.add(new Room (roomName));
        this.earnings = roomAmt * rooms.get(0).getBasePrice();
    }

    public void viewHighInfo() {
        System.out.println("Listed below are the high-level information for this hotel: ");
        System.out.println("  Name: " + name);
        System.out.println("  Total number of rooms: " + rooms.size());
        System.out.println("  Estimated earnings for this month: " + getEarnings);
    }

    public void viewLowInfo() { // lipat siguro dapat to sa HRS para maaccess niya yung methods.
        Scanner input = new Scanner(System.in);
        int checkIn, checkOut;
        String roomName;
        String response;

        System.out.println("Listed below are the low-level information for this hotel: ");
        
        System.out.println("To see the total available and booked rooms, please select a check-in date: ");
        checkIn = input.nextInt();
        while (checkIn <= 0 || checkIn >= 31) {
            System.out.println("Invalid value. Please make sure the check-in date is between 1 and 30 only. Thank you: ");
            checkIn = input.nextInt();
        }
        
        System.out.println("Next, please select a check-out date: ");
        checkOut = input.nextInt();
        while (checkOut >= 32 || checkOut <= 1 || checkOut < checkIn) {
            System.out.println("Invalid value. Please make sure the check-out date is between 2 and 31 only, and that the check-out date is after the check-in date. Thank you: ");
            checkOut = input.nextInt();
        }

        System.out.println(" For Days " + checkIn + " to " + checkOut ", here are the total available and booked rooms:");
        System.out.println("  Available: " + totalAvailable);
        System.out.println("  Booked: " + totalBooked);

        System.out.println("For the room information, please select a room: ");
       


    }

    public String getName() {
        return this.name;
    }

    public void getAvailRoom (int checkIn, int checkOut) { // Changed to check-in and check-out.
        int i;

        for (i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).checkAvailability(checkIn, checkOut))
                System.out.println("Room " + rooms.get(i).getName() + ": " + "\u001b[32;1m" + "AVAILABLE" + "\u001b[0m");
            else
                System.out.println("Room " + rooms.get(i).getName() + ": " + "\u001b[31;1m" + "Booked" + "\u001b[0m");
        }
    }

    public void getTotalAvailable(int checkIn, int checkOut) {
        int i;
        int total = 0;

        for (i = 0; i < rooms.size(); i++) {
            if (!rooms.get(i).checkAvailability(checkIn, checkOut))
                total++;
        }

        this.totalAvailable = total;
    }

    public void getTotalBooked(int checkIn, int checkOut) {
        int i;
        int total = 0;

        for (i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).checkAvailability(checkIn, checkOut))
                total++;
        }

        this.totalBooked = total;
    }

    public void setHotelName (String name) {
        this.name = name;
    }

    public void displayRooms() {
        int i;

        for (i = 0, i < rooms.size(); i++) {
            System.out.println((i+1) + ". " + rooms.get(i).getName());
        }
    }

    public void addRoom() {
        String name = rooms.get(rooms.size()-1).getName;
        char letterPart = name.charAt(0); // Gets the letter of the room name.
        int numberPart = Integer.parseInt(name.substring(1)); // Gets the number of the room name.

        numberPart++; 
        if (numberPart == 10) { // If the number part reaches 10, increment to the letter.
            letterPart++;
            numberPart = 0;
        }

        name = String.format("%c%d", letterPart, numbePart);
        rooms.add(new Room (name));
    }

    public void removeRoom (String name) {
        int i;

        for (i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getName.equals(name)) // If the name input is found in the ArrayList.
                rooms.remove(i);
        }
    }

    public double getEarnings() {
        int i;
        double total = 0;

        for (i = 0; i < reservations.size(); i++)
            total += (double)reservations.get(i).getPrice;

        return total;
    }


}
