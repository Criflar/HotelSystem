import java.util.ArrayList;

public class Room {
    private String roomName;
    private double basePrice;
    private ArrayList<Boolean> daysBooked;

    public Room(String roomName) {
        this.roomName = roomName;
        this.basePrice = 1299.0;
        this.daysBooked = new ArrayList<>(31); // Initialize with 31 days
        
        for (int i = 0; i < 31; i++) {
            daysBooked.add(false); // Initially, all days are not booked
        }
    }

    public void setBasePrice(double price) {
        if (price >= 100.0) {
            this.basePrice = price;
        } else {
            System.out.println("Invalid value. New price must be greater than or equal to 100.0");
        }
    }

    public boolean bookRoom(int startDay, int endDay) {
        if (!isValidBookingRange(startDay, endDay)) {
            System.out.println("Invalid booking dates.");
            return false;
        }

        for (int i = startDay - 1; i < endDay; i++) {
            daysBooked.set(i, true);
        }
        return true;
    }

    private boolean isValidBookingRange(int startDay, int endDay) {
        return startDay >= 1 && startDay <= 31 &&
               endDay >= 1 && endDay <= 31 &&
               startDay <= endDay;
    }

    public boolean checkAvailability(int checkIn, int checkOut) {
        if (!isValidCheckInOutDates(checkIn, checkOut)) {
            System.out.println("Invalid check-in or check-out dates.");
            return false;
        }

        for (int i = checkIn - 1; i <= checkOut - 1; i++) {
            if (daysBooked.get(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidCheckInOutDates(int checkIn, int checkOut) {
        return checkIn >= 1 && checkIn <= 31 &&
               checkOut >= 1 && checkOut <= 31 &&
               checkIn <= checkOut;
    }

    public void displayBookedStatus(String input) {
        for (int i = 0; i < 31; i++) {
            if (input.equalsIgnoreCase("YES")) {
                displayAllDaysStatus(i);
            } else {
                displayAvailableDaysStatus(i);
            }
        }
    }

    private void displayAllDaysStatus(int i) {
        System.out.print("Day " + (i + 1) + ": ");
        if (daysBooked.get(i)) {
            System.out.println("\u001b[31;1m" + "Booked" + "\u001b[0m");
        } else {
            System.out.println("\u001b[32;1m" + "Available" + "\u001b[0m");
        }
    }

    private void displayAvailableDaysStatus(int i) {
        if (daysBooked.get(i)) {
            System.out.print("Day " + (i + 1) + ":   ");
            System.out.println("\u001b[32;1m" + "Available" + "\u001b[0m");
        }
    }

    // Getters for private fields
    public String getRoomName() {
        return roomName;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public int getTotalAvailable() {
        int count = 0;
        for (boolean booked : daysBooked) {
            if (!booked) {
                count++;
            }
        }
        return count;
    }

    public int getTotalBooked() {
        int count = 0;
        for (boolean booked : daysBooked) {
            if (booked) {
                count++;
            }
        }
        return count;
    }
}
