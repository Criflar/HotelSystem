import java.util.*;

public class Room {
    private int num;
    private double basePrice;
    private boolean availability;
    private ArrayList<Integer> day = new ArrayList<Integer>();

    public Room(int roomNum){
        int i;

        this.basePrice = 1299.0;
        this.availability = true;
        this.num = roomNum;
        
        //adds 31 items to the day arraylist. 0 = available, 1 = booked
        for (i = 0; i < 31; i++){
            day.add(0);
        }
    }

    public void setBasePrice(double price){
        this.basePrice = price;
    }
    
    public boolean isAvailable(int Date){
        if (day.get(Date-1) == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public double getBasePrice(){
        return basePrice;
    }

    public int getName(){
        return num;
    }

    public void getAvailability(){
        int i;

        for (i = 0; i < 31; i++){
            System.out.print("Day " + i+1 + ": ");

            if (isAvailable(i) == true){
                System.out.println("Vacant");
            }

            else{
                System.out.println("Booked");
            }
        }
    }
    
}
