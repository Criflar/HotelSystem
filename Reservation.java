public class Reservation {
    private String guestName;
    private int checkIn;
    private int checkOut;
    private Room roomInfo;
    private double totalPrice;

    public Reservation(String guestName, int checkIn, int checkOut, Room roomInfo, double totalPrice){
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomInfo = roomInfo;
        this.totalPrice = //make calculations for how much the reservation costs (amt of days * price per night)
    }
}
