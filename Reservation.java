public class Reservation {
    private String guestName;
    private int checkIn;
    private int checkOut;
    private Room roomInfo;
    private double totalPrice;

    // Constructor
    public Reservation(String guestName, int checkIn, int checkOut, Room roomInfo) {
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomInfo = roomInfo;

        int nights = checkOut - checkIn; // Calculate number of nights
        this.totalPrice = nights * roomInfo.getBasePrice(); // Calculate total price
    }

    // Getters
    public String getGuestName() {
        return guestName;
    }

    public Room getRoomInfo() {
        return roomInfo;
    }

    public int getCheckIn() {
        return checkIn;
    }

    public int getCheckOut() {
        return checkOut;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // Method to display price breakdown
    public void displayPriceBreakdown() {
        int nights = checkOut - checkIn;

        System.out.println("  Cost Breakdown per Night: ");
        System.out.println("  -----------------------------");
        System.out.printf("  Base Price per Night: $%.1f%n", roomInfo.getBasePrice());
        System.out.printf("  Number of Nights:     %d%n", nights);
        System.out.println("  -----------------------------");
        System.out.printf("  Total Cost:           $%.1f%n\n", totalPrice);
    }
}
