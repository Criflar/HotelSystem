import java.util.*;
import java.time.*;

public class HRS {
    private ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

    public void displayHotels() {
    	int i;

    	for (i = 0; i < hotelList.size(); i++) {
    		System.out.println((i+1) + ". " + hotelList.get(i).name);
    	}
    }

    public void book (String hotelName) {
    	int i = 0;

    	for (i = 0; i < hotelList.size(); i++) {
    		if (hotelList.get(i) == hotelName) {
    			System.out.print("Hotel is available!");
    		}
    	}
    	// continue
    }

    public void removeHotel (int inputIndex) { // Since the hotels are numbered starting from 1, subtract 1 from the input.
    	hotelList.remove(inputIndex - 1);
    }



}
