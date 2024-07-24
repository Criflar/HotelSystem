import java.util.ArrayList;

/**
 * The Room class represents a hotel room with a name, base price, and booking status for each day of a month.
 */
public class Room {
    protected String name;
    protected double basePrice;
    protected ArrayList<Boolean> daysBooked;
    protected ArrayList<DPM> DPMList;

    /**
     * Constructor to initialize a Room with a name and default base price.
     * The room is initialized with all days as not booked (false).
     *
     * @param name the name of the room
     */
    public Room(String name) {
        this.name = name;
        this.basePrice = 1299.0;
        this.daysBooked = new ArrayList<>(31); // Initialize with 31 days
        this.DPMList = new ArrayList<>(31); // Initialize with 31 days

        
        for (int i = 0; i < 31; i++) {
            daysBooked.add(false); // Initialize all days as not booked (false)
            DPMList.add(new DPM(basePrice)); // Initialize all days' base prices.
        }
    }

    /**
     * Sets the base price of the room. The price must be greater than or equal to 100.0.
     *
     * @param price the new base price of the room
     */
    public void setBasePrice(double price) {
        if (price >= 100.0) { // Ensure the new price is valid
            this.basePrice = price;

        } else {
            System.out.println("Invalid value. New price must be greater than or equal to 100.0");
        }
    }

    /**
     * Gets the base price of the room.
     *
     * @return the base price of the room
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Gets the name of the room.
     *
     * @return the name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the booking status of each day of the room.
     *
     * @return an ArrayList representing the booking status of each day
     */
    public ArrayList<Boolean> getDaysBooked() {
        return daysBooked;
    }

    public ArrayList<DPM> getDPMList(){
        return DPMList;
    }

    /**
     * Checks the availability of the room for a given date range.
     *
     * @param checkIn  the check-in day (1-based index)
     * @param checkOut the check-out day (1-based index)
     * @return true if the room is available for the entire range, false otherwise
     */
    public boolean checkAvailability(int checkIn, int checkOut) {
        // Adjust checkIn and checkOut to zero-based index
        checkIn--;
        checkOut--;

        // Validate the check-in and check-out range
        if (checkIn < 0 || checkIn >= 30 || checkOut <= 0 || checkOut >= 31 || checkOut < checkIn) {
            return false; // Invalid range
        }

        // Special condition for check-in: If the check-in day is booked,
        // but the next day is available, move the check-in day to the next day
        if (daysBooked.get(checkIn) && (checkIn + 1 <= checkOut) && !daysBooked.get(checkIn + 1)) {
            checkIn++;
        }

        // Loop through the range of days to check if any day is booked
        for (int i = checkIn; i <= checkOut; i++) {
            if (daysBooked.get(i)) {
                return false; // At least one day in the range is booked
            }
        }

        return true; // All days in the range are available
    }

    /**
     * Displays the booking status of the room for each day.
     * If the input is "YES", both booked and available days are shown.
     * Otherwise, only available days are shown.
     *
     * @param input the input string to determine the display mode
     */
    public void displayBooked(String input) {
        for (int i = 0; i < 31; i++) {
            if (input.equalsIgnoreCase("YES")) {
                // Display both booked and available days
                if (daysBooked.get(i)) {
                    System.out.printf(" Day %2d: \u001b[31;1mBooked\u001b[0m%n", i + 1);
                } else {
                    System.out.printf(" Day %2d: \u001b[32;1mAVAILABLE\u001b[0m%n", i + 1);
                }
            } else {
                // Display only available days
                if (!daysBooked.get(i)) {
                    System.out.printf(" Day %2d: \u001b[32;1mAVAILABLE\u001b[0m%n", i + 1);
                }
            }
        }
    }
}
