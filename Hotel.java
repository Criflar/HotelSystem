import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Hotel class represents a hotel with a name, rooms, and reservations.
 * It provides functionality to manage rooms, reservations, and to view information about the hotel.
 */
public class Hotel {
    private String name;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private double earnings;
    private int totalAvailable;
    private int totalBooked;
    private Scanner input = new Scanner(System.in);

    /**
     * Constructor to create a Hotel with a specified name.
     * Initializes with one room ("A0") and updates earnings.
     *
     * @param name the name of the hotel
     */
    public Hotel(String name) {
        this.name = name;
        rooms.add(new Room("A0")); // Initial room setup
        updateEarnings();
    }

    /**
     * Sets the name of the hotel.
     *
     * @param name the new name of the hotel
     */
    public void setHotelName(String name) {
        this.name = name;
        System.out.println("Hotel name updated to: " + name + "\n");
    }

    /**
     * Gets the name of the hotel.
     *
     * @return the name of the hotel
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of rooms in the hotel.
     *
     * @return an ArrayList of Room objects
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Gets the list of reservations in the hotel.
     *
     * @return an ArrayList of Reservation objects
     */
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Finds the index of the next available room for the given dates.
     *
     * @param checkIn  the check-in date
     * @param checkOut the check-out date
     * @return the index of the next available room, or -1 if none are available
     */
    public int getNextRoom(int checkIn, int checkOut) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).checkAvailability(checkIn, checkOut)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the Room object at the specified index.
     *
     * @param n the index of the room
     * @return the Room object at the specified index
     */
    public Room getRoom(int n) {
        return rooms.get(n);
    }

    /**
     * Displays high-level information about the hotel, such as the name, number of rooms, and earnings forecast.
     */
    public void viewHighInfo() {
        System.out.println("\n============= \u001b[33;1mHotel Overview\u001b[0m =============");
        System.out.println("  Name: \u001b[36;1m" + name + "\u001b[0m");
        System.out.println("  Total number of rooms: \u001b[36;1m" + rooms.size() + "\u001b[0m");
        System.out.println("  Earning forecast for the month: $\u001b[36;1m" + String.format("%.2f", earnings) + "\u001b[0m\n");
    }

    /**
     * Displays low-level information about the availability and booking status of rooms for specified dates.
     */
    public void viewLowInfo1() {
        int checkIn, checkOut;

        System.out.println("\nListed below is the overview of the low-level information for this hotel: ");

        // Prompt for check-in date
        System.out.print("To see the total available and booked rooms, please select a check-in date (1-30): ");
        checkIn = input.nextInt();
        while (checkIn <= 0 || checkIn >= 31) {
            System.out.print("Invalid value. Please enter a check-in date between 1 and 30: ");
            checkIn = input.nextInt();
        }

        // Prompt for check-out date
        System.out.print("Next, please select a check-out date (2-31, after check-in date): ");
        checkOut = input.nextInt();
        while (checkOut >= 32 || checkOut <= 1 || checkOut <= checkIn) {
            System.out.print("Invalid value. Please enter a check-out date between 2 and 31, after the check-in date: ");
            checkOut = input.nextInt();
        }

        getTotalAvailable(checkIn, checkOut);
        getTotalBooked(checkIn, checkOut);

        // Display total available and booked rooms for the specified dates
        System.out.println("\n For Days " + checkIn + " to " + checkOut + ", here are the total available and booked rooms:");
        System.out.println("  \u001b[32;1mAVAILABLE\u001b[0m: " + totalAvailable);
        System.out.println("  \u001b[31;1mBooked\u001b[0m: " + totalBooked);
    }

    /**
     * Displays detailed information about a selected room.
     */
    public void viewLowInfo2() {
        int roomIndex;

        System.out.println("\nTo access the room information, please select a room number:");

        // List all rooms with their indices
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println("[" + (i + 1) + "] Room \u001b[36;1m" + rooms.get(i).getName() + "\u001b[0m");
        }

        // Prompt for room number
        System.out.print("Room number: ");
        roomIndex = input.nextInt();
        roomIndex--; // Adjust to zero-based index

        // Display detailed information about the selected room
        Room selectedRoom = rooms.get(roomIndex);
        System.out.println("Room Name: \u001b[36;1m" + selectedRoom.getName() + "\u001b[0m");
        System.out.println("Price per Night: \u001b[36;1m" + selectedRoom.getBasePrice() + "\u001b[0m");
        System.out.println("\nAvailability of the room for the month: ");
        selectedRoom.displayBooked("YES");
    }

    /**
     * Displays detailed information about a selected reservation.
     */
    public void viewLowInfo3() {
        int guestIndex;

        if (reservations.isEmpty()) {
            System.out.println("\nThere are currently no reservations made to this hotel.\n");
        } else {
            System.out.println("\nTo access the reservation information, please input the guest's number on the list: ");

            // List all reservations with their indices
            for (int i = 0; i < reservations.size(); i++) {
                System.out.println("[" + (i + 1) + "] \u001b[36;1m" + reservations.get(i).getGuestName() + "\u001b[0m");
            }

            // Prompt for guest number
            System.out.print("\nGuest number: ");
            guestIndex = input.nextInt();
            guestIndex--; // Adjust to zero-based index

            // Display detailed information about the selected reservation
            Reservation selectedReservation = reservations.get(guestIndex);
            System.out.println("\nGuest Name: \u001b[36;1m" + selectedReservation.getGuestName() + "\u001b[0m");
            System.out.println("Room Info: ");
            System.out.println("  Room Name: \u001b[36;1m" + selectedReservation.getRoomInfo().getName() + "\u001b[0m");
            System.out.println("  Room BasePrice: \u001b[36;1m" + selectedReservation.getRoomInfo().getBasePrice() + "\u001b[0m");
            System.out.println("  Check-in Date: Day \u001b[36;1m" + selectedReservation.getCheckIn() + "\u001b[0m");
            System.out.println("  Check-out Date: Day \u001b[36;1m" + selectedReservation.getCheckOut() + "\u001b[0m");
            System.out.print("  Active Discount:");
            discountDisplay(selectedReservation);
            System.out.println("  Total Price for Stay: \u001b[36;1m$" + String.format("%.2f", selectedReservation.getTotalPrice()) + "\u001b[0m");
            selectedReservation.displayPriceBreakdown();
            System.out.println();
        }
    }

    public void discountDisplay(Reservation r){

        switch (r.getDiscount()){

            case 1: 
                System.out.println("\u001b[36;1m \"I_WORK_HERE\" \u001b[0m");
            break;

            case 2:
                System.out.println("\u001b[36;1m \"STAY4_GET1\" \u001b[0m");
            break;

            case 3:
                System.out.println("\u001b[36;1m \"PAYDAY\" \u001b[0m");
            break;

            case 0:
                System.out.println("\u001b[36;1m N/A \u001b[0m");
            break;

        }
            
    }

    /**
     * Checks the availability of rooms for the given dates and displays the available rooms.
     *
     * @param checkIn  the check-in date
     * @param checkOut the check-out date
     * @return true if there are available rooms, false otherwise
     */
    public boolean getAvailRooms(int checkIn, int checkOut) {
        boolean avail = false;
        System.out.println("The following are the available rooms on your scheduled dates: ");
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).checkAvailability(checkIn, checkOut)) {
                System.out.println((i + 1) + ". Room \u001b[36;1m" + rooms.get(i).getName() + "\u001b[0m: \u001b[32;1mAVAILABLE\u001b[0m");
                avail = true;
            }
        }
        return avail;
    }

    /**
     * Checks the availability of all rooms for the given dates and displays their status.
     *
     * @param checkIn  the check-in date
     * @param checkOut the check-out date
     * @return true if there are available rooms, false otherwise
     */
    public boolean getAllRooms(int checkIn, int checkOut) {
        boolean avail = false;
        System.out.println("The following are the rooms and their status on your scheduled dates: ");
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).checkAvailability(checkIn, checkOut)) {
                System.out.println((i + 1) + ". Room \u001b[36;1m" + rooms.get(i).getName() + "\u001b[0m: \u001b[32;1mAVAILABLE\u001b[0m");
                avail = true;
            } else {
                System.out.println((i + 1) + ". Room \u001b[36;1m" + rooms.get(i).getName() + "\u001b[0m: \u001b[31;1mBooked\u001b[0m");
            }
        }
        return avail;
    }

    /**
     * Calculates the total number of available rooms for the given dates.
     *
     * @param checkIn  the check-in date
     * @param checkOut the check-out date
     */
    private void getTotalAvailable(int checkIn, int checkOut) {
        totalAvailable = 0;
        for (Room room : rooms) {
            if (room.checkAvailability(checkIn, checkOut)) {
                totalAvailable++;
            }
        }
    }

    /**
     * Calculates the total number of booked rooms for the given dates.
     *
     * @param checkIn  the check-in date
     * @param checkOut the check-out date
     */
    private void getTotalBooked(int checkIn, int checkOut) {
        totalBooked = 0;
        for (Room room : rooms) {
            if (!room.checkAvailability(checkIn, checkOut)) {
                totalBooked++;
            }
        }
    }

    /**
     * Displays the list of all rooms in the hotel.
     */
    public void displayRooms() {
        System.out.println("\nList of rooms in the hotel:");
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println("[" + (i + 1) + "] Room: \u001b[36;1m" + rooms.get(i).getName() + "\u001b[0m");
        }
    }

    /**
     * Adds a specified number of rooms to the hotel.
     *
     * @param n the number of rooms to add
     */
    public void addRoom(int n, int type) { //1 - Standard, 2 - Deluxe, 3 - Executive
        for (int i = 0; i < n; i++) {
            String lastRoomName = rooms.get(rooms.size() - 1).getName();
            char letterPart = lastRoomName.charAt(0); // Get the letter part of the room name
            int numberPart = Character.getNumericValue(lastRoomName.charAt(1));
            //int numberPart = Integer.parseInt(lastRoomName.substring(1)); // Get the number part of the room name

            numberPart++;
            if (numberPart == 10) {
                letterPart++;
                numberPart = 0;
            }

            String newRoomName = String.format("%c%d", letterPart, numberPart);

            switch (type){
                case 1: 
                    rooms.add(new Room(newRoomName));
                break;

                case 2:
                    rooms.add(new DeluxeRoom(newRoomName + " (Deluxe)"));
                break;

                case 3:
                    rooms.add(new ExecutiveRoom(newRoomName + " (Executive)"));
                break;
            }
            
        }
    }

    /**
     * Removes the room at the specified index, if it has no reservations.
     *
     * @param index the index of the room to remove
     */
    public void removeRoom(int index) {
        if (rooms.size() == 1) {
            System.out.println("\nSorry, there should at least be 1 room in Hotel " + name + ".");
            return;
        }
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i) == rooms.get(index - 1)) {
                boolean canRemove = true;
                for (Reservation reservation : reservations) {
                    if (reservation.getRoomInfo() == rooms.get(index - 1)) {
                        canRemove = false;
                        break;
                    }
                }
                if (canRemove) {
                    System.out.println("\nRoom \u001b[36;1m" + rooms.get(i).getName() + "\u001b[0m removed successfully.");
                    rooms.remove(i);
                } else {
                    System.out.println("\nThe room " + rooms.get(i).getName() + " has reservations and cannot be removed.");
                }
                return;
            }
        }
        System.out.println("Room " + rooms.get(index - 1) + " not found.");
    }

    /**
     * Adds a reservation for the specified guest and dates in the given room.
     *
     * @param guestName the name of the guest
     * @param checkIn   the check-in date
     * @param checkOut  the check-out date
     * @param room      the room to reserve
     */
    public void addReservation(String guestName, int checkIn, int checkOut, Room room, int discountApplied) {  
        if (room.checkAvailability(checkIn, checkOut)) {
            Reservation r = new Reservation(guestName, checkIn, checkOut, room);
            reservations.add(r);
            updateRoomAvailability(room, checkIn, checkOut, true);

            applyDiscount(discountApplied, r);

            updateEarnings();

        } else {
            System.out.println("Room " + room.getName() + " is not available for the selected dates.");
        }
    }

    public void applyDiscount(int discountApplied, Reservation r){
        double newPrice;

        r.setDiscount(discountApplied);

        switch (discountApplied){

            case 1:

                discount1 dc1 = new discount1();

                newPrice = dc1.calculateDiscount(r);

                r.setTotalPrice(newPrice);
                

            break;

            case 2:

                discount2 dc2 = new discount2();

                newPrice = dc2.calculateDiscount(r);

                r.setTotalPrice(newPrice);

            break;

            case 3:

                discount3 dc3 = new discount3();

                newPrice = dc3.calculateDiscount(r);

                r.setTotalPrice(newPrice);

            break;
        }

    }

    /**
     * Removes a reservation selected by the user.
     */
    public void removeReservation() {
        System.out.println("To remove a reservation, please select the guest number:");
        for (int i = 0; i < reservations.size(); i++) {
            System.out.println("[" + (i + 1) + "] Guest: \u001b[36;1m" + reservations.get(i).getGuestName() + "\u001b[0m");
        }
        int guestIndex = input.nextInt();
        input.nextLine();

        if (guestIndex == 0) {
            System.out.println("\nHotel management canceled. Going back to the previous menu...\n");
            return;
        }

        while (guestIndex < 0 || guestIndex > reservations.size()) {
            System.out.print("\nInvalid input. Please enter a valid guest number from the list: ");
            guestIndex = input.nextInt();
        }

        System.out.println("\nAre you sure you want to remove this reservation?");
        System.out.println("[1] Yes                  [2] No");
        System.out.print("INPUT: ");
        int option = input.nextInt();
        input.nextLine(); // Consume leftover newline
        while (option != 1 && option != 2) {
            System.out.print("\nInvalid input. Please enter [1] for Yes or [2] for No: ");
            option = input.nextInt();
            input.nextLine(); // Consume leftover newline
        }

        if (option == 1) {
            Reservation removedReservation = reservations.remove(guestIndex - 1);
            updateRoomAvailability(removedReservation.getRoomInfo(), removedReservation.getCheckIn(), removedReservation.getCheckOut(), false);
            updateEarnings();
            System.out.println("\nReservation removed successfully.");
        } else {
            System.out.println("\nReservation removal canceled.");
        }
    }

    /**
     * Updates the base price of all rooms in the hotel.
     *
     * @param price the new base price
     */
    public void updateBasePrice(double price) {
        /*
        for (Room room : rooms) {
            room.setBasePrice(price);
        }
        */

        for (int i = 0; i < rooms.size(); i++){

            rooms.get(i).setBasePrice(price);
            
            if (rooms.get(i) instanceof DeluxeRoom){
                DeluxeRoom a = (DeluxeRoom) rooms.get(i);
                a.updatePrice();
            }

            if (rooms.get(i) instanceof ExecutiveRoom){
                ExecutiveRoom a = (ExecutiveRoom) rooms.get(i);
                a.updatePrice();
            }

            for (int j = 0; j < 31; j++){

                rooms.get(i).getDPMList().get(j).setBasePrice(price);

            }

        }

        System.out.println("Base price updated successfully.\n");
    }

    /**
     * Updates the availability of a room for the specified dates.
     *
     * @param room     the room to update
     * @param checkIn  the check-in date
     * @param checkOut the check-out date
     * @param booked   true if the room is booked, false if it is available
     */
    private void updateRoomAvailability(Room room, int checkIn, int checkOut, boolean booked) {
        for (int i = checkIn - 1; i < checkOut; i++) {
            room.getDaysBooked().set(i, booked);
        }
    }

    public void updateDPM(int dateModified, int modifierValue){

        int i;

        for (i = 0; i < rooms.size(); i++){
            rooms.get(i).getDPMList().get(dateModified).setModifier(modifierValue);
        }

    }

    /**
     * Updates the total earnings of the hotel based on current reservations.
     */
    private void updateEarnings() {
        earnings = 0;
        for (Reservation reservation : reservations) {
            earnings += reservation.getTotalPrice();
        }
    }

    /**
     * Gets the total number of rooms in the hotel.
     *
     * @return the total number of rooms
     */
    public int getRoomAmt() {
        return rooms.size();
    }
}
