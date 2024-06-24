import java.util.*;
import java.time.*;

public class Room {
    private String name;
    private double basePrice;
    private ArrayList<Boolean> daysBooked = new ArrayList<Boolean>();

    public Room (String name) {
        this.name = name;
        this.basePrice = 1299.0;
        
        //adds 31 boolean items to the daysBooked arraylist. Every day is initially false = not booked.
        int i;
        for (i = 0; i < 31; i++){
            daysBooked.add(false);
        }
    }

    public void setBasePrice (double price) {
        if (price >= 100.0)
            this.basePrice = price;
        else
            System.out.println("Invalid value. New price must be greater than or equal to 100.0");
    }

    public void setDaysBooked (boolean status, int startingDay, int lastDay) {
        int i;

        for (i = startingDay; i < lastDay; i++) { // Books from start to last day.
            daysBooked.set(i, status);                                
        }
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getName() {
        return name;
    }

    public int getTotalAvailable() {
        int i;
        int total = 0;

        for (i = 0; i < daysBooked.size(); i++) {
            if (daysBooked.get(i) == false)
                total++;
        }

        return total;
    }

    public int getTotalBooked() {
        int i;
        int total = 0;

        for (i = 0; i < daysBooked.size(); i++) {
            if (daysBooked.get(i) == true)
                total++;
        }

        return total;
    }

    public boolean checkAvailability (int checkIn, int checkOut) {
        int i;

        // Adjust checkIn and checkOut to zero-based index
        checkIn -= 1;
        checkOut -= 1;

        /* 
            Check-in day can't be more than or equal to 31 or less than or equal 0, while Check-out day 
           can't be less than or equal to 1, greater than or equal to 32, or before the Check-in day.
       */
      //checkIn: 1-30, checkOut: 2-31
        if (checkIn < 0 || checkIn >= 30 || checkOut <= 0 || checkOut >= 31 || checkOut < checkIn) {
            return false;
        }

        // Check special condition for checkIn
        if (daysBooked.get(checkIn) && (checkIn + 1 <= checkOut) && !daysBooked.get(checkIn + 1)) {
            // Allow check-in on a booked day only if the next day is available
            checkIn++;
        }

        // Check if any day in the range [checkIn, checkOut] is booked
        for (i = checkIn; i <= checkOut; i++) {
            if (daysBooked.get(i)) {
                return false;
            }
        }

        return true; // Available if no booked days found in the range.
    }

    public void displayBooked(String input) { // Input to determine if all will be displayed or not.
        int i;

        for (i = 0; i < 31; i++){
            if (input.toUpperCase().equals("YES")) { // If the parameter is YES to show all days and their status.
                if (daysBooked.get(i)) {
                    System.out.print("  Day " + (i + 1) + ": ");
                    if (i < 9)
                        System.out.print(" ");
                    // System.out.println("\u001b[32;1m" + "AVAILABLE" + "\u001b[0m");
                    System.out.println("AVAILABLE");
                } else {
                    System.out.print("  Day " + (i + 1) + ": ");
                    if (i < 9)
                        System.out.print(" ");
                    System.out.println("\u001b[31;1m" + "Booked" + "\u001b[0m");
                    // System.out.println("Booked");
                }
            } else {
                if (daysBooked.get(i)) {
                    System.out.print("  Day " + (i + 1) + ":   ");
                    if (i < 9)
                        System.out.print(" ");
                    System.out.println("\u001b[32;1m" + "AVAILABLE" + "\u001b[0m");
                    // System.out.println("AVAILABLE");
                }
            }
        }
    }
    
}
