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
    private int discount = 0;

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
        this.checkIn = checkIn - 1;
        this.checkOut = checkOut - 1;
        this.roomInfo = roomInfo;

        // Calculate the number of nights the guest will stay
        //int nights = checkOut - checkIn;
        // Calculate the total price for the stay
        //this.totalPrice = nights * roomInfo.getBasePrice();

        this.totalPrice = initTotalPrice(0);
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
        return checkIn + 1;
    }

    /**
     * Gets the check-out date of the reservation.
     *
     * @return the check-out date
     */
    public int getCheckOut() {
        return checkOut + 1;
    }

    /**
     * Gets the total price of the reservation.
     *
     * @return the total price of the reservation
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double newPrice){
        this.totalPrice = newPrice;
    }

    public int getDiscount(){
        return discount;
    }

    public void setDiscount(int n){
        this.discount = n;
    }

    public double initTotalPrice(int n){
        int i;
        double sum = 0;

        for (i = checkIn + n; i < checkOut; i++){
            sum = sum + roomInfo.getDPMList().get(i).getNewPrice();
        }

        return sum;
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
        discountScenario();
        System.out.println("  -----------------------------");
        // Display the total cost
        System.out.printf("  Total Cost:           \u001b[36;1m$%.1f%n\u001b[0m\n", totalPrice);
    }

    public void discountScenario(){

        switch (discount){

            case 1: 
                System.out.println("        \u001b[36;1m 10% Off Total \u001b[0m");
            break;

            case 2:
                System.out.println("        \u001b[36;1m Day 1 Free \u001b[0m");
            break;

            case 3:
                System.out.println("        \u001b[36;1m 7% Off Total \u001b[0m");
            break;

            case 0:
                System.out.println("    \u001b[36;1m No Discount Code Used. \u001b[0m");
            break;

        }
            
    }
}
