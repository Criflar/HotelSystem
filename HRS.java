import java.util.*;
import java.time.*;

public class HRS {
    private ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

    public void displayHotels() {
    	int i;

    	for (i = 0; i < hotelList.size(); i++) {
    		System.out.println((i+1) + ". " + hotelList.get(i).getName());
    	}
    }

    public void book () {
		Scanner inputH = new Scanner(System.in);
		Scanner inputR = new Scanner(System.in);
    	int i;
		int chosenHotel;
		String guestName;
		int checkIn, checkOut;
		int nextRoom;
		Room room;



		//mas maganda siguro kung mamimili ang user sa mga available hotels

		/*
    	for (i = 0; i < hotelList.size(); i++) {
    		if (hotelList.get(i).getName() == hotelName) {
    			System.out.print("Hotel is available!");
    		}
    	}
		*/

		for (i = 0; i < hotelList.size(); i++){
			System.out.println(i+1 + ".) " + hotelList.get(i).getName());
		}
	
    	chosenHotel = inputH.nextInt();


		System.out.println("Input Check-In Date: ");
		checkIn = inputR.nextInt();
		System.out.println("Input Check-Out Date");
		checkOut = inputR.nextInt();
		System.out.println("Input Guest Name: ");
		guestName = inputR.nextLine();

		nextRoom = hotelList.get(chosenHotel).getNextRoom(checkIn, checkOut);
		room = hotelList.get(chosenHotel).getRoom(nextRoom);

		hotelList.get(chosenHotel).addReservation(guestName, checkIn, checkOut, room);
    }

    public void removeHotel (int inputIndex) { // Since the hotels are numbered starting from 1, subtract 1 from the input.
    	hotelList.remove(inputIndex - 1);
    }

	



}
