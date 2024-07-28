public class DPM implements DPMInterface{
    
    private double modifier;
    private double basePrice;

    public DPM (double basePrice){
        this.basePrice = basePrice;
        this.modifier = 1.0;
    }

    public void setModifier(double n){
        n = n / 100; // ex: turns 50 into 0.5
        this.modifier = n;
    }

    public double getNewPrice(){
        return basePrice * modifier;
    }

    public void setBasePrice(double newPrice){
        this.basePrice = newPrice;
    }
}
