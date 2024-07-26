import java.util.*;

/**
 * This class implements a simple command-line based Hotel Reservation System.
 * It allows users to create hotels, view hotel details, manage hotels,
 * book reservations, and exit the system.
 * @author Marvin Ivan C. Mangubat
 * @author Reiven Davidson Viray
 * @version 12.0
 */
public class Driver {

    /**
     * The main method that drives the Hotel Reservation System.
     */
    public static void main(String[] args) {
        // Declaration of variables
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
        int roomType;
        int invalidRoomNum;
        int toRemove;
        int removingRooms;
        double newBasePrice;
        int invalidPrice;
        int removeHotel;
        int dateModified;
        int modifierValue;
        boolean invalidPercent;
        HRS HRS = new HRS(); // Instantiate the Hotel Reservation System

        Scanner input = new Scanner(System.in); // Scanner object for user input

        // Main loop for the menu system
        while (menu == 1) {
            System.out.println("\n       ~~~~~~~~~~ \u001b[33;1mHOTEL RESERVATION SYSTEM\u001b[0m ~~~~~~~~~~\n");

            // Displaying menu options
            System.out.println("         ============= \u001b[35;1mMANAGER OPTIONS\u001b[0m: =============");
            System.out.println("                      [1] Create Hotel");
            System.out.println("                      [2] View Hotel");
            System.out.println("                      [3] Manage Hotel");

            System.out.println("                 ---------------------------");

            System.out.println("         ============= \u001b[35;1mCUSTOMER OPTIONS\u001b[0m: =============");
            System.out.println("                    [4] Book a Reservation");

            System.out.println("                 ---------------------------");

            System.out.println("                          [5] Exit\n");
            System.out.println("   --- \u001b[37;1mRemember, enter [0] to go back to the previous menu!\u001b[0m ---\n");

            System.out.print("INPUT: ");
            option = input.nextInt();
            input.nextLine(); // leftover newline

            // Validate user input
            while (option < 0 || option > 5) {
                System.out.print("Invalid input. Please enter a valid choice: ");
                option = input.nextInt();
                input.nextLine(); // leftover newline
            }

            // Switch case based on user's menu option
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

                            // Validate user confirmation choice
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
                            System.out.println("\nA hotel with the name '" + hotelName + "' already exists. Please choose a different name.");
                        }

                    }
                    break;

                case 2:
                    // Display list of hotels
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
                        System.out.println("===============================================");
                        System.out.println("|                \u001b[33;1mHotel Info\u001b[0m                  |");
                        System.out.println("===============================================");
                        System.out.println("| Hotel Name: \u001b[36;1m" + HRS.getHotelList().get(hotelChoice).getName() + "\u001b[0m");
                        System.out.println("===============================================");
                        System.out.println("|  [1] Hotel Overview");
                        System.out.println("|  [2] Room and Reservation Overview ");
                        System.out.println("===============================================");
                        System.out.print("INPUT: ");
                        viewChoice = input.nextInt();
                        input.nextLine(); // leftover newline

                        // Validate user input for view options
                        while (viewChoice != 0 && viewChoice != 1 && viewChoice != 2) {
                            System.out.print("Invalid input. Please enter a valid choice: ");
                            viewChoice = input.nextInt();
                            input.nextLine(); // leftover newline
                        }

