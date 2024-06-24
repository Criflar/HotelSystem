import java.util.ArrayList;

public class Room {
    private String name;
    private double basePrice;
    private ArrayList<Boolean> daysBooked;

    // Constructor
    public Room(String name) {
        this.name = name;
        this.basePrice = 1299.0;
        this.daysBooked = new ArrayList<>(31); // Initialize with 31 days

        // Initialize all days as not booked (false)
        for (int i = 0; i < 31; i++) {
            daysBooked.add(false);
        }
    }

    // Setters
    public void setBasePrice(double price) {
        if (price >= 100.0) {
            this.basePrice = price;
        } else {
            System.out.println("Invalid value. New price must be greater than or equal to 100.0");
        }
    }

    public void setDaysBooked(boolean status, int startingDay, int lastDay) {
        // Ensure startingDay and lastDay are within valid range
        if (startingDay < 1) {
            startingDay = 1;
        }
        if (lastDay > 31) {
            lastDay = 31;
        }

        // Book/unbook days from startingDay to lastDay
        for (int i = startingDay - 1; i < lastDay; i++) {
            daysBooked.set(i, status);
        }
    }

    // Getters
    public double getBasePrice() {
        return basePrice;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Boolean> getDaysBooked() {
        return daysBooked;
    }

    public int getTotalAvailable() {
        int total = 0;
        for (boolean booked : daysBooked) {
            if (!booked) {
                total++;
            }
        }
        return total;
    }

    public int getTotalBooked() {
        int total = 0;
        for (boolean booked : daysBooked) {
            if (booked) {
                total++;
            }
        }
        return total;
    }

    public boolean checkAvailability(int checkIn, int checkOut) {
        // Adjust checkIn and checkOut to zero-based index
        checkIn--;
        checkOut--;

        if (checkIn < 0 || checkIn >= 30 || checkOut <= 0 || checkOut >= 31 || checkOut < checkIn) {
            return false;
        }

        // Check special condition for checkIn
        if (daysBooked.get(checkIn) && (checkIn + 1 <= checkOut) && !daysBooked.get(checkIn + 1)) {
            checkIn++;
        }

        // Check if any day in the range [checkIn, checkOut] is booked
        for (int i = checkIn; i <= checkOut; i++) {
            if (daysBooked.get(i)) {
                return false;
            }
        }

        return true;
    }

    public void displayBooked(String input) {
        for (int i = 0; i < 31; i++) {
            if (input.equalsIgnoreCase("YES")) {
                if (daysBooked.get(i)) {
                    System.out.printf("  Day %2d: \u001b[31;1mBooked\u001b[0m%n", i + 1);
                } else {
                    System.out.printf("  Day %2d: \u001b[32;1mAVAILABLE\u001b[0m%n", i + 1);
                }
            } else {
                if (daysBooked.get(i)) {
                    System.out.printf("  Day %2d: \u001b[32;1mAVAILABLE\u001b[0m%n", i + 1);
                }
            }
        }
    }
}
