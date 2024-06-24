import java.util.*;
import java.time.*;

public class Reservation {
    private String guestName;
    private int checkIn;
    private int checkOut;
    private Room roomInfo;
    private double totalPrice;

    public Reservation(String guestName, int checkIn, int checkOut, Room roomInfo){
        int nights;

        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomInfo = roomInfo;
        
        nights = checkOut - checkIn;

        this.totalPrice = nights * roomInfo.getBasePrice();
    }

    public double displayTotalPrice(){
        return totalPrice;
    }
    
    public void displayPriceBreakdown(){
        int nights;
        nights = checkOut - checkIn;

        System.out.println("  Cost Breakdown per Night: ");
        System.out.println("  -----------------------------");
        System.out.printf("  Base Price per Night: $%.1f%n", roomInfo.getBasePrice());
        System.out.printf("  Number of Nights:     %d%n", nights);
        System.out.println("  -----------------------------");
        System.out.printf("  Total Cost:           $%.1f%n\n", totalPrice);
    }

    public String getGuestName(){
        return guestName;
    }

    public Room roomInfo(){
        return roomInfo;
    }

    public int getCheckIn(){
        return checkIn;
    }

    public int getCheckOut(){
        return checkOut;
    }
}
