public class ExecutiveRoom extends Room{

    public ExecutiveRoom(String name){
        super(name);
        updatePrice();
    }

    public void updatePrice(){
        this.basePrice = basePrice * 1.35;
    }

}