                        switch (viewChoice) {
                            case 0:
                                System.out.println("\nHotel viewing canceled. Going back to the previous menu...\n");
                                break;

                            case 1:
                                HRS.getHotelList().get(hotelChoice).viewHighInfo();
                                break;

                            case 2:
                                System.out.println("\n===============================================");
                                System.out.println("|        \u001b[33;1mRoom and Reservation Overview\u001b[0m        |");
                                System.out.println("===============================================");
                                System.out.println("|  [1] View Available Rooms on a Date Range");
                                System.out.println("|  [2] Room Info");
                                System.out.println("|  [3] Reservation Info");
                                System.out.println("===============================================");
                                System.out.print("INPUT: ");
                                lowChoice = input.nextInt();
                                input.nextLine(); // Consume the leftover newline

                                // Validate user input for low-level info options
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

                        System.out.println("===============================================");
                        System.out.println("|              \u001b[33;1mHotel Management\u001b[0m               |");
                        System.out.println("===============================================");
                        System.out.println(" Hotel Name: \u001b[36;1m" + HRS.getHotelList().get(manageChoice).getName() + "\u001b[0m");
                        System.out.println("===============================================");
                        System.out.println("| [1] Name Change");
                        System.out.println("| [2] Add Room/s");
                        System.out.println("| [3] Remove Room/s");
                        System.out.println("| [4] Update Base Price");
                        System.out.println("| [5] Remove Reservation");
                        System.out.println("| [6] Remove Hotel");
                        System.out.println("| [7] Date Price Modifier");
                        System.out.println("===============================================");
                        System.out.print("INPUT: ");
                        manageChoice2 = input.nextInt();
                        input.nextLine(); // leftover newline

                        // Validate user input for manage options
                        while (manageChoice2 < 0 || manageChoice2 > 7) {
                            System.out.print("Invalid input. Please enter a valid choice: ");
                            manageChoice2 = input.nextInt();
                            input.nextLine(); // leftover newline
                        }

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
                                break;

                            case 2:
                                invalidRoomNum = 1;

                                if (HRS.getHotelList().get(manageChoice).getRoomAmt() == 50)
                                    invalidRoomNum = 0;

                                while (invalidRoomNum == 1) {
                                    System.out.print("\nCurrent # of Rooms: \u001b[36;1m" + HRS.getHotelList().get(manageChoice).getRoomAmt() + "\u001b[0m\n");

                                    System.out.print("What Type of Room to Add: ");
                                    roomType = input.nextInt();
                                    input.nextLine(); // leftover newline

                                    if (roomType == 0) {
                                        System.out.println("\nHotel management canceled. Going back to the previous menu...\n");
                                        break;
                                    }

                                    System.out.print("\nNumber of Rooms to Add: ");
                                    addRooms = input.nextInt();
                                    input.nextLine(); // leftover newline

                                    if (addRooms == 0) {
                                        System.out.println("\nHotel management canceled. Going back to the previous menu...\n");
                                        break;
                                    }

                                    while (addRooms < 0 || addRooms > (50 - HRS.getHotelList().get(manageChoice).getRoomAmt())) {
                                        if (HRS.getHotelList().get(manageChoice).getRoomAmt() + addRooms > 50)
                                            System.out.println("\nError. There is a limit of 50 rooms per hotel.");

                                        System.out.print("Please enter a valid number of rooms to add: ");
                                        addRooms = input.nextInt();
                                        input.nextLine(); // leftover newline
                                    }

                                    System.out.println("\nAre you sure you want to add " + addRooms + " rooms to " + HRS.getHotelList().get(manageChoice).getName() + "?");
                                    System.out.println("[1] Yes                  [2] No");
                                    System.out.print("INPUT: ");
                                    option = input.nextInt();
                                    input.nextLine(); // leftover newline

                                    while (option != 1 && option != 2) {
                                        confirmationChoices();
                                        option = input.nextInt();
                                        input.nextLine(); // leftover newline
                                    }

                                    if (option == 1) {
                                        HRS.getHotelList().get(manageChoice).addRoom(addRooms, roomType);
                                        System.out.println("\u001b[36;1m" + addRooms + "\u001b[0m rooms have been successfully added to \u001b[36;1m" + HRS.getHotelList().get(manageChoice).getName() + "\u001b[0m\n");
                                        System.out.println("Total Number of Rooms: \u001b[36;1m" + HRS.getHotelList().get(manageChoice).getRoomAmt() + "\u001b[0m\n");
                                        invalidRoomNum = 0;
                                    }
                                }
                                break;

                            case 3:
                                removingRooms = 1;
                                while (removingRooms == 1) {
                                    HRS.getHotelList().get(manageChoice).displayRooms();

                                    System.out.print("Select rooms to remove. Enter [0] to exit: ");

                                    toRemove = input.nextInt();
                                    input.nextLine(); // leftover newline

                                    if (toRemove != 0) { // finalized, remove rooms
                                        HRS.getHotelList().get(manageChoice).removeRoom(toRemove);
                                    } else {

                                        System.out.println("\nAre you done removing rooms?");
                                        System.out.println("[1] Yes                  [2] No");
                                        System.out.print("INPUT: ");
                                        option = input.nextInt();
                                        input.nextLine(); // leftover newline
                                        while (option != 1 && option != 2) {
                                            confirmationChoices();
                                            option = input.nextInt();
                                            input.nextLine(); // leftover newline
                                        }

                                        if (option == 1) {
                                            System.out.println("\nEnding room removal...");
                                            removingRooms = 0;
                                        }
                                    }
                                }
                                break;

                            case 4:
                                if (HRS.getHotelList().get(manageChoice).getReservations().isEmpty()) {
                                    invalidPrice = 1;
                                    while (invalidPrice == 1) {
                                        System.out.print("Enter New Price: ");
                                        newBasePrice = input.nextDouble();
                                        input.nextLine(); // leftover newline

                                        if ((int) newBasePrice == 0) {
                                            System.out.println("\nHotel management canceled. Going back to the previous menu...\n");
                                            break;
                                        }

                                        if (newBasePrice >= 100.00) {
                                            System.out.println("\nAre you sure you want to set the hotel's new base price to \u001b[36;1m" + newBasePrice + "\u001b[0m?");
                                            System.out.println("[1] Yes                  [2] No");
                                            System.out.print("INPUT: ");
                                            option = input.nextInt();

                                            while (option != 1 && option != 2) {
                                                System.out.print("\nInvalid input. Please enter a valid choice: ");
                                                option = input.nextInt();
                                                input.nextLine(); // leftover newline
                                            }

                                            if (option == 1) {
                                                HRS.getHotelList().get(manageChoice).updateBasePrice(newBasePrice);
                                                invalidPrice = 0;
                                            }

                                        } else {
                                            System.out.println("\nInvalid Price. New price must be PHP100.00 or above.");
                                        }
                                    }

                                } else {
                                    System.out.println("\nCannot change the price! There are still reservations in the hotel.");
                                }

                                break;

                            case 5:
                                if (HRS.getHotelList().get(manageChoice).getReservations().isEmpty()) {
                                    System.out.println("\nThere are currently no reservations in this hotel.\n");
                                } else {
                                    HRS.getHotelList().get(manageChoice).removeReservation();
                                }

                                break;

                            case 6:
                                System.out.println("\nAre you sure you want to remove this hotel?");
                                System.out.println("[1] Yes                  [2] No");

                                removeHotel = input.nextInt();
                                input.nextLine(); // leftover newline

                                while (removeHotel != 1 && removeHotel != 2) {
                                    System.out.print("Invalid input. Please enter a valid choice: ");
                                    removeHotel = input.nextInt();
                                    input.nextLine(); // leftover newline
                                }

                                if (removeHotel == 1) {
                                    HRS.removeHotel(manageChoice);
                                }

                                break;

                            case 7:
                                System.out.println("Input a date:");
                                dateModified = input.nextInt();
                                input.nextLine(); // leftover newline
                                
                                invalidPercent = true;
                                while (invalidPercent){
                                    System.out.println("In percentages, how would you like to modify the price? (50% - 150%)");
                                    modifierValue = input.nextInt();
                                    input.nextLine(); // leftover newline

                                    if (modifierValue < 50 || modifierValue > 150){
                                        System.out.println("Please input a valid value!");
                                    }

                                    else{
                                        invalidPercent = false;
                                        HRS.getHotelList().get(manageChoice).updateDPM(dateModified - 1, modifierValue);
                                        System.out.println("Date Price Modifier Successfully Set!\n");
                                    }
                                }  

                                break;

                        }
                    }
                    break;

                case 4: // booking
                    HRS.displayHotels();
                    HRS.book();
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
                        input.nextLine(); // leftover newline
                    }
                    if (option == 1) menu = 0;
                    else break;
            }

        }

        input.close(); // Close the scanner object
    }

    /**
     * Displays a prompt for confirmation choices.
     */
    public static void confirmationChoices() {
        System.out.println("Please enter only [1] Yes or [2] No.");
        System.out.println("[1] Yes                  [2] No");
        System.out.print("INPUT: ");
    }

}
