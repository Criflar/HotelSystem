import java.util.*;
import java.time.*;

public class HRS {
    private ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

    public void displayHotels() {
    	int i;

    	for (i = 0; i < hotelList.size(); i++) {
    		System.out.println((i+1) + ".) " + hotelList.get(i).getName());
    	}
    }

    public void book () {
		Scanner inputH = new Scanner(System.in);
		Scanner inputC = new Scanner(System.in);
		Scanner inputR = new Scanner(System.in);
		Scanner inputG = new Scanner(System.in);
		Scanner inputA = new Scanner(System.in);
    	int i;
		int chosenHotel;
		int choice;
		String guestName;
		int checkIn, checkOut;
		int nextRoom;
		int chosenRoom;
		Room room = new Room();

		System.out.println("Good day! If you would like to book with us, choose a number from our list of available hotels: ");
		for (i = 0; i < hotelList.size(); i++){
			System.out.println((i+1) + "  .) " + hotelList.get(i).getName());
		}
		System.out.println("Hotel: ");
    	chosenHotel = inputH.nextInt() - 1;

    	while (chosenHotel < 1 || chosenHotel > hotelList.size()) {
    		System.out.println("Invalid input. Please only enter a value between 1 and " + hotelList.size() + ": ");
    		chosenHotel = inputH.nextInt() - 1;
    	}

    	System.out.println("Please enter the day of your Check-In Date from Day 1 - 30: ");
		checkIn = inputR.nextInt();
		while (checkIn < 1 || checkIn > 30) {
    		System.out.println("Invalid input. Please only enter a value between 1 and 30: ");
    		checkIn = inputR.nextInt();
    	}

		System.out.println("Please enter the day of your Check-Out Date from Day 2 - 31: ");
		checkOut = inputR.nextInt();
		while (checkOut < 2 || checkIn > 31) {
    		System.out.println("Invalid input. Please only enter a value between 2 and 31: ");
    		checkOut = inputR.nextInt();
    	}

    	System.out.println("\n For the best user experience, we will let you choose if you want us to select a room based on your Check-in and Check-out dates ");
    	System.out.println("or if you want to instead see a list of available rooms on your dates to choose from. ");
    	System.out.println("Enter (1) if you want us to choose, (2) if you want to see a list of available rooms, or (3) if you want to see all rooms and their status: ");
    	choice = inputC.nextInt();
    	while (choice != 1 && choice != 2 && choice != 3) {
    		System.out.println("Invalid input. Please enter (1), (2), or (3) only: ");
    		choice = inputC.nextInt();
    	}

    	if (choice == 1) {

			nextRoom = hotelList.get(chosenHotel).getNextRoom(checkIn, checkOut); // gets the index
			if (nextRoom == -1) { // Handles if there are no rooms.
				System.out.println("Sorry! There are no available rooms for your scheduled dates right now.");
				System.out.println("If you would like to try with another date, enter (1). Otherwise, if you would like to exit the Hotel Reservation System, enter (2): ");
				choice = inputC.nextInt();
				while (choice != 1 && choice != 2) {
					System.out.println("Invalid input. Please enter (1) or (2) only: ");
    				choice = inputC.nextInt();	
				}
				
				if (choice == 1)
					book();
				else
					System.exit(0);

			}

			room = hotelList.get(chosenHotel).getRoom(nextRoom); // finds the room with that index
			System.out.println("To confirm your reservation, please enter your name: ");
			guestName = inputG.nextLine();

			hotelList.get(chosenHotel).addReservation(guestName, checkIn, checkOut, room);
			System.out.println("Congratulations! You have successfully booked Room " + room.getName() + " at Hotel " + hotelList.get(chosenHotel).getName());
		}
		
		else if (choice == 2) {
			hotelList.get(chosenHotel).getAvailRooms(checkIn, checkOut);
			System.out.println("Please enter your chosen room: ");
			chosenRoom = inputA.nextInt() - 1;
			while (!hotelList.get(chosenHotel).availRooms.contains(chosenRoom)) { // Between 1 and max number of rooms.
    			System.out.println("Invalid input. Please only enter a number of an available room from the list: ");
    			chosenRoom = inputA.nextInt() - 1;
    		}
			room = hotelList.get(chosenHotel).getRoom(chosenRoom); // finds the room with that index

			System.out.println("To confirm your reservation, please enter your name: ");
			guestName = inputG.nextLine();

			hotelList.get(chosenHotel).addReservation(guestName, checkIn, checkOut, room);
			System.out.println("Congratulations! You have successfully booked Room " + room.getName() + " at Hotel " + hotelList.get(chosenHotel).getName());
		}

		else if (choice == 3) {
			hotelList.get(chosenHotel).getAllRooms(checkIn, checkOut);
			System.out.println("Please enter your chosen room: ");
			chosenRoom = inputA.nextInt() - 1;
			while (!hotelList.get(chosenHotel).availRooms.contains(chosenRoom)) { // Between 1 and max number of rooms.
    			System.out.println("Invalid input. Please only enter a number of an available room from the list: ");
    			chosenRoom = inputA.nextInt() - 1;
    		}

			room = hotelList.get(chosenHotel).getRoom(chosenRoom); // finds the room with that index

			System.out.println("To confirm your reservation, please enter your name: ");
			guestName = inputG.nextLine();

			hotelList.get(chosenHotel).addReservation(guestName, checkIn, checkOut, room);
			System.out.println("Congratulations! You have successfully booked Room " + room.getName() + " at Hotel " + hotelList.get(chosenHotel).getName());
		}
    }

    public void removeHotel (int inputIndex) { // Since the hotels are numbered starting from 1, subtract 1 from the input.
    	hotelList.remove(inputIndex);
    }

	



}
