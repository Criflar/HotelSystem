public class discount2 extends discount{
    
    double calculateDiscount(double basePrice, int noOfDays){

        noOfDays = noOfDays - 1;

        return basePrice * noOfDays;

    }

}
