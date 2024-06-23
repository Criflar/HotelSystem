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
    
    public double displayPriceBreakdown(){
        return roomInfo.getBasePrice();
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
