public class discount1 extends discount{
    
    double calculateDiscount(Reservation r){

        return r.getTotalPrice() * 0.9;

    }

}
