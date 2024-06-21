import java.util.*;

public class Hotel {
    private String name;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    private double earnings;
    private int roomAmt = 1;

    public Hotel(String name, double price){
        this.name = name;
        this.rooms.add(new Room(roomAmt));
        this.earnings = roomAmt * rooms.get(0).getBasePrice();
    }

    public String getName(){
        return this.name;
    }
    
    public int roomAmt(){
        return roomAmt;
    }

    public void getAvailRoom(int date){
        int i;

        for (i = 0; i < roomAmt; i++){
            if (rooms.get(i).isAvailable(date) == true){
                System.out.println("Room " + rooms.get(i).getName() + " : Vacant");
            }
            else{
                System.out.println("Room " + rooms.get(i).getName() + " : Booked");
            }
        }
    }


}
