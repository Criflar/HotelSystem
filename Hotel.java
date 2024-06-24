import java.util.*;
import java.time.*;

public class Hotel {
    private String name;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<Integer> availRooms = new ArrayList<Integer>();
    private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    private double earnings;
    private String roomName = "A0";
    private int totalAvailable; // Values will change upon use.
    private int totalBooked; // Values will change upon use.

    public Hotel (String name, double price) {
        this.name = name;
        this.rooms.add(new Room (roomName));
        this.earnings = rooms.size() * rooms.get(0).getBasePrice();
    }


    public void viewHighInfo() {
        System.out.println("Listed below are the high-level information for this hotel: ");
        System.out.println("  Name: " + name);
        System.out.println("  Total number of rooms: " + rooms.size());
        updateEarnings();
        System.out.println("  Earning forecast for the month: " + getEarnings());
    }

    public void viewLowInfo1() { 
        Scanner input = new Scanner(System.in);
        int checkIn, checkOut;
        //String response;


        //i.
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

        System.out.println(" For Days " + checkIn + " to " + checkOut + ", here are the total available and booked rooms:");
        System.out.println("  Available: " + totalAvailable);
        System.out.println("  Booked: " + totalBooked);

    }

    public void viewLowInfo2(){
        Scanner input = new Scanner(System.in);
        int roomName;
        int i;


        //ii.
        System.out.println("To access the room information, please select a room (input its number within the list): ");
        
        for (i = 0; i < rooms.size(); i++){
            System.out.println((i+1) + ".) " + rooms.get(i).getName());
        }
        System.out.println("Room: ");
        roomName = input.nextInt();
        roomName = roomName - 1;


       System.out.println("Room Name: " + rooms.get(roomName).getName());
       System.out.println("Price per Night: " + rooms.get(roomName).getBasePrice());
       System.out.println("Availability of the room for the month: "); 

       for (i = 0; i < 31; i++){
            rooms.get(roomName).displayBooked("YES");
       }

    }

    public void viewLowInfo3(){
        Scanner input = new Scanner(System.in);
        int guestName;
        int i;


        //iii.
        System.out.println("To access the reservation information, please input the guest's number on the list: ");

        for (i = 0; i < reservations.size(); i++){
            System.out.println((i+1) + ".) " + reservations.get(i).getGuestName());
        }
        System.out.println("Guest: ");
        guestName = input.nextInt();
        guestName = guestName - 1;

        System.out.println("Guest Name: " + reservations.get(guestName).getGuestName());   

        System.out.println("Room Info: ");
        System.out.println("  Room Name: "  + reservations.get(guestName).roomInfo().getName());
        System.out.println("  Room BasePrice: "  + reservations.get(guestName).roomInfo().getBasePrice());

        System.out.println("  Check-in Date: Day " + reservations.get(guestName).getCheckIn());
        System.out.println("  Check-out Date: Day " + reservations.get(guestName).getCheckOut());

        System.out.println("  Total Price for Stay:  " + reservations.get(guestName).displayTotalPrice());
        reservations.get(guestName).displayPriceBreakdown();
    }

    public String getName() {
        return this.name;
    }

    public void getAvailRooms (int checkIn, int checkOut) {
        int i;
        availRooms.clear();
        System.out.println("The following are the available rooms on your scheduled dates: ");
        for (i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).checkAvailability(checkIn, checkOut)) {
                System.out.println((i+1) + "  .) Room " + rooms.get(i).getName() + ": " + "\u001b[32;1m" + "AVAILABLE" + "\u001b[0m");
                availRooms.add(i);
            }
        }
    }

    public void getAllRooms (int checkIn, int checkOut) {
        int i;
        availRooms.clear();
        System.out.println("The following are the rooms and their status on your scheduled dates: ");
        for (i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).checkAvailability(checkIn, checkOut)) {
                System.out.println((i+1) + "  .) Room " + rooms.get(i).getName() + ": " + "\u001b[32;1m" + "AVAILABLE" + "\u001b[0m");
                availRooms.add(i);
            }
            else
                System.out.println((i+1) + "  .) Room " + rooms.get(i).getName() + ": " + "\u001b[31;1m" + "Booked" + "\u001b[0m");
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

        for (i = 0; i < rooms.size(); i++) {
            System.out.println((i+1) + ".) " + rooms.get(i).getName());
        }
    }

    public void addRoom() {
        String name = rooms.get(rooms.size() - 1).getName();
        char letterPart = name.charAt(0); // Gets the letter of the room name.
        int numberPart = Integer.parseInt(name.substring(1)); // Gets the number of the room name.

        numberPart++; 
        if (numberPart == 10) { // If the number part reaches 10, increment to the letter.
            letterPart++;
            numberPart = 0;
        }

        name = String.format("%c%d", letterPart, numberPart);
        rooms.add(new Room (name));
    }

    public void removeRoom (String name) {
        int i;
        int j;

        System.out.println("If you would like to remove a room from the list, ");

        for (i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getName().equals(name))  {// If the name input is found in the ArrayList.
                for (j = 0; j < reservations.size(); j++) {
                    if (reservations.get(j).roominfo().getName().equals(name))
                        rooms.remove(i);
                    else
                        System.out.println("The room still has an existing reservation.");
                }
            }
        }
    }

    public void updateEarnings(){
        int i;
        double total = 0;

        for (i = 0; i < reservations.size(); i++)
            total += (double)reservations.get(i).displayTotalPrice();

        this.earnings = total;
    }

    public double getEarnings() {
        return earnings;
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

    public void addReservation(String guestName, int checkIn, int checkOut, Room room) {
        int i;

        for (i = (checkIn-1); i < room.daysBooked.get(checkOut-1); i++) { // Sets the availablity of the days.
            room.daysBooked.set(i, true);
        }
        reservations.add(new Reservation(guestName, checkIn, checkOut, room));
    }

    public void removeReservation() {
        Scanner input = new Scanner(System.in);
        int guestName;
        int i;

        System.out.println("To remove a reservation, please input the guest's number from the list: ");

        for (i = 0; i < reservations.size(); i++) {
            System.out.println((i+1) + ".) " + reservations.get(i).getGuestName());
        }
        System.out.println("Guest: ");
        guestName = input.nextInt() - 1;
        while (guestName < 1 || guestName > reservations.size()) {
            System.out.println("Invalid input. Please only enter a value between 1 and " + reservations.size() + ": ");
            guestName = input.nextInt() - 1;
        }
        reservations.remove(guestName);
    }

    public void updateBasePrice(double price) {
        int i;
        if (reservations.isEmpty()) {
            for (i = 0; i < rooms.size(); i++) {
                rooms.get(i).setBasePrice(price);
            }

        } else
            System.out.println("There should be no reservations in the entire hotel before updating the base price of the rooms.");
    }
}
