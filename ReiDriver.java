import java.util.*;

public class ReiDriver {
    public static void main (String[] args){
        int i;
        int menu = 1;
        int option;
        int hotelChoice;
        int viewChoice;
        int lowChoice;
        int manageChoice;
        int manageChoice2;
        String hNewName;
        boolean uniqueName;
        boolean invalidName;
        int addRooms;
        int invalidRoomNum;
        int toRemove;
        int removingRooms;
        double newBasePrice;
        int invalidPrice;
        int removeHotel;
        HRS HRS = new HRS();

        Scanner input = new Scanner(System.in);

        while (menu == 1) {

            System.out.println("\n   ▒▓▒▓▒▓ HOTEL RESERVATION SYSTEM ▓▒▓▒▓▒▓\n");


            System.out.println("   ╔═════════ MANAGER OPTIONS: ═════════╗");
            System.out.println("             [1] Create Hotel");
            System.out.println("             [2] View Hotel");
            System.out.println("             [3] Manage Hotel");

            System.out.println("         ---------------------------");

            System.out.println("    ╔═════════ CUSTOMER OPTIONS: ═════════╗");
            System.out.println("            [4] Book a Reservation");

            System.out.println("         ---------------------------");

            System.out.println("                 [5] Exit\n");
            System.out.println("--- Remember, enter [0] to go back to the previous menu! ---\n");

            System.out.print("INPUT: ");
            option = input.nextInt();
            input.nextLine(); // leftover newline

            while (option < 0 || option > 5) {
                System.out.print("Invalid input. Please enter a valid choice: ");
                option = input.nextInt();
                input.nextLine(); // leftover newline
            }

            input.close();

            switch (option) {
                case 0:
                    System.out.println("Going back to the previous menu...");
                    break;

                case 1:
                    invalidName = true;
                    while (invalidName) {
                        System.out.print("\nEnter the name of the new hotel: ");
                        String hotelName = input.nextLine();

                        if (hotelName.equals("0")) {
                            System.out.println("\nHotel creation canceled. Going back to the previous menu...\n");
                            break;
                        }

                        // Check if the hotel name already exists
                        uniqueName = true;
                        for (i = 0; i < HRS.getHotelList().size(); i++) {
                            if (HRS.getHotelList().get(i).getName().equalsIgnoreCase(hotelName)) { // Case-insensitive check
                                uniqueName = false;
                                break;
                            }
                        }

                        if (uniqueName) {
                            System.out.println("\nAre you sure you want to name the hotel '" + hotelName + "'?");

                            confirmationChoices();
                            option = input.nextInt();
                            input.nextLine(); // Consume the leftover newline


                            while (option != 1 && option != 2) {
                                System.out.print("Invalid input. Please enter a valid choice: ");
                                option = input.nextInt();
                                input.nextLine(); // leftover newline
                            }

                            if (option == 1) {
                                HRS.addHotel(hotelName);
                                System.out.println("\nHotel '" + hotelName + "' added successfully.\n");
                                invalidName = false;
                            }
                        } else {
                            System.out.println("\nA hotel with the name '" + hotelName + "' already exists. Please choose a different name.\n");
                        }
                        input.close();
                    }
                    break;



                case 2:
                    HRS.displayHotels();

                        if (!HRS.getHotelList().isEmpty()) {
                            System.out.print("Which hotel would you like to view? (Input List Number Value): ");
                            hotelChoice = input.nextInt();
                            input.nextLine(); // leftover newline

                            if (hotelChoice == 0) {
                                System.out.println("\nHotel viewing canceled. Going back to the previous menu...\n");
                                break;
                            }

                            while (hotelChoice < 0 || hotelChoice > HRS.getHotelList().size()) {
                                System.out.println("Invalid input. Please enter a valid hotel number: ");
                                hotelChoice = input.nextInt();
                                input.nextLine(); // leftover newline
                            }

                            hotelChoice -= 1;
                            System.out.println("Hotel Info: " + HRS.getHotelList().get(hotelChoice).getName() + "\n");
                            System.out.println("  [1] Hotel Overview");
                            System.out.println("  [2] Room and Reservation Overview \n");
                            System.out.print("INPUT: ");
                            viewChoice = input.nextInt();
                            input.nextLine(); // leftover newline

                            while (viewChoice != 0 && viewChoice != 1 && viewChoice != 2) {
                                System.out.print("Invalid input. Please enter a valid choice: ");
                                viewChoice = input.nextInt();
                                input.nextLine(); // leftover newline
                            }
                            input.close();

                            switch (viewChoice) {
                                case 0:
                                    System.out.println("\nHotel viewing canceled. Going back to the previous menu...\n");
                                    break;

                                case 1:
                                    System.out.println("High-level Hotel Overview:");
                                    HRS.getHotelList().get(hotelChoice).viewHighInfo();
                                    break;

                                case 2:
                                        System.out.println("Low-level Room and Reservation Overview:");
                                        System.out.println("\n  [1] View Available Rooms on a Date");
                                        System.out.println("  [2] Room Info");
                                        System.out.println("  [3] Reservation Info\n");

                                        System.out.print("INPUT: ");
                                        lowChoice = input.nextInt();
                                        input.nextLine(); // Consume the leftover newline

                                        while (lowChoice != 0 && lowChoice != 1 && lowChoice != 2 && lowChoice != 3) {
                                            System.out.print("Invalid input. Please enter a valid choice: ");
                                            lowChoice = input.nextInt();
                                            input.nextLine(); // leftover newline
                                        }

                                        switch (lowChoice) {
                                            case 0:
                                                System.out.println("\nHotel viewing canceled. Going back to the previous menu...\n");
                                                break;
                                            case 1:
                                                HRS.getHotelList().get(hotelChoice).viewLowInfo1();
                                                break;

                                            case 2:
                                                HRS.getHotelList().get(hotelChoice).viewLowInfo2();
                                                break;

                                            case 3:
                                                HRS.getHotelList().get(hotelChoice).viewLowInfo3();
                                                break;
                                        }
                                    input.close();
                                    break;
                            }
                        }
                break;

                case 3:
                    HRS.displayHotels();

                    if (!HRS.getHotelList().isEmpty()) {
                        System.out.print("Which hotel would you like to manage? (Input List Number Value): ");
                        manageChoice = input.nextInt();
                        input.nextLine(); // leftover newline

                        if (manageChoice == 0) {
                            System.out.println("\nHotel management canceled. Going back to the previous menu...\n");
                            break;
                        }

                        while (manageChoice < 0 || manageChoice > HRS.getHotelList().size()) {
                            System.out.println("Invalid input. Please enter a valid hotel number: ");
                            manageChoice = input.nextInt();
                            input.nextLine(); // leftover newline
                        }

                        manageChoice -= 1;
                        System.out.println("Hotel Management: " + HRS.getHotelList().get(manageChoice).getName() + "\n");

                        System.out.println("[1] Name Change");
                        System.out.println("[2] Add Room/s");
                        System.out.println("[3] Remove Room/s");
                        System.out.println("[4] Update Base Price");
                        System.out.println("[5] Remove Reservation");
                        System.out.println("[6] Remove Hotel\n");

                        System.out.print("INPUT: ");
                        manageChoice2 = input.nextInt();
                        input.nextLine(); // leftover newline

                        while (manageChoice2 < 0 || manageChoice2 > 6) {
                            System.out.print("Invalid input. Please enter a valid choice: ");
                            manageChoice2 = input.nextInt();
                            input.nextLine(); // leftover newline
                        }

                        input.close();


                        switch (manageChoice2) {
                            case 0:
                                System.out.println("\nHotel management canceled. Going back to the previous menu...\n");
                                break;

                            case 1:
                                invalidName = true;
                                while (invalidName) {
                                    System.out.print("\nNew Name: ");
                                    hNewName = input.nextLine();
                                    uniqueName = true;

                                    if (hNewName.equals("0")) {
                                        System.out.println("\nHotel management canceled. Going back to the previous menu...\n");
                                        break;
                                    }

                                    for (i = 0; i < HRS.getHotelList().size(); i++) {
                                        if (HRS.getHotelList().get(i).getName().equalsIgnoreCase(hNewName)) { // Use equalsIgnoreCase for case-insensitive comparison
                                            uniqueName = false;
                                            break;
                                        }
                                    }

                                    if (uniqueName) {
                                        System.out.println("\nAre you sure you want to rename the hotel to '" + hNewName + "'?");
                                        System.out.println("[1] Yes                  [2] No");
                                        System.out.print("INPUT: ");
                                        option = input.nextInt();
                                        input.nextLine(); // Consume the leftover newline

                                        while (option != 1 && option != 2) {
                                            System.out.print("Invalid input. Please enter a valid choice: ");
                                            option = input.nextInt();
                                            input.nextLine(); // leftover newline
                                        }

                                        if (option == 1) {
                                            HRS.getHotelList().get(manageChoice).setHotelName(hNewName);
                                            invalidName = false;
                                            System.out.println("\nHotel renamed successfully to '" + hNewName + "'.\n");
                                        }
                                    } else {
                                        System.out.println("\nPlease enter a unique name.");
                                    }
                                }
                                input.close();
                                break;

                            case 2:
                                invalidRoomNum = 1;

                                if (HRS.getHotelList().get(manageChoice).getRoomAmt() == 50)
                                    invalidRoomNum = 0;

                                while (invalidRoomNum == 1) {
                                    System.out.print("\nCurrent # of Rooms: " + HRS.getHotelList().get(manageChoice).getRoomAmt() + "\n");

                                    System.out.print("Number of Rooms to Add: ");
                                    addRooms = input.nextInt();
                                    input.nextLine(); // leftover newline

                                    if (addRooms == 0) {
                                        System.out.println("\nHotel management canceled. Going back to the previous menu...\n");
                                        break;
                                    }

                                    while (addRooms < 0 || addRooms > (50 - HRS.getHotelList().get(manageChoice).getRoomAmt())) {
                                        if (HRS.getHotelList().get(manageChoice).getRoomAmt() + addRooms > 50)
                                            System.out.println("Error. There is a limit of 50 rooms per hotel.");

                                        System.out.println("Invalid input. Please enter a valid number of rooms to add: ");
                                        addRooms = input.nextInt();
                                        input.nextLine(); // leftover newline
                                    }

                                    System.out.println("\nAre you sure you want to add " + addRooms + " rooms to " + HRS.getHotelList().get(manageChoice).getName() + "?");
                                    confirmationChoices();
                                    option = input.nextInt();

                                    while (option != 1 && option != 2) {
                                        System.out.print("Invalid input. Please enter a valid choice: ");
                                        option = input.nextInt();
                                        input.nextLine(); // leftover newline
                                    }

                                    if (option == 1) {
                                        HRS.getHotelList().get(manageChoice).addRoom(addRooms);
                                        System.out.println(addRooms + " Rooms have been successfully added to " + HRS.getHotelList().get(manageChoice).getName() + "\n");
                                        System.out.println("Total Number of Rooms: " + HRS.getHotelList().get(manageChoice).getRoomAmt() + "\n");
                                        invalidRoomNum = 0;
                                    }
                                }
                                input.close();
                                break;


//                            case 3:
//                                ArrayList<Integer> removeRooms = new ArrayList<Integer>();
//
//                                while(removingRooms == 1){
//                                    HRS.getHotelList().get(manageChoice).displayRooms();
//
//                                    System.out.println("Select Rooms to Remove. Enter [0] to Finalize Selection");
//
//                                    toRemove = input.nextInt();
//                                    toRemove -= 1;
//                                    input.nextLine(); // leftover newline
//
//                                    if (toRemove < 0){ // finalized, remove rooms
//                                        for (i = 0; i < HRS.getHotelList().get(manageChoice).getRoomAmt(); i++){
//                                            HRS.getHotelList().get(manageChoice).removeRoom(removeRooms(i));
//                                        }
//                                    }
//                                    else {
//                                        removeRooms.add(toRemove);
//                                    }
//
//
//                                }
//                                break;

                            case 4:
                                invalidPrice = 1;
                                while (invalidPrice == 1) {
                                    System.out.print("Enter New Price: ");
                                    newBasePrice = input.nextDouble();
                                    input.nextLine(); // leftover newline

                                    if ((int)newBasePrice == 0) {
                                        System.out.println("\nHotel management canceled. Going back to the previous menu...\n");
                                        break;
                                    }

                                    if (newBasePrice >= 100.00) {
                                        System.out.println("\nAre you sure you want to set the hotel's new base price to " + newBasePrice + "?");
                                        System.out.println("[1] Yes                  [2] No");
                                        System.out.print("INPUT: ");
                                        option = input.nextInt();

                                        while (option != 1 && option != 2) {
                                            System.out.print("Invalid input. Please enter a valid choice: ");
                                            option = input.nextInt();
                                            input.nextLine(); // leftover newline
                                        }

                                        confirmationChoices();
                                        option = input.nextInt();
                                        input.nextLine(); // leftover newline

                                        while (option != 1 && option != 2) {
                                            System.out.print("Invalid input. Please enter a valid choice: ");
                                            option = input.nextInt();
                                            input.nextLine(); // leftover newline
                                        }

                                        if (option == 1) {
                                            HRS.getHotelList().get(manageChoice).updateBasePrice(newBasePrice);
                                            invalidPrice = 0;
                                        }
                                    } else {
                                        System.out.println("Invalid Price. New price must be PHP100.00 or above.");
                                    }
                                }
                                input.close();
                                break;

                            case 5:
                                //remove reservation to be implemented after booking

                                break;

                            case 6:
                                System.out.println("\nAre you sure you want to remove this hotel?\n");
                                System.out.println("[1] Yes                  [2] No");

                                removeHotel = input.nextInt();
                                input.nextLine(); //leftover newline

                                while (removeHotel != 1 && removeHotel != 2) {
                                    System.out.print("Invalid input. Please enter a valid choice: ");
                                    removeHotel = input.nextInt();
                                    input.nextLine(); // leftover newline
                                }

                                if (removeHotel == 1) {
                                    HRS.removeHotel(manageChoice);
                                }

                                input.close();
                                break;

                        }
                    }
                break;

                case 4: // booking
                    HRS.displayHotels();

                    if (!HRS.getHotelList().isEmpty()) {
                        System.out.println("Which hotel would you like to book a room in? ");


                    }
                break;

                case 5:
                    System.out.println("\nAre you sure you want to exit the Hotel Reservation System?");
                    System.out.println("[1] Yes                  [2] No");
                    System.out.print("INPUT: ");
                    option = input.nextInt();
                    input.nextLine();
                    while (option != 1 && option != 2) {
                        confirmationChoices();
                        option = input.nextInt();
                        // input.nextLine(); // leftover newline
                    }
                    if (option == 1) menu = 0;
                    else break;
                    input.close();
            }

        }

    }

    public static void confirmationChoices() {
        System.out.println("Please enter only [1] Yes or [2] No.");
        System.out.println("[1] Yes                  [2] No");
        System.out.print("INPUT: ");
    }
}
