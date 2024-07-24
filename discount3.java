public class discount3 extends discount{
    
    double calculateDiscount(Reservation r){

        return r.getTotalPrice() * 0.93;

    }

}
