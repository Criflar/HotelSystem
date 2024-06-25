/**
 * The Reservation class represents a booking made by a guest for a room.
 * It contains details such as the guest's name, check-in and check-out dates,
 * the room information, and the total price for the stay.
 */
public class Reservation {
    private String guestName;
    private int checkIn;
    private int checkOut;
    private Room roomInfo;
    private double totalPrice;

    /**
     * Constructor to create a new Reservation with the given details.
     * Calculates the total price based on the number of nights and the room's base price.
     *
     * @param guestName the name of the guest making the reservation
     * @param checkIn the check-in date (1-based index)
     * @param checkOut the check-out date (1-based index)
     * @param roomInfo the Room object containing details about the booked room
     */
    public Reservation(String guestName, int checkIn, int checkOut, Room roomInfo) {
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomInfo = roomInfo;

        // Calculate the number of nights the guest will stay
        int nights = checkOut - checkIn;
        // Calculate the total price for the stay
        this.totalPrice = nights * roomInfo.getBasePrice();
    }

    /**
     * Gets the name of the guest who made the reservation.
     *
     * @return the guest's name
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * Gets the Room object containing information about the booked room.
     *
     * @return the Room object
     */
    public Room getRoomInfo() {
        return roomInfo;
    }

    /**
     * Gets the check-in date of the reservation.
     *
     * @return the check-in date
     */
    public int getCheckIn() {
        return checkIn;
    }

    /**
     * Gets the check-out date of the reservation.
     *
     * @return the check-out date
     */
    public int getCheckOut() {
        return checkOut;
    }

    /**
     * Gets the total price of the reservation.
     *
     * @return the total price of the reservation
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Displays a detailed breakdown of the price for the reservation.
     */
    public void displayPriceBreakdown() {
        // Calculate the number of nights the guest will stay
        int nights = checkOut - checkIn;

        // Display the breakdown of the cost per night and the total cost
        System.out.println("  Cost Breakdown per Night: ");
        System.out.println("  -----------------------------");
        // Display the base price per night
        System.out.printf("  Base Price per Night: \u001b[36;1m$%.1f%n\u001b[0m", roomInfo.getBasePrice());
        // Display the number of nights
        System.out.printf("  Number of Nights:     \u001b[36;1m%d%n\u001b[0m", nights);
        System.out.println("  -----------------------------");
        // Display the total cost
        System.out.printf("  Total Cost:           \u001b[36;1m$%.1f%n\u001b[0m\n", totalPrice);
    }
}
