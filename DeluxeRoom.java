public class DeluxeRoom extends Room{
    
    public DeluxeRoom (String name){
        super(name);
        updatePrice();
    }

    public void updatePrice(){
        this.basePrice = basePrice * 1.20;
    }

}
